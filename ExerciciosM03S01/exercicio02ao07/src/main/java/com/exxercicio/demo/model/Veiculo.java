package com.exxercicio.demo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder - faz com que volte a dar o erro de pedir pra inserir a PK manualmente antes de registrar
@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id
    private String placa;
    private String tipo;
    private String cor;
    private Integer anoDeFabricacao;
    private Integer qtdMultas;

    public Veiculo(String placa, String tipo, String cor, Integer anoDeFabricacao, Integer qtdMultas) {
    }

    public Veiculo() {

    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

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
