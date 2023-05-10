package com.exxercicio.demo.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor // gera um construtor vázio
@AllArgsConstructor // gera um construtor cheio
public class VeiculoMultaDto {
    private Integer qtdMultas;

    public Integer getQtdMultas() {
        return qtdMultas;
    }

    public void setQtdMultas(Integer qtdMultas) {
        this.qtdMultas = qtdMultas;
    }
}
