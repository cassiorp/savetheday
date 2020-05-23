package br.com.savetheday.services.impl;

import br.com.savetheday.dtos.EnderecoDto;
import br.com.savetheday.dtos.EnderecoDtoModel;
import br.com.savetheday.entities.*;
import br.com.savetheday.services.EnderecoService;
import br.com.savetheday.exceptions.EnderecoNaoConrrespondeException;
import br.com.savetheday.exceptions.EntidadeNaoEncontradaException;
import br.com.savetheday.repositories.EnderecoRepository;
import br.com.savetheday.exceptions.OngComEnderecoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EstadoServiceImpl estadoService;

    @Autowired
    private CidadeServiceImpl cidadeService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private OngServiceImpl ongService;

    @Transactional( rollbackFor = Exception.class )
    @Override
    public Endereco save(EnderecoDto enderecoDto, Integer id) {
        Ong ong = ongService.findById(id);
        if(ong.getEndereco() != null ){
            throw new OngComEnderecoException("Ong Com Enderço ja Cadastrado, Apenas permitido edição!");
        }
        Estado estado = estadoService.defineEstado(enderecoDto.getEstado());
        Cidade cidade = cidadeService.defineCidade(enderecoDto.getCidade(), estado);
        Endereco endereco = new Endereco(enderecoDto.getBairro(), enderecoDto.getRua(),
                    enderecoDto.getNumero(), enderecoDto.getCEP(), cidade, ong);

        ong.setEndereco(endereco);

        enderecoRepository.save(endereco);
        ongService.edit(id, ong);
        return endereco;
    }

    public Endereco findById(Integer id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado"));
    }

    @Override
    public List<EnderecoDtoModel> findAll() {
       return toCollectionModel(enderecoRepository.findAll());
    }

    @Transactional( rollbackFor = Exception.class )
    @Override
    public Endereco edit(EnderecoDto enderecoDto, Integer idOng, Integer idEnd) {
        Endereco endVerifica = this.findById(idEnd);
        if(!endVerifica.getOng().getId().equals(idOng)){
            throw new EnderecoNaoConrrespondeException("Id da Ong não conrresponde ao do endereco");
        }

        Endereco endereco = this.setEndereco(enderecoDto, idOng);
        Estado estado = estadoService.defineEstado(enderecoDto.getEstado());
        Cidade cidade = cidadeService.defineCidade(enderecoDto.getCidade(), estado);
        endereco.setCidade(cidade);
        return enderecoRepository.save(endereco);
    }

    @Transactional( rollbackFor = Exception.class )
    @Override
    public Boolean delete(Integer idOng, Integer idEnd){
        Endereco endVerifica = this.findById(idEnd);
        if(!endVerifica.getOng().getId().equals(idOng)){
            throw new EnderecoNaoConrrespondeException("Id da Ong não conrresponde ao do endereco");
        }
        enderecoRepository.deleteById(idEnd);
        if(ifExists(idEnd)){
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
    private Endereco setEndereco (EnderecoDto enderecoDto, Integer id) {
        Endereco endereco = this.findById(id);
        endereco.setId(id);
        endereco.setBairro(enderecoDto.getBairro());
        endereco.setRua(enderecoDto.getRua());
        endereco.setNumero(enderecoDto.getNumero());
        endereco.setCEP(enderecoDto.getCEP());
        return endereco;
    }
}
