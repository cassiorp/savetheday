package br.com.savetheday.services;

import br.com.savetheday.dtos.EnderecoDtoInput;
import br.com.savetheday.dtos.EnderecoDtoModel;
import br.com.savetheday.entities.Cidade;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.entities.Estado;
import br.com.savetheday.exceptions.EntidadeNaoEncontradaException;
import br.com.savetheday.repositories.CidadeRepository;
import br.com.savetheday.repositories.EnderecoRepository;
import br.com.savetheday.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional( rollbackFor = Exception.class )
    public Endereco save(EnderecoDtoInput enderecoDtoInput) {
        Estado estado = estadoService.defineEstado(enderecoDtoInput.getEstado());
        Cidade cidade = cidadeService.defineCidade(enderecoDtoInput.getCidade(), estado);
        Endereco endereco = new Endereco(null, enderecoDtoInput.getBairro(), enderecoDtoInput.getRua(),
                    enderecoDtoInput.getNumero(), enderecoDtoInput.getCEP(), cidade);

        return enderecoRepository.save(endereco);
    }

    public Endereco findById(Integer id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado"));
    }

    public List<EnderecoDtoModel> findAll() {
       return toCollectionModel(enderecoRepository.findAll());
    }

    @Transactional( rollbackFor = Exception.class )
    public Endereco edit(EnderecoDtoInput enderecoDtoInput, Integer id) {
        if(!enderecoRepository.existsById(id)){
            throw new RuntimeException("Endereço Não encontrado");
        }
        Endereco endereco = this.setEndereco(enderecoDtoInput, id);
        Estado estado = estadoService.defineEstado(enderecoDtoInput.getEstado());
        Cidade cidade = cidadeService.defineCidade(enderecoDtoInput.getCidade(), estado);
        endereco.setCidade(cidade);
        return enderecoRepository.save(endereco);
    }

    @Transactional( rollbackFor = Exception.class )
    public void delete(Integer id){
        enderecoRepository.deleteById(id);
    }

    public Boolean ifExists(Integer id){
        return enderecoRepository.existsById(id);
    }

    public List<EnderecoDtoModel> toCollectionModel(List<Endereco> enderecos) {
        return enderecos.stream()
                .map(endereco -> toModel(endereco))
                .collect(Collectors.toList());
    }
    public EnderecoDtoModel toModel(Endereco endereco) {
        EnderecoDtoModel model = new EnderecoDtoModel(endereco.getId(), endereco.getBairro(),
                                                        endereco.getRua(), endereco.getNumero(),
                                                        endereco.getCEP(), endereco.getCidade().getNome(),
                                                        endereco.getCidade().getEstado().getNome());

        return model;
    }
    private Endereco setEndereco ( EnderecoDtoInput enderecoDtoInput,Integer id) {
        Endereco endereco = this.findById(id);
        endereco.setId(id);
        endereco.setBairro(enderecoDtoInput.getBairro());
        endereco.setRua(enderecoDtoInput.getRua());
        endereco.setNumero(enderecoDtoInput.getNumero());
        endereco.setCEP(enderecoDtoInput.getCEP());
        return endereco;
    }


}
