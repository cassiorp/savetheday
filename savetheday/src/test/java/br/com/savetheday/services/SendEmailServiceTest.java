package br.com.savetheday.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("teste_h2")
public class SendEmailServiceTest {

    @Autowired
    SendEmailService sendEmailService;

    @Test
    void sendEmail(){
        sendEmailService.enviar("cassio.r.pereira@gmail.com", "teste", "testeee");
    }
}
