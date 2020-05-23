package br.com.savetheday.servicesImplents;

import br.com.savetheday.dtos.CasoDto;
import br.com.savetheday.dtos.OngDto;
import br.com.savetheday.dtos.ValorDoadoDto;
import br.com.savetheday.entities.Caso;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.entities.enums.Categoria;
import br.com.savetheday.services.impl.CasoServiceImpl;
import br.com.savetheday.services.impl.OngServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("teste_h2")
public class CasoServiceTest {

    @Autowired
    CasoServiceImpl service;

    @Autowired
    OngServiceImpl ongService;

    @Transactional
    @Test
    void save(){
        OngDto ongDto = new OngDto("Ong name", "sigla", LocalDate.now(), "63.097.084/0001-43", "foto.jpg", "99999", "mail@mail", "senha123456", Categoria.MEIO_AMBIENTE);
        Ong ong =  ongService.save(ongDto);

        CasoDto dto = new CasoDto("Titulo", "descricao",20.0, ong.getId());
        Caso caso = service.save(dto);

        assertNotNull(caso.getId());

    }

    @Transactional
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

        Boolean seDeletou = service.delete(1);

        assertEquals(true, seDeletou);

    }

    @Transactional
    @Test
    void doacao(){
        OngDto ongDto = new OngDto("Ong name", "sigla", LocalDate.now(), "62.218.204/0001-50", "foto.jpg", "99999", "mail151@mail", "senha123456", Categoria.MEIO_AMBIENTE);
        Ong ong =  ongService.save(ongDto);

        CasoDto dto = new CasoDto("Titulo", "descricao",20.0, ong.getId());
        Caso caso = service.save(dto);

        ValorDoadoDto valor = new ValorDoadoDto(10.50);
        String msg = service.doacao(valor, caso.getId());

        assertEquals(10.50, caso.getColetado());

    }
}
