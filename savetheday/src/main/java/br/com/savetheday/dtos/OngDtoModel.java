package br.com.savetheday.dtos;

import br.com.savetheday.dtos.EnderecoDtoModel;
import br.com.savetheday.entities.Categoria;
import br.com.savetheday.entities.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class OngDtoModel {


    private Integer id;
    private String nome;
    private String sigla;
    private String fundacao;
    private String cnpj;
    private String foto;
    private String telefone;
    private String email;
    private String senha;
    private String categoria;
    private EnderecoDtoModel endereco;

    public OngDtoModel(Integer id, String nome, String sigla, String fundacao, String cnpj, String foto, String telefone, String email, String senha, String categoria, EnderecoDtoModel endereco) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.fundacao = fundacao;
        this.cnpj = cnpj;
        this.foto = foto;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.categoria = categoria;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getFundacao() {
        return fundacao;
    }

    public void setFundacao(String fundacao) {
        this.fundacao = fundacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public EnderecoDtoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDtoModel endereco) {
        this.endereco = endereco;
    }
}
