package br.com.savetheday.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviar(String destinatario, String assunto, String mensagem) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destinatario);
        email.setSubject(assunto);
        email.setText(mensagem);

        mailSender.send(email);

    }
}
