package br.com.savetheday.dtos;


public class CasoDtoModel {

    private Integer id;

    private String titulo;

    private String descricao;

    private String total;

    private String coletado;

    private String status;

    public CasoDtoModel(Integer id, String titulo, String descricao, String total, String coletado, String status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.total = total;
        this.coletado = coletado;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getColetado() {
        return coletado;
    }

    public void setColetado(String coletado) {
        this.coletado = coletado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
