package br.com.savetheday.services.impl;

import br.com.savetheday.dtos.ContaDto;
import br.com.savetheday.dtos.ContaDtoModel;
import br.com.savetheday.entities.Conta;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.repositories.ContaRepository;
import br.com.savetheday.repositories.OngRepository;
import br.com.savetheday.services.ContaService;
import br.com.savetheday.exceptions.ContaNaoConrrespondeException;
import br.com.savetheday.exceptions.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private OngServiceImpl ongService;

    @Autowired
    private OngRepository ongRepository;

    @Transactional( rollbackFor = Exception.class )
    @Override
    public Conta save(ContaDto dto, Integer id) {
        Ong ong = ongService.findById(id);
        Conta conta = new Conta(dto.getNomeBanco(), dto.getAgencia(),
                                    dto.getNumero(),dto.getDigito(), ong);

        ong.getContas().add(conta);

        repository.save(conta);
        ongService.edit(id, ong);
        return conta;
    }

    @Override
    public List<ContaDtoModel> findAll() {
        return toCollectionModel(repository.findAll());
    }

    public Conta findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Conta não encontrada"));
    }

    @Transactional( rollbackFor = Exception.class )
    @Override
    public Boolean delete(Integer idOng, Integer idConta){
        Ong ong = ongService.findById(idOng);
        Conta contaVerifica = this.findById(idConta);
        if(!contaVerifica.getOng().getId().equals(idOng)){
            throw new ContaNaoConrrespondeException("Conta não conrresponde a Ong!");
        }
        repository.deleteById(idConta);
        if(ifExists(idConta)){
            return false;
        }
        return true;
    }

    @Transactional( rollbackFor = Exception.class )
    @Override
    public Conta update(ContaDto dto, Integer idOng, Integer idConta) {
        Ong ong = ongService.findById(idOng);
        Conta contaVerifica = this.findById(idConta);
        if(!contaVerifica.getOng().getId().equals(idOng)){
            throw new ContaNaoConrrespondeException("Conta não conrresponde a Ong!");
        }

        Conta obj = fromDto(dto, idOng);
        Conta newObj = this.findById(idConta);
        this.updateData(newObj, obj);
        return repository.save(newObj);
    }

    private void updateData(Conta newObj, Conta obj) {
        newObj.setNomeBanco(obj.getNomeBanco() != null ? obj.getNomeBanco() : newObj.getNomeBanco());
        newObj.setAgencia(obj.getAgencia() != null ? obj.getAgencia() : newObj.getAgencia());
        newObj.setNumero(obj.getNumero() != null ? obj.getNumero() : newObj.getNumero());
        newObj.setDigito(obj.getDigito() != null ? obj.getDigito() : newObj.getDigito());
    }

    public Boolean ifExists(Integer id) {
        return repository.existsById(id);
    }

    public List<ContaDtoModel> toCollectionModel(List<Conta> contas) {
        return contas.stream()
                .map(conta -> toModel(conta))
                .collect(Collectors.toList());
    }
    public ContaDtoModel toModel(Conta conta) {
        if(conta == null){
            return null;
        }
        ContaDtoModel model = new ContaDtoModel(conta.getId(), conta.getNomeBanco(), conta.getAgencia(), conta.getNumero(), conta.getDigito());
        return model;
    }

    public Conta fromDto(ContaDto dto, Integer id) {
        Ong ong = ongService.findById(id);
        return new Conta(
                dto.getNomeBanco(), dto.getAgencia(),
                dto.getNumero(), dto.getDigito(), ong
        );
    }
}
