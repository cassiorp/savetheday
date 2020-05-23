package br.com.savetheday.servicesImplents;

import br.com.savetheday.dtos.EnderecoDto;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.entities.enums.Categoria;
import br.com.savetheday.repositories.EnderecoRepository;
import br.com.savetheday.repositories.OngRepository;
import br.com.savetheday.services.impl.EnderecoServiceImpl;
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
        Ong ong = new Ong( "AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);

        EnderecoDto enderecoDto = new EnderecoDto("RS", "Charqueadas",
                "Centro", "Boadeseviver", "123", "9674500");

        Endereco endereco = service.save(enderecoDto, ong.getId());

        assertNotNull(endereco.getId());
        assertEquals("Centro", endereco.getBairro());
        assertEquals(endereco.getOng().getId(), ong.getId());
    }


    @Test
    void ifFindById(){
        Ong ong = new Ong("AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);

        EnderecoDto enderecoDto = new EnderecoDto("RS", "Charqueadas",
                "Centro", "Boadeseviver", "123", "9674500");

        Endereco endereco = service.save(enderecoDto, ong.getId());
        Endereco find = service.findById(endereco.getId());

        assertEquals(find.getCEP(), endereco.getCEP());
    }

    @Test
    void edit(){
        Ong ong = new Ong( "AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);

        EnderecoDto enderecoDto = new EnderecoDto("RS", "Charqueadas",
                "Centro", "Boadeseviver", "123", "9674500");

        EnderecoDto endEdit = new EnderecoDto("RS", "HHHHHHHH",
                "Centro", "RRRRR", "123", "9674500");

        Endereco endereco = service.save(enderecoDto, ong.getId());

        endereco = service.edit(endEdit,ong.getId() ,endereco.getId());

        assertEquals(1, repository.count());
    }



    @AfterEach
    void tearDown() {
        this.repository.deleteAll();
    }
}
