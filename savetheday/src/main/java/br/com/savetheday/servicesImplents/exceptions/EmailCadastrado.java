package br.com.savetheday.servicesImplents.exceptions;

public class EmailCadastrado extends RuntimeException {
    public EmailCadastrado(String message) {
        super(message);
    }
}
