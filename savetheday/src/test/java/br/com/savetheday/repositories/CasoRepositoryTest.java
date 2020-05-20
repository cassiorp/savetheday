package br.com.savetheday.repositories;

import br.com.savetheday.entities.Caso;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.entities.enums.Categoria;
import br.com.savetheday.entities.enums.StatusCaso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.Table;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("teste")
public class CasoRepositoryTest {

    @Autowired
    CasoRepository repository;

    @Autowired
    OngRepository ongRepository;


    @Test
    void insert(){
        Ong ong = new Ong(null, "AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);

        Caso caso = new Caso(null,"Caso teste", "descricao teste", 100.0, 0.0, StatusCaso.ABERTO,ong.getEndereco().getCidade().getNome() ,ong);
        repository.save(caso);

        assertNotNull(ong.getId());
        assertNotNull(caso.getId());
        assertEquals("Caso teste", caso.getTitulo());

    }

    @Test
    void update(){
        Ong ong = new Ong(null, "AAPAGE", "APAE", LocalDate.now(),"99.315.223/0001-05", "foto.png", "999999","mail2@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);

        Caso caso = new Caso(null,"Caso teste", "descricao teste", 100.0, 0.0, StatusCaso.ABERTO,ong.getEndereco().getCidade().getNome() ,ong);
        repository.save(caso);
        Integer tamanhoAntes = Math.toIntExact(repository.count());

        caso.setDescricao("EDITADO");
        repository.save(caso);
        Integer tamanhoDepois = Math.toIntExact(repository.count());

        assertEquals(tamanhoAntes, tamanhoDepois);
        assertEquals("EDITADO", caso.getDescricao());

    }


    @Test
    void delete(){
        Ong ong = new Ong(null, "AAPAGE", "APAE", LocalDate.now(),"77.684.136/0001-92", "foto.png", "999999","mail22@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);

        Caso caso = new Caso(null,"Caso teste", "descricao teste", 100.0, 0.0, StatusCaso.ABERTO,ong.getEndereco().getCidade().getNome() ,ong);
        repository.save(caso);

        repository.deleteById(caso.getId());

        assertEquals(0, repository.count());
    }
}
