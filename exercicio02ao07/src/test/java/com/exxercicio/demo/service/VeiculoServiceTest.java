package com.exxercicio.demo.service;

import com.exxercicio.demo.exceptions.PlacaJaExiste;
import com.exxercicio.demo.model.Veiculo;
import com.exxercicio.demo.repository.VeiculoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class) // configurando classe de teste para rodar com o Mockito
@MockitoSettings(strictness = Strictness.LENIENT)
class VeiculoServiceTest {
    @Mock // criar objeto mock da dependencia da classe testada
    private VeiculoRepository veiculoRepository;
    @InjectMocks // injeta os mocks criados na classe sendo testada
    private VeiculoService veiculoService;

    @Nested
    class SalvaVeiculoTest {
        @Test
        @DisplayName("Cadastra veículo com placa ainda não cadastrada, retorna veículo salvo")
        void salvaVeiculo_comPlacaNaoCadastrada() {
            // given
            Veiculo veiculoCompleto = new Veiculo("Test-123", "carro", "azul", 2000, 0);
//            Veiculo veiculoCompleto = Veiculo.builder().placa("Test-123").tipo("carro").cor("azul").anoDeFabricacao(2000).qtdMultas(0).build();

            Mockito.when(veiculoRepository.findByPlaca(veiculoCompleto.getPlaca())).thenReturn(null);

            Mockito.when(veiculoRepository.save(Mockito.any(Veiculo.class)))
                    .thenReturn(veiculoCompleto);
            // when
            Veiculo veiculo = veiculoService.salvaVeiculo(veiculoCompleto).getBody();

            // then
            assertNotNull(veiculo);
            assertEquals(veiculoCompleto.getPlaca(), veiculo.getPlaca());
            assertEquals(veiculoCompleto.getTipo(), veiculo.getTipo());
            assertEquals(veiculoCompleto.getCor(), veiculo.getCor());
            assertEquals(veiculoCompleto.getAnoDeFabricacao(), 2000);
            assertEquals(veiculoCompleto.getQtdMultas(), veiculo.getQtdMultas());
        }

        @Test
        @DisplayName("Cadastra veículo com placa já cadastrada, lança exceção")
        void salvaVeiculo_comPlacaJaCadastrada() {

            Veiculo veiculoCompleto = new Veiculo("Test-123", "carro", "azul", 2000, 0);

            Veiculo veiculoExiste = new Veiculo("Test-123", "carro", "azul", 2000, 0);

            Mockito.when(veiculoRepository.findByPlaca(veiculoCompleto.getPlaca())).thenReturn(veiculoExiste);

            Mockito.when(veiculoRepository.save(Mockito.any(Veiculo.class)))
                    .thenReturn(veiculoCompleto);

            assertThrows(PlacaJaExiste.class, () -> veiculoService.salvaVeiculo(veiculoCompleto));

        }

    }

    @Nested
    class BuscarVeiculosTest {
        @Test
        @DisplayName("Busca lista de veículos sem registros, retorna lista vázia")
        void buscarVeiculos_listaVazia() {
            List<Veiculo> listaVeiculos = veiculoService.buscarVeiculos().getBody();

            Mockito.when(veiculoRepository.findAll()).thenReturn(listaVeiculos);

            assertNotNull(listaVeiculos);
            assertEquals(0, listaVeiculos.size());
            assertTrue(listaVeiculos.isEmpty());
        }

        @Test
        @DisplayName("Busca lista de veículos com registros, retorna lista com registros")
        void buscarVeiculos_listaComRegistros() {
            List<Veiculo> listaVeiculos = List.of(
                    new Veiculo("Test-123", "carro", "azul", 2000, 2),
                    new Veiculo("Test-456", "caminhão", "branco", 2010, 0)
            );

            Mockito.when(veiculoRepository.findAll()).thenReturn(listaVeiculos);

            List<Veiculo> listaResultado = veiculoService.buscarVeiculos().getBody();

            assertNotNull(listaResultado);
            assertFalse(listaResultado.isEmpty());
            assertEquals(listaVeiculos, listaResultado);
            assertEquals(2, listaResultado.size());
            assertEquals(listaResultado.get(1).getPlaca(), "Test-456");


        }
    }
}