package br.com.savetheday.services;

import br.com.savetheday.dtos.EnderecoDtoInput;
import br.com.savetheday.entities.Cidade;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.entities.Estado;
import br.com.savetheday.repositories.CidadeRepository;
import br.com.savetheday.repositories.EnderecoRepository;
import br.com.savetheday.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Endereco salvar(EnderecoDtoInput enderecoDtoInput) {
        Estado estado = new Estado();
        estado.setId(null);
        estado.setNome(enderecoDtoInput.getEstado());
        estado = estadoRepository.save(estado);

        Cidade cidade = new Cidade();
        cidade.setId(null);
        cidade.setNome(enderecoDtoInput.getCidade());
        cidade.setEstado(estado);
        cidade = cidadeRepository.save(cidade);

        Endereco endereco = new Endereco();
        endereco.setId(null);
        endereco.setBairro(enderecoDtoInput.getBairro());
        endereco.setRua(enderecoDtoInput.getRua());
        endereco.setNumero(enderecoDtoInput.getNumero());
        endereco.setCEP(enderecoDtoInput.getCEP());
        endereco.setCidade(cidade);
        return enderecoRepository.save(endereco);
    }

}
