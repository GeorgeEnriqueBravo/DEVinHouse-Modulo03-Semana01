package com.exxercicio.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Builder - faz com que volte a dar o erro de pedir pra inserir a PK manualmente antes de registrar
@Data
@NoArgsConstructor // gera um construtor vázio
@AllArgsConstructor // gera um construtor cheio
@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id
    private String placa;
    private String tipo;

    private String cor;
    private Integer anoDeFabricacao;
    private Integer qtdMultas;


    //@AllArgsConstructor - faz por baixo dos panos a mesma coisa que esse construtor completo abaixo
//    public Veiculo(String placa, String tipo, String cor, Integer anoDeFabricacao, Integer qtdMultas) {
//        this.placa = placa;
//        this.tipo = tipo;
//        this.cor = cor;
//        this.anoDeFabricacao = anoDeFabricacao;
//        this.qtdMultas = qtdMultas;
//    }


    //@NoArgsConstructor - faz por baixo dos panos a mesma coisa que esse construtor vázio abaixo
//    public Veiculo() {
//
//    }


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
