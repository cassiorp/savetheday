package br.com.savetheday.servicesImplents.exceptions;

public class ContaNaoConrresponde extends RuntimeException{
    public ContaNaoConrresponde(String message) {
        super(message);
    }
}
