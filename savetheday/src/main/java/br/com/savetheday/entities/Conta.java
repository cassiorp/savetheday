package br.com.savetheday.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CONTAS")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 60)
    private String nomeBanco;

    @Size(max = 20)
    private String agencia;

    @Size(max = 30)
    private String numero;

    @Size(max = 2)
    private String digito;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_ong")
    private Ong ong;

    public Conta() {
    }

    public Conta(Integer id, @NotBlank @Size(max = 60) String nomeBanco, @NotBlank @Size(max = 20) String agencia, @NotBlank @Size(max = 30) String numero, @NotBlank @Size(max = 2) String digito, Ong ong) {
        this.id = id;
        this.nomeBanco = nomeBanco;
        this.agencia = agencia;
        this.numero = numero;
        this.digito = digito;
        this.ong = ong;
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

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }

}
