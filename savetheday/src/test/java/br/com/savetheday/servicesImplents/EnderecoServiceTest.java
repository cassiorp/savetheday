package br.com.savetheday.servicesImplents;

import br.com.savetheday.dtos.EnderecoDto;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.entities.enums.Categoria;
import br.com.savetheday.repositories.EnderecoRepository;
import br.com.savetheday.repositories.OngRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EnderecoServiceTest {

    @Autowired
    EnderecoServiceImpl service;
    @Autowired
    EnderecoRepository repository;

    @Autowired
    OngRepository ongRepository;

    @Test
    void save(){
        Ong ong = new Ong(null, "AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);

        EnderecoDto enderecoDto = new EnderecoDto("RS", "Charqueadas",
                "Centro", "Boadeseviver", "123", "9674500", ong.getId());

        Endereco endereco = service.save(enderecoDto);

        assertNotNull(endereco.getId());
        assertEquals("Centro", endereco.getBairro());
        assertEquals(endereco.getOng().getId(), ong.getId());
    }


    @Test
    void ifFindById(){
        Ong ong = new Ong(null, "AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);

        EnderecoDto enderecoDto = new EnderecoDto("RS", "Charqueadas",
                "Centro", "Boadeseviver", "123", "9674500", ong.getId());

        Endereco endereco = service.save(enderecoDto);
        Endereco find = service.findById(endereco.getId());

        assertEquals(find.getCEP(), endereco.getCEP());
    }

    @Test
    void edit(){
        Ong ong = new Ong(null, "AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);

        EnderecoDto enderecoDto = new EnderecoDto("RS", "Charqueadas",
                "Centro", "Boadeseviver", "123", "9674500", ong.getId());

        EnderecoDto endEdit = new EnderecoDto("RS", "HHHHHHHH",
                "Centro", "RRRRR", "123", "9674500",ong.getId());

        Endereco endereco = service.save(enderecoDto);

        endereco = service.edit(endEdit, endereco.getId());

        assertEquals(1, repository.count());
    }



    @AfterEach
    void tearDown() {
        this.repository.deleteAll();
    }
}
