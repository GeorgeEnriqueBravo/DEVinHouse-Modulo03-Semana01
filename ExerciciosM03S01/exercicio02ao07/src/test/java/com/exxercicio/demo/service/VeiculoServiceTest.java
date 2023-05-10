package com.exxercicio.demo.service;

import com.exxercicio.demo.dto.VeiculoMultaDto;
import com.exxercicio.demo.exceptions.PlacaJaExiste;
import com.exxercicio.demo.exceptions.PlacaNaoExiste;
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
            var listaVeiculos = List.of(
                    new Veiculo("Test-123", "carro", "azul", 2000, 2),
                    new Veiculo("Test-456", "caminhão", "branco", 2010, 0)
            );

            Mockito.when(veiculoRepository.findAll()).thenReturn(listaVeiculos);

            List<Veiculo> listaResultado = veiculoService.buscarVeiculos().getBody();

            assertNotNull(listaResultado);
            assertFalse(listaResultado.isEmpty());
            assertEquals(listaVeiculos, listaResultado);
            assertEquals(2, listaResultado.size());
            assertEquals(listaResultado.get(0).getAnoDeFabricacao(), 2000);
            assertEquals(listaResultado.get(1).getPlaca(), "Test-456");
        }
    }

    @Nested
    class BuscaVeiculoPorPlacaTest {
        @Test
        @DisplayName("Busca veículo por placa existente, retorna o veículo")
        void buscaVeiculoPorPlaca_encontrado() {
            Veiculo veiculo = new Veiculo("Test-123", "carro", "azul", 2000, 2);
            String placa = "Test-123";

            Mockito.when(veiculoRepository.findByPlaca(Mockito.anyString())).thenReturn(veiculo);

            Veiculo veicuLocalizado = veiculoService.buscaVeiculoPorPlaca(placa).getBody();

            assertNotNull(veicuLocalizado);
            assertEquals(veicuLocalizado.getPlaca(), placa);
        }

        @Test
        @DisplayName("Busca veículo por placa que não existe, retorna nulo")
        void buscaVeiculoPorPlaca_naoEncontrado() {
            Veiculo veiculo = new Veiculo();
            String placa = "Test-123";

            Mockito.when(veiculoRepository.findByPlaca(Mockito.anyString())).thenReturn(veiculo);
//                Mockito.when(veiculoRepository.findByPlaca(Mockito.anyString())).thenReturn(new Veiculo());

            Veiculo veicuLocalizado = veiculoService.buscaVeiculoPorPlaca(placa).getBody();

            assertNotNull(veicuLocalizado);
            assertNull(veicuLocalizado.getPlaca());
        }

    }

    @Nested
    class ApagaVeiculoPorPlacaTest {
        @Test
        @DisplayName("Busca veículo por placa que não existe, retorna msg de não localizado")
        void apagaVeiculoPorPlaca_naoEncontrado() {
            Veiculo veiculo = new Veiculo();
            String placa = "Test-123";

            Mockito.when(veiculoRepository.findByPlaca(Mockito.anyString())).thenReturn(null);

            String veiculoLocalizado = veiculoService.apagaVeiculoPorPlaca(placa).getBody();

            assertNotNull(veiculoLocalizado);
            assertEquals(veiculoLocalizado, "Desculpe, esse veículo não foi localizado no sistema!");
        }

        @Test
        @DisplayName("Busca veículo por placa existente, mas com multas, retorna msg de multas vinculadas")
        void apagaVeiculoPorPlaca_Encontrado_masComMultas() {
            Veiculo veiculo = new Veiculo("Test-123", "carro", "azul", 2000, 2);
            String placa = "Test-123";

            Mockito.when(veiculoRepository.findByPlaca(Mockito.anyString())).thenReturn(veiculo);

            String veiculoLocalizado = veiculoService.apagaVeiculoPorPlaca(placa).getBody();

            assertNotNull(veiculoLocalizado);
            assertEquals(veiculoLocalizado, "Não foi possível deletar, existem multas vinculadas a esse veículo!");
        }

        @Test
        @DisplayName("Busca veículo por placa existente, mas sem multas, retorna msg de veículo deletado")
        void apagaVeiculoPorPlaca_Encontrado_semMultas() {
            Veiculo veiculo = new Veiculo("Test-123", "carro", "azul", 2000, 0);
            String placa = "Test-123";

            Mockito.when(veiculoRepository.findByPlaca(Mockito.anyString())).thenReturn(veiculo);

            String veiculoLocalizado = veiculoService.apagaVeiculoPorPlaca(placa).getBody();

            assertNotNull(veiculoLocalizado);
            assertEquals(veiculoLocalizado, "Veículo deletado com sucesso!");
        }
    }

    @Nested
    class atualizaMultaTest {
        @Test
        @DisplayName("Atualiza veículo inexistente, retorna exceção PlacaNaoExiste")
        void atualizaMulta_veiculoNaoEncontrado() {
            VeiculoMultaDto veiculoMultaDto = new VeiculoMultaDto();
            String placa = "Test-123";

            Mockito.when(veiculoRepository.findByPlaca(placa)).thenReturn(null);

            assertThrows(PlacaNaoExiste.class, () -> veiculoService.atualizaMulta(placa, veiculoMultaDto));
        }

        @Test
        @DisplayName("Atualiza veículo existente, retorna veículo")
        void atualizaMulta_veiculoEncontrado() {
            VeiculoMultaDto veiculoMultaDto = new VeiculoMultaDto(2);
            Veiculo veiculo = new Veiculo("Test-123", "carro", "azul", 2000, 0);
            String placa = "Test-123";

            Mockito.when(veiculoRepository.findByPlaca(placa)).thenReturn(veiculo);

            Veiculo veiculoMultaatualizada = veiculoService.atualizaMulta(placa, veiculoMultaDto);

            assertNotNull(veiculoMultaatualizada);
            assertEquals(veiculoMultaatualizada.getQtdMultas(), veiculoMultaDto.getQtdMultas());
            assertEquals(veiculoMultaatualizada.getQtdMultas(), 2);
        }

    }
}