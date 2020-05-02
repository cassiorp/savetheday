package br.com.savetheday.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ENDERECOS")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 60)
    private String bairro;

    @Size(max = 60)
    private String rua;

    @Size(max = 20)
    private String numero;

    @Size(max = 8)
    private String CEP;

    @ManyToOne
    @JoinColumn(name="id_cidade")
    private Cidade cidade;

    public Endereco(Integer id, @NotBlank @Size(max = 60) String bairro, @NotBlank @Size(max = 60) String rua, @NotBlank @Size(max = 20) String numero, @NotBlank @Size(max = 8) String CEP, Cidade cidade) {
        this.id = id;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.CEP = CEP;
        this.cidade = cidade;
    }

    public Endereco() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
