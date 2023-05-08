package com.exxercicio.demo.dto;

public class VeiculoDTO {
    private String tipo;
    private String cor;
    private Integer anoDeFabricacao;
    private Integer qtdMultas;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(Integer anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public Integer getQtdMultas() {
        return qtdMultas;
    }

    public void setQtdMultas(Integer qtdMultas) {
        this.qtdMultas = qtdMultas;
    }
}

