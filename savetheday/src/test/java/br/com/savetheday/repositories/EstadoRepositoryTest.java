package br.com.savetheday.repositories;


import br.com.savetheday.entities.Estado;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("teste")
public class EstadoRepositoryTest {

    @Autowired
    private EstadoRepository repository;

    @Test
    public void insert() {
        Estado estado = new Estado();
        estado.setId(null);
        estado.setNome("Estado 1");
        estado = this.repository.save(estado);
        assertEquals(2, estado.getId());
    }

    @Test
    void shouldFindName() {
        Estado estado1 = new Estado( "RJ");
        repository.save(estado1);
        Estado estado2 = repository.findByNome(estado1.getNome());
        assertEquals(estado1.getNome(),estado2.getNome());
    }

    @AfterEach
    void tearDown() {
        this.repository.deleteAll();
    }

}
