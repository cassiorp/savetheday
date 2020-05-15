package br.com.savetheday.entities;

import br.com.savetheday.entities.enums.StatusCaso;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CASOS")
public class Caso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotNull
    private Double total;

    private Double coletado;
    @Enumerated(EnumType.STRING)
    private StatusCaso status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_ong")
    private Ong ong;

    public Caso() {
    }

    public Caso(Integer id, @NotBlank String titulo, @NotBlank String descricao, @NotNull Double total, StatusCaso status, Ong ong) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.total = total;
        this.status = status;
        this.ong = ong;
    }


    public Caso(Integer id, String titulo, String descricao, Double total, Double coletado, StatusCaso status, Ong ong) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.total = total;
        this.coletado = coletado;
        this.status = status;
        this.ong = ong;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getColetado() {
        return coletado;
    }

    public void setColetado(Double coletado) {
        this.coletado = coletado;
    }

    public StatusCaso getStatus() {
        return status;
    }

    public void setStatus(StatusCaso status) {
        this.status = status;
    }

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }
}
