package br.com.savetheday.repositories;

import br.com.savetheday.entities.Cidade;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.entities.Estado;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("teste")
public class EnderecoRepositoryTest {

    @Autowired
    EnderecoRepository repository;

    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    CidadeRepository cidadeRepository;

    @Test
    void insert() {
        Estado estado = new Estado(null, "RS");
        estadoRepository.save(estado);
        Cidade cidade = new Cidade(null, "Charqueadas", estado);
        cidadeRepository.save(cidade);

        Endereco endereco = new Endereco(null, "Centro", "Rua Boa Nova", "123", "96745000", cidade);
        repository.save(endereco);

        assertNotNull(endereco.getId());
        assertEquals("Centro", endereco.getBairro());
        assertEquals("123", endereco.getNumero());
    }


    @Test
    void upDate() {
        Estado estado = new Estado(null, "RS");
        estadoRepository.save(estado);
        Cidade cidade = new Cidade(null, "Charqueadas", estado);
        cidadeRepository.save(cidade);

        Endereco endereco = new Endereco(null, "Centro", "Rua Boa Nova", "123", "96745000", cidade);
        repository.save(endereco);

        Integer id = endereco.getId();
        endereco.setNumero("666");

        assertEquals(3, endereco.getId());
        assertEquals(1, repository.count());
    }

    @Test
    void delete() {
        Estado estado = new Estado(null, "RS");
        estadoRepository.save(estado);
        Cidade cidade = new Cidade(null, "Charqueadas", estado);
        cidadeRepository.save(cidade);

        Endereco endereco = new Endereco(null, "Centro", "Rua Boa Nova", "123", "96745000", cidade);
        repository.save(endereco);

        Integer id = endereco.getId();
        repository.deleteById(id);

        assertEquals(0, repository.count());
    }


    @AfterEach
    void tearDown() {
        this.repository.deleteAll();
    }
}
