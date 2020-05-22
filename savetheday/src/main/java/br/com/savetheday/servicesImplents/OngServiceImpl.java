package br.com.savetheday.servicesImplents;

import br.com.savetheday.dtos.OngDto;
import br.com.savetheday.dtos.OngDtoModel;
import br.com.savetheday.dtos.OngDtoModelToCaso;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.repositories.OngRepository;
import br.com.savetheday.services.OngService;
import br.com.savetheday.servicesImplents.exceptions.CnpjCadastrado;
import br.com.savetheday.servicesImplents.exceptions.EmailCadastrado;
import br.com.savetheday.servicesImplents.exceptions.EntidadeNaoEncontrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OngServiceImpl implements OngService {

    @Autowired
    OngRepository repository;

    @Autowired
    EnderecoServiceImpl enderecoService;

    @Autowired
    ContaServiceImpl contaService;

    @Autowired
    CasoServiceImpl casoService;

    @Transactional( rollbackFor = Exception.class )
    @Override
    public Ong save(OngDto dto) {
        Ong ong = fromDto(dto);

        Ong validaCNPJ = repository.findByCnpj(ong.getCnpj());
        if(validaCNPJ != null){
            throw new CnpjCadastrado("Cnpj ja cadastrado!");
        }
        Ong validaEmail = repository.findByEmail(ong.getEmail());
        if(validaEmail != null){
            throw new EmailCadastrado("Email ja cadastrado!");
        }
        return repository.save(ong);
    }

    public Ong findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontrada("Ong não encontrada"));

    }

    @Override
    public OngDtoModel find(Integer id) {
        OngDtoModel dto = toModel(this.findById(id));
        if(dto == null){
            throw new EntidadeNaoEncontrada("Entidade não encontrada!");
        }
        return dto;
    }

    @Override
    public List<OngDtoModel> findAll() {
        return toCollectionModel(repository.findAll());
    }

    @Transactional( rollbackFor = Exception.class )
    @Override
    public void delete(Integer id){
        repository.deleteById(id);
    }

    @Transactional( rollbackFor = Exception.class )
    @Override
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

    public List<OngDtoModelToCaso> toCollectionModelCaso(List<Ong> ongs) {
        return ongs.stream()
                .map(ong -> toModelCaso(ong))
                .collect(Collectors.toList());
    }

    public OngDtoModel toModel(Ong ong) {

        OngDtoModel model = new OngDtoModel(
                ong.getId(), ong.getNome(), ong.getSigla(),
                ong.getFundacao() == null ? null : ong.getFundacao().toString(),
                ong.getCnpj(), ong.getFoto(),
                ong.getTelefone(), ong.getEmail(), ong.getSenha(),
                ong.getCategoria().toString(),enderecoService.toModel(ong.getEndereco()),
                contaService.toCollectionModel(ong.getContas()), casoService.toCollectionModelOng(ong.getCasos())
        );
        return model;
    }

    public OngDtoModelToCaso toModelCaso(Ong ong) {

        OngDtoModelToCaso model = new OngDtoModelToCaso(
                ong.getId(), ong.getNome(), ong.getSigla(),
                ong.getFundacao() == null ? null : ong.getFundacao().toString(),
                ong.getCnpj(), ong.getFoto(),ong.getTelefone(), ong.getEmail(),
                ong.getCategoria().toString(),enderecoService.toModel(ong.getEndereco()),
                contaService.toCollectionModel(ong.getContas())
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
