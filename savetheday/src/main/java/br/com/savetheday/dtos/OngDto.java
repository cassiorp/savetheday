package br.com.savetheday.dtos;

import br.com.savetheday.entities.enums.Categoria;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class OngDto {

    @NotBlank
    @Size(max = 120)
    private String nome;

    @NotBlank
    @Size(max = 20)
    private String sigla;

    private LocalDate fundacao;
    @CNPJ
    @NotBlank
    @Size(max = 18)
    private String cnpj;

    private String foto;
    @NotBlank
    private String telefone;
    @NotBlank
    private String email;
    @NotBlank
    @Length(min = 6, max = 12)
    private String senha;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public OngDto(@NotBlank @Size(max = 120) String nome, @NotBlank @Size(max = 20) String sigla, LocalDate fundacao, @CNPJ @NotBlank @Size(max = 18) String cnpj, String foto, @NotBlank String telefone, @NotBlank String email, @NotBlank @Length(min = 6, max = 12) String senha, Categoria categoria) {
        this.nome = nome;
        this.sigla = sigla;
        this.fundacao = fundacao;
        this.cnpj = cnpj;
        this.foto = foto;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.categoria = categoria;
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

    public LocalDate getFundacao() {
        return fundacao;
    }

    public void setFundacao(LocalDate fundacao) {
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
