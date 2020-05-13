package br.com.savetheday.dtos;

public class ContaDtoModel {

    private Integer id;

    private String nomeBanco;

    private String agencia;

    private String numero;

    private String digito;

    public ContaDtoModel(Integer id, String nomeBanco, String agencia, String numero, String digito) {
        this.id = id;
        this.nomeBanco = nomeBanco;
        this.agencia = agencia;
        this.numero = numero;
        this.digito = digito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
