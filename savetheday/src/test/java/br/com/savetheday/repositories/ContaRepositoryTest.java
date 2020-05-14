package br.com.savetheday.repositories;


import br.com.savetheday.entities.Conta;
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
public class ContaRepositoryTest {


    @Autowired
    ContaRepository repository;

    @Autowired
    OngRepository ongRepository;


    @Test
    void insert(){
        Ong ong = new Ong(null, "AAPAGE", "APAE", LocalDate.now(),"55.921.638/0001-57", "foto.png", "999999","mail@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);

        Conta conta = new Conta(null, "Banrisul", "0509","3955555","2",ong);
        repository.save(conta);

        assertNotNull(ong.getId());
        assertNotNull(conta.getId());
        assertEquals("Banrisul", conta.getNomeBanco());
    }

    @Test
    void update(){
        Ong ong = new Ong(null, "AAPAGE", "APAE", LocalDate.now(),"28.883.365/0001-20", "foto.png", "999999","mail2@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);

        Conta conta = new Conta(null, "Banrisul", "0509","3955555","2",ong);
        repository.save(conta);
        Integer tamanhoAntes = Math.toIntExact(repository.count());

        conta.setNomeBanco("Banco do Brasil");
        Integer tamanhoDepois = Math.toIntExact(repository.count());

        assertEquals(tamanhoAntes, tamanhoDepois);
        assertEquals("Banco do Brasil", conta.getNomeBanco());
    }


    @Test
    void delete(){
        Ong ong = new Ong(null, "AAPAGE", "APAE", LocalDate.now(),"77.720.160/0001-30", "foto.png", "999999","mail3@mail","senha123456", Categoria.CULTURA);
        ongRepository.save(ong);

        Conta conta = new Conta(null, "Banrisul", "0509","3955555","2",ong);
        repository.save(conta);

        repository.deleteById(conta.getId());

        assertEquals(0, repository.count());
    }
}
