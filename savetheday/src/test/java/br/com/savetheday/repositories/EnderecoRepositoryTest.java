package br.com.savetheday.repositories;

import br.com.savetheday.entities.Cidade;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.entities.Estado;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.entities.enums.Categoria;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("teste_h2")
public class EnderecoRepositoryTest {

    @Autowired
    EnderecoRepository repository;

    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    OngRepository ongRepository;

    @AfterEach
    void tearDown() {
        this.repository.deleteAll();
    }

    @Test
    void insert() {
        Ong ong = new Ong(null, "AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);
        Estado estado = new Estado(null, "RS");
        estadoRepository.save(estado);
        Cidade cidade = new Cidade(null, "Charqueadas", estado);
        cidadeRepository.save(cidade);

        Endereco endereco = new Endereco(null, "Centro", "Rua Boa Nova", "123", "96745000", cidade, ong);
        repository.save(endereco);

        assertNotNull(endereco.getId());
        assertEquals("Centro", endereco.getBairro());
        assertEquals("123", endereco.getNumero());
        assertEquals(endereco.getOng().getId(), ong.getId());
    }

    @Test
    void delete() {
        Ong ong = new Ong(null, "AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);
        Estado estado = new Estado(null, "RS");
        estadoRepository.save(estado);
        Cidade cidade = new Cidade(null, "Charqueadas", estado);
        cidadeRepository.save(cidade);

        Endereco endereco = new Endereco(null, "Centro", "Rua Boa Nova", "123", "96745000", cidade, ong);
        repository.save(endereco);

        Integer id = endereco.getId();
        repository.deleteById(id);

        assertEquals(1, repository.count());
    }


    @Test
    void upDate() {
        Ong ong = new Ong(null, "AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);
        Estado estado = new Estado(null, "RS");
        estadoRepository.save(estado);
        Cidade cidade = new Cidade(null, "Charqueadas", estado);
        cidadeRepository.save(cidade);

        Endereco endereco = new Endereco(null, "Centro", "Rua Boa Nova", "123", "96745000", cidade, ong);
        repository.save(endereco);
        Integer tamanhaAntes = Math.toIntExact(repository.count());


        endereco.setBairro("666");
        repository.save(endereco);
        Integer tamanhoDepois = Math.toIntExact(repository.count());

        assertEquals("666", endereco.getBairro());
        assertEquals(tamanhaAntes, tamanhoDepois);

    }

}
