package br.com.savetheday.services;

import br.com.savetheday.dtos.EnderecoDtoInput;
import br.com.savetheday.dtos.EnderecoDtoModel;
import br.com.savetheday.entities.*;
import br.com.savetheday.services.exceptions.EntidadeNaoEncontradaException;
import br.com.savetheday.repositories.EnderecoRepository;
import br.com.savetheday.services.exceptions.OngComEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private OngService ongService;

    @Transactional( rollbackFor = Exception.class )
    public Endereco save(EnderecoDtoInput enderecoDtoInput) {
        Ong ong = ongService.findById(enderecoDtoInput.getIdOng());
        if(ong.getEndereco() != null ){
            throw new OngComEndereco("Ong Com Enderço ja Cadastrado, Apenas permitido edição!");
        }
        Estado estado = estadoService.defineEstado(enderecoDtoInput.getEstado());
        Cidade cidade = cidadeService.defineCidade(enderecoDtoInput.getCidade(), estado);
        Endereco endereco = new Endereco(null, enderecoDtoInput.getBairro(), enderecoDtoInput.getRua(),
                    enderecoDtoInput.getNumero(), enderecoDtoInput.getCEP(), cidade, ong);

        ong.setEndereco(endereco);

        enderecoRepository.save(endereco);
        ongService.edit(enderecoDtoInput.getIdOng(), ong);
        return endereco;
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
    public Boolean delete(Integer id){
        Endereco endereco = this.findById(id);
        endereco.setOng(null);
        enderecoRepository.deleteById(id);
        if(ifExists(id)){
            return false;
        }
        return true;
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
        if(endereco == null){
            return null;
        }
        EnderecoDtoModel model = new EnderecoDtoModel(endereco.getId(), endereco.getBairro(),
                                                        endereco.getRua(), endereco.getNumero(),
                                                        endereco.getCEP(), endereco.getCidade().getNome(),
                                                        endereco.getCidade().getEstado().getNome(), endereco.getOng().getId());

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
