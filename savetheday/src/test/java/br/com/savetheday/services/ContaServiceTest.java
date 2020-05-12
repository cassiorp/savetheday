package br.com.savetheday.services;

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
        EnderecoDtoInput enderecoDtoInput = new EnderecoDtoInput("RS", "Charqueadas",
                "Centro", "Boadeseviver", "123", "9674500");

        Endereco endereco = enderecoService.save(enderecoDtoInput);

        Ong ong = new Ong(null, "AAPE", "APE", new Date(), "55.603.575/0001-90", "foto.png", "99999",
                "email@mail", "senha", Categoria.CULTURA, endereco, null);

        ongService.save(ong);

        Conta conta = new Conta(null, "Banco do Brasil", "0509", "1159930", "02", ong);

        service.save(conta);

        assertEquals(1, ong.getContas().size());
    }
}
