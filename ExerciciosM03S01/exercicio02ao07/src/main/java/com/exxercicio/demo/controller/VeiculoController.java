package com.exxercicio.demo.controller;

import com.exxercicio.demo.dto.VeiculoDTO;
import com.exxercicio.demo.dto.VeiculoMultaDto;
import com.exxercicio.demo.model.Veiculo;
import com.exxercicio.demo.service.VeiculoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;

//    @Autowired
//    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Veiculo> cadastrarVeiculo(@RequestBody @Valid Veiculo veiculo) {
        return veiculoService.salvaVeiculo(veiculo);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listarveiculos() {
        return veiculoService.buscarVeiculos();
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Veiculo> pegaVeiculoPorPlaca(@PathVariable String placa) {
        return veiculoService.buscaVeiculoPorPlaca(placa);
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<String> deletaVeiculoPorPlaca(@PathVariable String placa) {
        return veiculoService.apagaVeiculoPorPlaca(placa);
    }

    @PutMapping("/{placa}/multas")
    public Veiculo atualizaVeiculoMulta(@PathVariable String placa, @RequestBody @Valid VeiculoMultaDto veiculoMultaDto) {
        return veiculoService.atualizaMulta(placa, veiculoMultaDto);
    }


    // MÉTODO CRIADO PARA ATUALIZAR O VEÍCULO TODO - Está comentado porque não foi pedido no exercício
//    @PutMapping("/{placa}")
//    public Veiculo atualizaVeiculoPorPlaca(@PathVariable String placa, @RequestBody @Valid VeiculoDTO veiculoDto) {
//        return veiculoService.atualizaVeiculo(placa, veiculoDto);
//    }

}


/*
POST ==> /api/veiculos   (para cadastrar veículo)

GET ==> /api/veiculos  (para consultar todos os veículos cadastrados)

GET ==> /api/veiculos/{placa} (para consultar um veículo pela placa)

DELETE ==> /api/veiculos/{placa} (para excluir um veículo pela placa)

PUT ==> /api/veiculos/{placa}/multas (para adicionar uma multa ao veículo)
*/