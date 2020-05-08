package br.com.savetheday.services;

import br.com.savetheday.dtos.OngDtoModel;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.repositories.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Ong findById(Integer id) {
        Optional<Ong> ong =  repository.findById(id);
        return ong.get();
    }

    public List<OngDtoModel> findAll() {
        return toCollectionModel(repository.findAll());
    }


    public List<OngDtoModel> toCollectionModel(List<Ong> ongs) {
        return ongs.stream()
                .map(ong -> toModel(ong))
                .collect(Collectors.toList());
    }
    public OngDtoModel toModel(Ong ong) {
        OngDtoModel model = new OngDtoModel(
                ong.getId(), ong.getNome(), ong.getSigla(),
                ong.getFundacao().toString(),ong.getCnpj(), ong.getFoto(),
                ong.getTelefone(), ong.getEmail(), ong.getSenha(),
                ong.getCategoria().toString(),enderecoService.toModel(ong.getEndereco())
        );

        return model;
    }

}
