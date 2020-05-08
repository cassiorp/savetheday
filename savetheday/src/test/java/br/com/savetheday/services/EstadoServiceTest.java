package br.com.savetheday.services;

import br.com.savetheday.entities.Estado;
import br.com.savetheday.repositories.EstadoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EstadoServiceTest {


    @Autowired
    EstadoService service;
    @Autowired
    EstadoRepository repository;

    @Test
    void shouldDefineEstado() {
        Estado estado = service.defineEstado("RS");
        assertEquals("RS", estado.getNome());
    }

    @AfterEach
    void tearDown() {
        this.repository.deleteAll();
    }

}
