package br.com.savetheday.services;

import br.com.savetheday.dtos.*;
import br.com.savetheday.entities.Caso;
import br.com.savetheday.entities.Conta;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.entities.enums.StatusCaso;
import br.com.savetheday.repositories.CasoRepository;
import br.com.savetheday.services.exceptions.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Caso save(CasoDto dto){
        Ong ong = ongService.findById(dto.getIdOng());
        Caso caso = new Caso(null, dto.getTitulo(), dto.getDescricao(), dto.getTotal(), 0.0,StatusCaso.ABERTO, ong);

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

    public String doacao( ValorDoadoDto dto, Integer id){
        Caso caso = this.findById(id);
        Double valor = dto.getValor();
        Ong ong = ongService.findById(caso.getOng().getId());
        if(caso.getTotal() >= valor){
            caso.setColetado(caso.getColetado() + valor);
            caso.setId(id);
            repository.save(caso);

            if(caso.getColetado().equals(caso.getTotal())){
                sendEmailService.enviar(ong.getEmail(), "Caso encerrado", "Gostariamos de informar que conseguimos alcançar o valor desejado! o Caso foi encerrado e deletado da lista de casos da sua ong!");
                Double total = caso.getTotal();
                this.delete(id);
                return "Caso Encerrado, Valor "+total+" foi atingido com sucesso!";
            }
        }else{
            return "Valor excedido";
        }
                return "Doado: "+valor;
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

    public CasoDtoModel toModel(Caso caso) {
        if(caso == null){
            return null;
        }
        CasoDtoModel model = new CasoDtoModel(caso.getId(), caso.getTitulo(), caso.getDescricao(), caso.getTotal().toString(),
                caso.getColetado().toString(), caso.getStatus().toString());
        return model;
    }

    public Caso fromDto(CasoDto dto) {
        Ong ong = ongService.findById(dto.getIdOng());
        return new Caso(
                null, dto.getTitulo(), dto.getDescricao(), dto.getTotal(),dto.getStatusCaso() ,ong
        );
    }
}


