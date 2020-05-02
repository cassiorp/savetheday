package br.com.savetheday.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CidadeTest {

    @Test
    void testeGetId() {
        Cidade cidade = new Cidade();
        cidade.setId(1);

        Assertions.assertEquals(1, cidade.getId());
    }



    @Test
    void testGetNome() {
        Cidade cidade = new Cidade();
        cidade.setNome("Charqueadas");

        Cidade cidade2 = new Cidade();
        cidade2.setNome("Porto Alegre");


        Assertions.assertEquals("Charqueadas", cidade.getNome());
        Assertions.assertEquals("Porto Alegre", cidade2.getNome());
    }

    @Test
    void testGetEstado() {
        Estado estado = new Estado();
        Cidade cidade = new Cidade();
        cidade.setEstado(estado);


        Assertions.assertEquals(estado, cidade.getEstado());
    }
}
