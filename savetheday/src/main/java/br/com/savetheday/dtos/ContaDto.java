package br.com.savetheday.dtos;

public class ContaDto {

    private String nomeBanco;

    private String agencia;

    private String numero;

    private String digito;

    public ContaDto(String nomeBanco, String agencia, String numero, String digito) {
        this.nomeBanco = nomeBanco;
        this.agencia = agencia;
        this.numero = numero;
        this.digito = digito;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDigito() {
        return digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

}
