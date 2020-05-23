package br.com.savetheday.exceptions;

public class EmailCadastradoException extends RuntimeException {
    public EmailCadastradoException(String message) {
        super(message);
    }
}
