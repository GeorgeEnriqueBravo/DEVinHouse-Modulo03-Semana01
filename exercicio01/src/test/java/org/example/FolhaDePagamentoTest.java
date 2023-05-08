package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FolhaDePagamentoTest {
        private FolhaDePagamento folhaDePagamento;

        @BeforeEach
        public void setup() {
            folhaDePagamento = new FolhaDePagamento();
        }

        @Nested
        @DisplayName("Testando método calcularSalarioBruto")
        class CalcularSalarioBrutoTest {
            @Test
            @DisplayName("Teste sem gratificação e sem gerente, retorna salario base")
            void calculaSalarioBruto_semGratificacao_semGerencia() {
                Double salarioBase = 1500.00;
                Double gratificacao = null;
                String funcao = "analista";

                Double salarioBruto = folhaDePagamento.calcularSalarioBruto(1500.00, gratificacao, funcao);

                assertNotEquals("gerente", funcao);
                assertNotNull(salarioBruto);
                assertEquals(salarioBase, salarioBruto);
            }
            @Test
            @DisplayName("Teste com gratificação e sem gerente, retorna salário base + gratificação")
            void calculaSalarioBruto_comGratificacao_semGerente() {
                Double salarioBase = 1500.00;
                Double gratificacao = 150.00;
                String funcao = "analista";

                Double salarioBruto = folhaDePagamento.calcularSalarioBruto(1500.00, gratificacao, funcao);

                assertNotEquals("gerente", funcao);
                assertNotNull(salarioBruto);
                assertTrue(salarioBruto > salarioBase);
                assertEquals(1650, salarioBruto);
            }

            @Test
            @DisplayName("Teste sem gratificação e com gerente, retorna salário base + bonus gerente")
            void calculaSalarioBruto_semGratificacao_comGerente() {
                Double salarioBase = 1500.00;
                Double gratificacao = null;
                String funcao = "gerente";

                Double salarioBruto = folhaDePagamento.calcularSalarioBruto(salarioBase, gratificacao, funcao);

                assertEquals("gerente", funcao);
                assertNotNull(salarioBruto);
                assertTrue(salarioBruto > salarioBase);
                assertEquals(1950, salarioBruto);
            }

            @Test
            @DisplayName("Teste com gratificação e com gerente, retorna salário base + gratificação + bonus gerente")
            void calculaSalarioBruto_comGratificacao_comGerente() {
                Double salarioBase = 1500.00;
                Double gratificacao = 150.00;
                String funcao = "gerente";

                Double salarioBruto = folhaDePagamento.calcularSalarioBruto(salarioBase, gratificacao, funcao);

                assertEquals("gerente", funcao);
                assertNotNull(salarioBruto);
                assertTrue(salarioBruto > salarioBase);
                assertEquals(2145, salarioBruto);
            }

        }

        @Nested
        class CalcularSalarioLiquidoTest {
            @Test
            @DisplayName("Teste sem descontos, deve retornar salário")
            void calcularSalarioLiquido_semDescontos() {
                Double salario = 2000.00;
                List<Double> descontos = List.of(0.0, 0.0, 0.0);
//                 List<Double> descontos = new ArrayList<>();

                Double salarioLiquido = folhaDePagamento.calcularSalarioLiquido(salario, descontos);

                assertNotNull(salarioLiquido);
                assertNotNull(descontos);
                assertEquals(salario, salarioLiquido);
            }

            @Test
            @DisplayName("Teste com descontos, deve retornar salário subtraído descontos")
            void calcularSalarioLiquido_comDescontos() {
                Double salario = 2000.00;
                List<Double> descontos = List.of(100.00, 150.00, 200.00);
                Double somaDeDescontos = descontos.stream().reduce(Double::sum).get(); // 450

                Double salarioLiquido = folhaDePagamento.calcularSalarioLiquido(salario, descontos);

                assertNotNull(salarioLiquido);
                assertTrue(salarioLiquido > 0);
                assertEquals(salario - somaDeDescontos, salarioLiquido); // 1550
            }

            @Test
            @DisplayName("Teste com descontos maiores que o salário")
            void calcularSalarioLiquido_comDescontosMaioresQueSalario() {
                Double salario = 2000.00;
                List<Double>  descontos = List.of(500.00, 750.00, 1000.00);
                Double somaDeDescontos = descontos.stream().reduce(Double::sum).get(); // 2250

                assertThrows(IllegalStateException.class, () ->
                        folhaDePagamento.calcularSalarioLiquido(salario, descontos));

            }
        }

}

// @WebMvcTest permite usarmos o:
    // private MockMvc mockMvc

// @DataJpaTest permite usar o:
    // TestEntityManager