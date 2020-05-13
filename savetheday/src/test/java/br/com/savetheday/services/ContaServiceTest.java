package br.com.savetheday.services;

import br.com.savetheday.dtos.ContaDto;
import br.com.savetheday.dtos.EnderecoDtoInput;
import br.com.savetheday.entities.Conta;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.entities.enums.Categoria;
import br.com.savetheday.repositories.ContaRepository;
import br.com.savetheday.repositories.EnderecoRepository;
import br.com.savetheday.repositories.OngRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ContaServiceTest {

    @Autowired
    EnderecoService enderecoService;
    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ContaService service;

    @Autowired
    ContaRepository repository;

    @Autowired
    OngService ongService;

    @Autowired
    OngRepository ongRepository;


    @Test
    void sePersistConta(){
        Ong ong = new Ong(null, "AAPE", "APE", LocalDate.now(), "55.603.575/0001-90", "foto.png", "99999",
                "email@mail", "senha", Categoria.CULTURA);

        ongRepository.save(ong);

        EnderecoDtoInput enderecoDtoInput = new EnderecoDtoInput("RS", "Charqueadas",
                "Centro", "Boadeseviver", "123", "9674500", 1);

        Endereco endereco = enderecoService.save(enderecoDtoInput);

        ContaDto conta = new ContaDto("Banco do Brasil","1159930","0509" ,"02");

        service.save(conta, ong.getId());

        assertEquals(1, ong.getContas().size());
    }
}
