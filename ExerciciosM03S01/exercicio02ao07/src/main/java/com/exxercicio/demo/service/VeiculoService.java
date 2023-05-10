package com.exxercicio.demo.service;

import com.exxercicio.demo.dto.VeiculoDTO;
import com.exxercicio.demo.dto.VeiculoMultaDto;
import com.exxercicio.demo.exceptions.PlacaJaExiste;
import com.exxercicio.demo.exceptions.PlacaNaoExiste;
import com.exxercicio.demo.model.Veiculo;
import com.exxercicio.demo.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<Veiculo> salvaVeiculo(Veiculo veiculo) {
        if (veiculoRepository.findByPlaca(veiculo.getPlaca()) == null) {
            veiculoRepository.save(veiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
        } else {
            throw new PlacaJaExiste();
        }
    }

//    public List<Veiculo> buscarVeiculos() {
//        return veiculoRepository.findAll();
//    }
    public ResponseEntity<List<Veiculo>> buscarVeiculos() {
        return ResponseEntity.status(HttpStatus.OK).body(veiculoRepository.findAll());
    }

    public ResponseEntity<Veiculo> buscaVeiculoPorPlaca(String placa) {

        if (veiculoRepository.findByPlaca(placa) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(veiculoRepository.findByPlaca(placa));
    }

    public ResponseEntity<String> apagaVeiculoPorPlaca(String placa) {

        if (veiculoRepository.findByPlaca(placa) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Desculpe, esse veículo não foi localizado no sistema!");
        }

        Veiculo veiculo = veiculoRepository.findByPlaca(placa);

        if (veiculo.getQtdMultas() > 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não foi possível deletar, existem multas vinculadas a esse veículo!");
        }
        veiculoRepository.deleteByPlaca(placa);
        return ResponseEntity.status(HttpStatus.OK).body("Veículo deletado com sucesso!");
    }

    public Veiculo atualizaMulta(String placa, VeiculoMultaDto veiculoMultaDto) {

        if (veiculoRepository.findByPlaca(placa) == null) {
            throw new PlacaNaoExiste();
        }

        Veiculo veiculo = veiculoRepository.findByPlaca(placa);
        veiculo.setQtdMultas(veiculoMultaDto.getQtdMultas());
        veiculoRepository.save(veiculo);
        return veiculo;
    }

    // MÉTODO CRIADO PARA ATUALIZAR O VEÍCULO TODO - Está comentado porque não foi pedido no exercício
//    public Veiculo atualizaVeiculo(String placa, VeiculoDTO veiculoDto) {
//
//        Veiculo veiculo = veiculoRepository.findByPlaca(placa);
//        veiculo.setTipo(veiculoDto.getTipo());
//        veiculo.setCor((veiculoDto.getCor()));
//        veiculo.setAnoDeFabricacao(veiculoDto.getAnoDeFabricacao());
//        veiculo.setQtdMultas(veiculoDto.getQtdMultas());
//
//        veiculoRepository.save(veiculo);
//        return veiculo;
//
//    }

}
