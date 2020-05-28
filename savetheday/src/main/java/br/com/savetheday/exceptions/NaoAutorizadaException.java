package br.com.savetheday.exceptions;

public class NaoAutorizadaException extends RuntimeException{
    public NaoAutorizadaException(String message) {
        super(message);
    }
}
