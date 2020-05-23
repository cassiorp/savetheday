package br.com.savetheday.repositories;


import br.com.savetheday.entities.Ong;
import br.com.savetheday.entities.enums.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("teste_h2")
public class OngRepositoryTest {

    @Autowired
    OngRepository repository;


    @Test
    void insert(){
        Ong ong = new Ong("AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        repository.save(ong);

        assertNotNull(ong.getId());
        assertEquals("AAPAGE", ong.getNome());
    }

    @Test
    void update(){
        Ong ong = new Ong("AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        repository.save(ong);
        Integer tamanhoAntes = Math.toIntExact(repository.count());

        ong.setNome("Ong Editada");
        repository.save(ong);
        Integer tamanhoDepois = Math.toIntExact(repository.count());

        assertEquals(tamanhoAntes, tamanhoDepois);

    }

    @Test
    void delete(){
        Ong ong = new Ong("AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        repository.save(ong);

        repository.deleteById(ong.getId());

        assertEquals(0, repository.count());
    }

}
