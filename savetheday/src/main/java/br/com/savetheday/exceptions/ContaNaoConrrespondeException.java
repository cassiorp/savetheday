package br.com.savetheday.exceptions;

public class ContaNaoConrrespondeException extends RuntimeException{
    public ContaNaoConrrespondeException(String message) {
        super(message);
    }
}
