package br.com.savetheday.repositories;

import br.com.savetheday.entities.Cidade;
import br.com.savetheday.entities.Estado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@ActiveProfiles("teste")
class CidadeRepositoryTest {

    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    EstadoRepository estadoRepository;


    @Test
    public void shouldPersistData() {
        Estado estado = new Estado( "RS");
        estadoRepository.save(estado);
        Cidade cidade = new Cidade( "Charqueadas", estado);

        cidadeRepository.save(cidade);

        assertNotNull(cidade);
        assertNotNull(cidade.getId());
        assertEquals("Charqueadas", cidade.getNome());
        assertEquals(estado, cidade.getEstado());
    }

    @Test
    void shouldFindName() {
        Estado estado = new Estado("RJ");
        estadoRepository.save(estado);

        Cidade cidade = new Cidade( "Charqueadas", estado);
        cidadeRepository.save(cidade);

        Cidade cidade2 = cidadeRepository.findByNome("Charqueadas");

        assertEquals(cidade.getNome(), cidade2.getNome());
    }

    @Test
    void shouldFindNameAndEstado() {
        Estado estado = new Estado("RJ");
        estadoRepository.save(estado);

        Cidade cidade = new Cidade("Charqueadas", estado);
        cidadeRepository.save(cidade);

        Cidade cidade2 = cidadeRepository.findByNomeAndEstado("Charqueadas", estado);

        assertEquals(cidade.getNome(), cidade2.getNome());
        assertEquals(cidade2.getEstado().getNome(), estado.getNome());
    }




    @BeforeEach
    void setUp() {
        cidadeRepository.deleteAll();
    }

}


