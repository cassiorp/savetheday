package br.com.savetheday.services;

import br.com.savetheday.dtos.*;
import br.com.savetheday.entities.Caso;;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.entities.enums.StatusCaso;
import br.com.savetheday.repositories.CasoRepository;
import br.com.savetheday.services.exceptions.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CasoService {
    @Autowired
    CasoRepository repository;

    @Autowired
    OngService ongService;

    @Autowired
    SendEmailService sendEmailService;

    @Transactional( rollbackFor = Exception.class )
    public Caso save(CasoDto dto){
        Ong ong = ongService.findById(dto.getIdOng());
        String cidade = ong.getEndereco() != null ? ong.getEndereco().getCidade().getNome() : null;
        Caso caso = new Caso(null, dto.getTitulo(), dto.getDescricao(), dto.getTotal(), 0.0,StatusCaso.ABERTO,cidade ,ong);

        ong.getCasos().add(caso);

        repository.save(caso);
        ongService.edit(dto.getIdOng(), ong);
        return caso;
    }

    public List<CasoDtoModel> findAll(){
        return toCollectionModel(repository.findAll());
    }


    public Caso findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Caso não encontrado"));
    }

    @Transactional( rollbackFor = Exception.class )
    public Boolean delete(Integer id){
        Caso caso = this.findById(id);
        repository.deleteById(id);
        if(ifExists(id)){
            return false;
        }
        return true;
    }

    @Transactional( rollbackFor = Exception.class )
    public Caso update(CasoDto dto, Integer id) {
        Caso obj = fromDto(dto);
        Caso newObj = this.findById(id);
        this.updateData(newObj, obj);
        return repository.save(newObj);
    }

    public List<CasoDtoModel> filter( String cidade ){
        return toCollectionModel(repository.findByCidadeContaining(cidade));
    }

    @Transactional( rollbackFor = Exception.class )
    public String doacao( ValorDoadoDto dto, Integer id){
        Caso caso = this.findById(id);
        Double valor = dto.getValor();
        Ong ong = ongService.findById(caso.getOng().getId());
        if(valor > 0){
            caso.setColetado(caso.getColetado() + valor);
            caso.setId(id);
            repository.save(caso);

            if(caso.getColetado().equals(caso.getTotal())){
                sendEmailService.enviar(ong.getEmail(), assunto(caso.getTitulo()),mensagem(caso.getTotal()));
                Double total = caso.getTotal();
                this.delete(id);
                doneCaso(total);
            }
        }
                return "Doado: " + NumberFormat.getCurrencyInstance().format(valor);
    }


    private void updateData(Caso newObj, Caso obj) {
        newObj.setTitulo(obj.getTitulo() != null ? obj.getTitulo() : newObj.getTitulo());
        newObj.setDescricao(obj.getDescricao() != null ? obj.getDescricao() : newObj.getDescricao());
        newObj.setTotal(obj.getTotal() != null ? obj.getTotal() : newObj.getTotal());
        newObj.setStatus(obj.getStatus() != null ? obj.getStatus() : newObj.getStatus());
    }

    public Boolean ifExists(Integer id) {
        return repository.existsById(id);
    }

    public List<CasoDtoModel> toCollectionModel(List<Caso> casos) {
        return casos.stream()
                .map(caso -> toModel(caso))
                .collect(Collectors.toList());
    }

    public List<CasoDtoModelToOng> toCollectionModelOng(List<Caso> casos) {
        return casos.stream()
                .map(caso -> toModelOng(caso))
                .collect(Collectors.toList());
    }

    public CasoDtoModel toModel(Caso caso) {
        if(caso == null){
            return null;
        }
        CasoDtoModel model = new CasoDtoModel(caso.getId(), caso.getTitulo(), caso.getDescricao(),NumberFormat.getCurrencyInstance().format(caso.getTotal()),
                                                NumberFormat.getCurrencyInstance().format(caso.getColetado()), caso.getStatus().toString(),ongService.toModelCaso(caso.getOng()));
        return model;
    }

    public CasoDtoModelToOng toModelOng(Caso caso) {
        if(caso == null){
            return null;
        }
        CasoDtoModelToOng model = new CasoDtoModelToOng(caso.getId(), caso.getTitulo(), caso.getDescricao(),NumberFormat.getCurrencyInstance().format(caso.getTotal()),
                NumberFormat.getCurrencyInstance().format(caso.getColetado()), caso.getStatus().toString());
        return model;
    }

    public Caso fromDto(CasoDto dto) {
        Ong ong = ongService.findById(dto.getIdOng());
        return new Caso(
                null, dto.getTitulo(), dto.getDescricao(), dto.getTotal(),dto.getStatusCaso() ,ong
        );
    }

    private String assunto( String titulo ) {
        return "Caso " + titulo + " encerrado";
    }
    private String mensagem( Double total ) {
        String totalFormatado = NumberFormat.getCurrencyInstance().format(total);
        return "Gostariamos de informar que conseguimos alcançar o valor desejado! " +
                "O total arrecadado para seu caso foi de " + totalFormatado + "!" +
                " Seu caso foi deletado da sua lista da casos, mas você pode cadastras quantos casos precisar.";
    }
    private String doneCaso( Double total ){
        String totalFormatado = NumberFormat.getCurrencyInstance().format(total);
        return "Caso Encerrado, Valor total de "+totalFormatado+" foi atingido com sucesso!";
    }
    
}


