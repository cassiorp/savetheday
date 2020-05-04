package br.com.savetheday.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EnderecoDtoInput {

    @NotBlank
    private String estado;
    @NotBlank
    private String cidade;
    @NotBlank
    private String bairro;
    @NotBlank
    private String rua;
    @NotBlank
    private String numero;

    private String CEP;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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
}
