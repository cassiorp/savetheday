package br.com.savetheday.services;

import br.com.savetheday.dtos.EnderecoDtoInput;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.repositories.EnderecoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EnderecoServiceTest {

    @Autowired
    EnderecoService service;
    @Autowired
    EnderecoRepository repository;

    @Test
    void save(){
        EnderecoDtoInput enderecoDtoInput = new EnderecoDtoInput("RS", "Charqueadas",
                "Centro", "Boadeseviver", "123", "9674500");

        Endereco endereco = service.save(enderecoDtoInput);

        assertNotNull(endereco.getId());
        assertEquals("Centro", endereco.getBairro());
    }

    @Test
    void ifFindById(){
        EnderecoDtoInput enderecoDtoInput = new EnderecoDtoInput("RS", "Charqueadas",
                "Centro", "Boadeseviver", "123", "9674500");

        Endereco endereco = service.save(enderecoDtoInput);
        Endereco find = service.findById(endereco.getId());

        assertEquals(find.getCEP(), endereco.getCEP());
    }

    @Test
    void edit(){
        EnderecoDtoInput enderecoDtoInput = new EnderecoDtoInput("RS", "Charqueadas",
                "Centro", "Boadeseviver", "123", "9674500");

        EnderecoDtoInput endEdit = new EnderecoDtoInput("RS", "HHHHHHHH",
                "Centro", "RRRRR", "123", "9674500");

        Endereco endereco = service.save(enderecoDtoInput);

        endereco = service.edit(endEdit, endereco.getId());

        assertEquals(1, repository.count());
    }



    @AfterEach
    void tearDown() {
        this.repository.deleteAll();
    }
}
