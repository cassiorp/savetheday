package br.com.savetheday.repositories;


import br.com.savetheday.entities.Estado;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EstadoRepositoryTest {

    @Autowired
    private EstadoRepository repository;

    @Test
    public void insert() {
        Estado estado = new Estado();
        estado.setId(null);
        estado.setNome("Rio Grande do Sul");
        estado = this.repository.save(estado);
        assertEquals(3, estado.getId());
    }

    @AfterEach
    void tearDown() {
        this.repository.deleteAll();
    }

}
