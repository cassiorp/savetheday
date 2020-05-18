package br.com.savetheday.services;

import br.com.savetheday.dtos.CasoDto;
import br.com.savetheday.dtos.OngDto;
import br.com.savetheday.entities.Caso;
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
public class CasoServiceTest {

    @Autowired
    CasoService service;

    @Autowired
    OngService ongService;

    @Test
    void save(){
        OngDto ongDto = new OngDto("Ong name", "sigla", LocalDate.now(), "63.097.084/0001-43", "foto.jpg", "99999", "mail@mail", "senha123456", Categoria.MEIO_AMBIENTE);
        Ong ong =  ongService.save(ongDto);

        CasoDto dto = new CasoDto("Titulo", "descricao",20.0, ong.getId());
        Caso caso = service.save(dto);

        assertNotNull(caso.getId());

    }

    @Test
    void findById(){
        OngDto ongDto = new OngDto("Ong name", "sigla", LocalDate.now(), "73.470.264/0001-82", "foto.jpg", "99999", "mail2@mail", "senha123456", Categoria.MEIO_AMBIENTE);
        Ong ong =  ongService.save(ongDto);

        CasoDto dto = new CasoDto("Titulo", "descricao",20.0, ong.getId());
        Caso caso = service.save(dto);

        Caso caso2 = service.findById(caso.getId());

        assertEquals(caso.getId(), caso2.getId());
        assertEquals(caso.getDescricao(),caso2.getDescricao());
    }

    @Test
    void delete(){
        OngDto ongDto = new OngDto("Ong name", "sigla", LocalDate.now(), "42.165.812/0001-37", "foto.jpg", "99999", "mail11@mail", "senha123456", Categoria.MEIO_AMBIENTE);
        Ong ong =  ongService.save(ongDto);

        CasoDto dto = new CasoDto("Titulo", "descricao",20.0, ong.getId());
        Caso caso = service.save(dto);

        Boolean seDeletou = service.delete(caso.getId());

        assertEquals(true, seDeletou);

    }
}
