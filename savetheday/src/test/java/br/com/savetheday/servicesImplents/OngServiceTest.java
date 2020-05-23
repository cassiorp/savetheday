package br.com.savetheday.servicesImplents;

import br.com.savetheday.dtos.OngDto;
import br.com.savetheday.dtos.OngDtoModel;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.entities.enums.Categoria;
import br.com.savetheday.repositories.OngRepository;
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
public class OngServiceTest {

    @Autowired
    OngServiceImpl ongService;

    @Autowired
    OngRepository ongRepository;

    @Test
    void save(){
        OngDto ongDto = new OngDto("Ong name", "sigla", LocalDate.now(), "63.097.084/0001-43", "foto.jpg", "99999", "mail@mail", "senha123456", Categoria.MEIO_AMBIENTE);

        Ong ong =  ongService.save(ongDto);

        assertNotNull(ong.getId());
        assertEquals("Ong name", ong.getNome());
    }

    @Test
    void findById(){
        OngDto ongDto = new OngDto("Ong name", "sigla", LocalDate.now(), "63.773.565/0001-21", "foto.jpg", "99999", "mail2@mail", "senha123456", Categoria.MEIO_AMBIENTE);

        Ong ong = ongService.save(ongDto);

        Ong ong2 = ongService.findById(ong.getId());

        assertEquals(ong.getId(), ong2.getId());
        assertEquals(ong.getCategoria(), ong2.getCategoria());

    }

    @Transactional
    @Test
    void find(){
        OngDto ongDto = new OngDto("Ong name", "sigla", LocalDate.now(), "02.585.011/0001-06", "foto.jpg", "99999", "mail3@mail", "senha123456", Categoria.MEIO_AMBIENTE);

        Ong ong = ongService.save(ongDto);

        OngDtoModel dto = ongService.find(ong.getId());

        assertEquals(dto.getCnpj(), ong.getCnpj());
        assertEquals(dto.getEmail(), ong.getEmail());

    }

    @Test
    void update(){
        OngDto ongDto = new OngDto("Ong name", "sigla", LocalDate.now(), "17.262.010/0001-06", "foto.jpg", "99999", "mail4@mail", "senha123456", Categoria.MEIO_AMBIENTE);
        Ong ong = ongService.save(ongDto);

        OngDto ongDto2 = new OngDto("Ong Nome Editado", "EDITADO", LocalDate.now(), "17.262.010/0001-06", "foto.jpg", "99999", "mail3@mail", "senha123456", Categoria.MEIO_AMBIENTE);

        ong = ongService.update(ongDto2, ong.getId());

        assertEquals("Ong Nome Editado", ong.getNome());
        assertEquals("EDITADO", ong.getSigla());
    }

}
