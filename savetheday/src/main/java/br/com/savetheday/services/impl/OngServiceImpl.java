package br.com.savetheday.services.impl;

import br.com.savetheday.dtos.OngDto;
import br.com.savetheday.dtos.OngDtoModel;
import br.com.savetheday.dtos.OngDtoModelToCaso;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.exceptions.NaoAutorizadaException;
import br.com.savetheday.repositories.OngRepository;
import br.com.savetheday.security.details.OngDetails;
import br.com.savetheday.services.OngService;
import br.com.savetheday.exceptions.CnpjCadastradoException;
import br.com.savetheday.exceptions.EmailCadastradoException;
import br.com.savetheday.exceptions.EntidadeNaoEncontradaException;
import br.com.savetheday.services.impl.util.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OngServiceImpl implements OngService {

    @Autowired
    private OngRepository repository;

    @Autowired
    private EnderecoServiceImpl enderecoService;

    @Autowired
    private ContaServiceImpl contaService;

    @Autowired
    private CasoServiceImpl casoService;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional( rollbackFor = Exception.class )
    @Override
    public Ong save(OngDto dto) {
        Ong ong = fromDto(dto);
        Ong validaCNPJ = repository.findByCnpj(ong.getCnpj());
        if(validaCNPJ != null){
            throw new CnpjCadastradoException("Cnpj ja cadastrado!");
        }
        Ong validaEmail = repository.findByEmail(ong.getEmail());
        if(validaEmail != null){
            throw new EmailCadastradoException("Email ja cadastrado!");
        }
        ong.setSenha(encoder.encode(ong.getSenha()));
        return repository.save(ong);
    }

    public Ong findById(Integer id) {
        OngDetails user = this.autenticado();
        if(!id.equals(user.getId())){
            throw new NaoAutorizadaException("Opereção não autorizada");
        }
        if(!repository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Ong não Encontrada");
        }
        Optional<Ong> ong = repository.findById(id);
        return ong.get();
    }

    @Override
    public OngDtoModel find(Integer id) {
        OngDtoModel dto = toModel(this.findById(id));
        if(dto == null){
            throw new EntidadeNaoEncontradaException("Entidade não encontrada!");
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
        this.findById(id);
        repository.deleteById(id);
    }

    @Transactional( rollbackFor = Exception.class )
    @Override
    public Ong update(OngDto dto, Integer id) {
        Ong newObj = findById(id);
        Ong obj = fromDto(dto);
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
                dto.getNome(),dto.getSigla() ,dto.getFundacao(),
                dto.getCnpj(),dto.getFoto() ,dto.getTelefone(), dto.getEmail(),
                dto.getSenha(),dto.getCategoria()
        );
    }

    @Override
    public URI uploadFoto(MultipartFile multipartFile, Integer id){
        Ong ong = this.findById(id);
        String uriString = ong.getNome() + "-" + ong.getId();
        URI uri = s3Service.uploadFile(multipartFile, uriString);
        ong.setFoto(uri.toString());
        repository.save(ong);
        return uri;
    }

    private OngDetails autenticado(){
        try {
            return (OngDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }

}
