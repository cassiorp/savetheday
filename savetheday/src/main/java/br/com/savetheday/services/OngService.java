package br.com.savetheday.services;

import br.com.savetheday.entities.Categoria;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.exceptions.EntidadeNaoEncontradaException;
import br.com.savetheday.exceptions.NegocioException;
import br.com.savetheday.repositories.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OngService {

    @Autowired
    OngRepository repository;

    @Autowired
    EnderecoService enderecoService;

    @Transactional( rollbackFor = Exception.class )
    public Ong save(Ong ong) {
        Ong validaCNPJ = repository.findByCnpj(ong.getCnpj());
        if(validaCNPJ != null){
            throw new RuntimeException("Cnpj ja cadastrado!");
        }
        Ong validaEmail = repository.findByEmail(ong.getEmail());
        if(validaEmail != null){
            throw new RuntimeException("Email ja cadastrado!");
        }

        Endereco endereco = enderecoService.findById(ong.getEndereco().getId());
        ong.setEndereco(endereco);

        return repository.save(ong);
    }

    public List<Ong> findAll() {
        return repository.findAll();
    }




}
