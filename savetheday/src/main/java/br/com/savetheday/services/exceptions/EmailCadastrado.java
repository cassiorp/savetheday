package br.com.savetheday.services.exceptions;

public class EmailCadastrado extends RuntimeException {
    public EmailCadastrado(String message) {
        super(message);
    }
}
