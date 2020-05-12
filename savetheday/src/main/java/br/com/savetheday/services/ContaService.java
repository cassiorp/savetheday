package br.com.savetheday.services;

import br.com.savetheday.dtos.ContaDto;
import br.com.savetheday.entities.Conta;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.repositories.ContaRepository;
import br.com.savetheday.repositories.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private OngService ongService;

    @Autowired
    private OngRepository ongRepository;

    @Transactional( rollbackFor = Exception.class )
    public Conta save(ContaDto dto, Integer id) {
        Ong ong = ongService.findById(id);
        Conta conta = new Conta(null, dto.getNomeBanco(), dto.getAgencia(),dto.getNumero(),
                dto.getDigito(), ong);

        ong.getContas().add(conta);
        ong.setId(id);

        repository.save(conta);
        ongRepository.save(ong);
        return conta;

    }

    public Conta findById(Integer id) {
        Optional<Conta> conta = repository.findById(id);
        return conta.get();
    }

}
