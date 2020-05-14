package br.com.savetheday.services;

import br.com.savetheday.dtos.ContaDto;
import br.com.savetheday.dtos.OngDto;
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
public class ContaServiceTest {

    @Autowired
    ContaService contaService;

    @Autowired
    OngService ongService;

    @Test
    void save(){
        OngDto ongDto = new OngDto("Ong name", "sigla", LocalDate.now(), "63.097.084/0001-43", "foto.jpg", "99999", "mail@mail", "senha123456", Categoria.MEIO_AMBIENTE);
        Ong ong =  ongService.save(ongDto);

        ContaDto dto = new ContaDto("Banrisul", "0509", "3590000","1", ong.getId());
        Conta conta = contaService.save(dto);

        assertNotNull(conta.getId());
        assertEquals("0509", conta.getAgencia());
    }

    @Test
    void findById(){
        OngDto ongDto = new OngDto("Ong name", "sigla", LocalDate.now(), "96.530.464/0001-89", "foto.jpg", "99999", "mail1@mail", "senha123456", Categoria.MEIO_AMBIENTE);
        Ong ong =  ongService.save(ongDto);

        ContaDto dto = new ContaDto("Banrisul", "0509", "3590000","1", ong.getId());
        Conta conta = contaService.save(dto);

        Conta conta2 = contaService.findById(conta.getId());

        assertEquals(conta.getId(), conta2.getId());
        assertEquals(conta.getAgencia(),conta2.getAgencia());

    }

    @Test
    void delete(){
        OngDto ongDto = new OngDto("Ong name", "sigla", LocalDate.now(), "42.165.812/0001-37", "foto.jpg", "99999", "mail11@mail", "senha123456", Categoria.MEIO_AMBIENTE);
        Ong ong =  ongService.save(ongDto);

        ContaDto dto = new ContaDto("Banrisul", "0509", "3590000","1", ong.getId());
        Conta conta = contaService.save(dto);

        Boolean seDeletou = contaService.delete(conta.getId());

        assertEquals(true, seDeletou);
    }

    @Test
    void update(){
        OngDto ongDto = new OngDto("Ong name", "sigla", LocalDate.now(), "77.156.472/0001-62", "foto.jpg", "99999", "mail111@mail", "senha123456", Categoria.MEIO_AMBIENTE);
        Ong ong =  ongService.save(ongDto);

        ContaDto dto = new ContaDto("Banrisul", "0509", "3590000","1", ong.getId());
        Conta conta = contaService.save(dto);

        ContaDto dto2 = new ContaDto("Banco do brasil", "0509", "3590000","1", ong.getId());
        conta = contaService.update(dto2, conta.getId());

        assertEquals("Banco do brasil", conta.getNomeBanco());

    }

}
