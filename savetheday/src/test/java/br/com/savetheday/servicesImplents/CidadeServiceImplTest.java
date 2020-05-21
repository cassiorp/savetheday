package br.com.savetheday.servicesImplents;

import br.com.savetheday.entities.Cidade;
import br.com.savetheday.entities.Estado;
import br.com.savetheday.repositories.CidadeRepository;
import br.com.savetheday.repositories.EstadoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CidadeServiceImplTest {

    @Autowired
    CidadeServiceImpl service;
    @Autowired
    EstadoRepository repository;
    @Autowired
    CidadeRepository cidadeRepository;

    @Test
    void shouldDefineEstado() {
        Estado estado = new Estado(null,"Estado Bom Demais");
        repository.save(estado);
        Cidade cidade = service.defineCidade("CidadeTeste", estado);


        assertEquals("CidadeTeste", cidade.getNome());
    }

    @AfterEach
    void tearDown() {
        this.cidadeRepository.deleteAll();
        this.repository.deleteAll();
    }

}
