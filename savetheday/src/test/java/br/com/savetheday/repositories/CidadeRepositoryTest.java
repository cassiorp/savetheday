package br.com.savetheday.repositories;


import br.com.savetheday.entities.Cidade;
import br.com.savetheday.entities.Estado;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CidadeRepositoryTest {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Test
    public void insert() {
        Estado estado = new Estado();
        estado.setId(null);
        estado.setNome("RS");
        estado = repository.save(estado);

        Cidade cidade = new Cidade();
        cidade.setId(null);
        cidade.setNome("Charqueadas");
        cidade.setEstado(estado);
        cidade = cidadeRepository.save(cidade);
        assertEquals(14, cidade.getId());
    }

    @AfterEach
    void tearDown() {
        this.repository.deleteAll();
        this.cidadeRepository.deleteAll();
    }

}

