package br.com.savetheday.services;

import br.com.savetheday.dtos.OngDto;
import br.com.savetheday.dtos.OngDtoModel;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.repositories.OngRepository;
import br.com.savetheday.services.exceptions.CnpjCadastrado;
import br.com.savetheday.services.exceptions.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OngService {

    @Autowired
    OngRepository repository;

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    ContaService contaService;

    @Autowired
    CasoService casoService;

    @Transactional( rollbackFor = Exception.class )
    public Ong save(OngDto dto) {
        Ong ong = fromDto(dto);

        Ong validaCNPJ = repository.findByCnpj(ong.getCnpj());
        if(validaCNPJ != null){
            throw new CnpjCadastrado("Cnpj ja cadastrado!");
        }
        Ong validaEmail = repository.findByEmail(ong.getEmail());
        if(validaEmail != null){
            throw new RuntimeException("Email ja cadastrado!");
        }
        return repository.save(ong);
    }

    public Ong findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ong não encontrada"));

    }

    public OngDtoModel find(Integer id) {
        OngDtoModel dto = toModel(this.findById(id));
        if(dto == null){
            throw new EntidadeNaoEncontradaException("Entidade não encontrada!");
        }
        return dto;
    }

    public List<OngDtoModel> findAll() {
        return toCollectionModel(repository.findAll());
    }

    @Transactional( rollbackFor = Exception.class )
    public void delete(Integer id){
        repository.deleteById(id);
    }

    @Transactional( rollbackFor = Exception.class )
    public Ong update(OngDto dto, Integer id) {
        Ong obj = fromDto(dto);
        Ong newObj = findById(id);
        this.updateData(newObj, obj);
        return repository.save(newObj);
    }

    private void updateData(Ong newObj, Ong obj) {
        newObj.setFundacao(obj.getFundacao() != null ? obj.getFundacao() : newObj.getFundacao());
        newObj.setCnpj(obj.getCnpj() != null ? obj.getCnpj() : newObj.getCnpj());
        newObj.setTelefone(obj.getTelefone() != null  ? obj.getTelefone() : newObj.getTelefone());
        newObj.setEndereco(obj.getEndereco() != null ? obj.getEndereco() : newObj.getEndereco());
        newObj.setNome(obj.getNome() != null ? obj.getNome() : newObj.getNome());
        newObj.setSigla(obj.getSigla() != null ? obj.getSigla() : newObj.getSigla());
        newObj.setSenha(obj.getSenha() != null ? obj.getSenha() : newObj.getSenha());
        newObj.setFoto(obj.getFoto() != null ? obj.getFoto() : newObj.getFoto());
        newObj.setCategoria(obj.getCategoria() != null ? obj.getCategoria() : newObj.getCategoria());
        newObj.setEndereco(obj.getEndereco() != null ? obj.getEndereco() : newObj.getEndereco());
    }
    @Transactional( rollbackFor = Exception.class )
    public Ong edit(Integer id, Ong ong) {
        ong.setId(id);
        return repository.save(ong);
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
                ong.getCategoria().toString(),enderecoService.toModel(ong.getEndereco()),
                contaService.toCollectionModel(ong.getContas()), casoService.toCollectionModel(ong.getCasos())
        );
        return model;
    }

    public Ong fromDto(OngDto dto) {
        return new Ong(
                null, dto.getNome(),dto.getSigla() ,dto.getFundacao(),
                dto.getCnpj(),dto.getFoto() ,dto.getTelefone(), dto.getEmail(),
                dto.getSenha(),dto.getCategoria()
        );
    }
}
