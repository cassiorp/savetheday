package br.com.savetheday.dtos;

import br.com.savetheday.entities.enums.StatusCaso;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CasoDto {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotNull
    private Double total;
    @Enumerated(EnumType.STRING)
    private StatusCaso statusCaso;
    @NotNull
    private Integer idOng;

    public CasoDto(@NotBlank String titulo, @NotBlank String descricao, @NotNull Double total, @NotNull Integer idOng) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.total = total;
        this.idOng = idOng;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public StatusCaso getStatusCaso() {
        return statusCaso;
    }

    public void setStatusCaso(StatusCaso statusCaso) {
        this.statusCaso = statusCaso;
    }

    public Integer getIdOng() {
        return idOng;
    }

    public void setIdOng(Integer idOng) {
        this.idOng = idOng;
    }
}
