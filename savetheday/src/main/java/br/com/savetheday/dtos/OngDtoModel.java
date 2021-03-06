package br.com.savetheday.dtos;

import java.util.List;

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
    private List<ContaDtoModel> contas;
    private List<CasoDtoModelToOng> casos;

    public OngDtoModel(Integer id, String nome, String sigla, String fundacao, String cnpj, String foto, String telefone, String email, String senha, String categoria, EnderecoDtoModel endereco, List<ContaDtoModel> contas, List<CasoDtoModelToOng> casos) {
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

    public List<ContaDtoModel> getContas() {
        return contas;
    }

    public void setContas(List<ContaDtoModel> contas) {
        this.contas = contas;
    }

    public List<CasoDtoModelToOng> getCasos() {
        return casos;
    }

    public void setCasos(List<CasoDtoModelToOng> casos) {
        this.casos = casos;
    }
}
