package br.com.savetheday.dtos;

public class ValorDoadoDto {

    private Double valor;

    public ValorDoadoDto(Double valor) {
        this.valor = valor;
    }

    public ValorDoadoDto() {
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
