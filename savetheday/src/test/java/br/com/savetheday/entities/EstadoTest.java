package br.com.savetheday.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EstadoTest {

    @Test
    void testeGetId() {
        Estado estado = new Estado();
        estado.setId(1);

        Assertions.assertEquals(1, estado.getId());
    }


    @Test
    void testGetNome() {
        Estado estado = new Estado();
        estado.setNome("Rio Grande do Sul");

        Estado estado2 = new Estado();
        estado2.setNome("Rio de Janeiro");


        Assertions.assertEquals("Rio Grande do Sul", estado.getNome());
        Assertions.assertEquals("Rio de Janeiro", estado2.getNome());
    }

}
