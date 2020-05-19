package br.com.savetheday.entities;

import br.com.savetheday.entities.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ONGS")
public class Ong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToOne(mappedBy = "ong", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Conta> contas = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Caso> casos = new ArrayList<>();

    public Ong() {
    }

    public Ong(Integer id, @NotBlank @Size(max = 120) String nome, @NotBlank @Size(max = 120) String sigla, LocalDate fundacao, @CNPJ @NotBlank @Size(max = 18) String cnpj, String foto, String telefone, @Email @NotBlank String email, @NotBlank String senha,Categoria categoria, Endereco endereco, List<Conta> contas, List<Caso> casos) {
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
        this.contas = contas;
        this.casos = casos;
    }

    public Ong(Integer id, @NotBlank @Size(max = 120) String nome, @NotBlank @Size(max = 120) String sigla, LocalDate fundacao, @CNPJ @NotBlank @Size(max = 18) String cnpj, String foto, String telefone, @Email @NotBlank String email, @NotBlank String senha, Categoria categoria) {
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public List<Caso> getCasos() {
        return casos;
    }

    public void setCasos(List<Caso> casos) {
        this.casos = casos;
    }
}
