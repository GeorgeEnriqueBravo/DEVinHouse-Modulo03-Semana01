# Bem vindo à DEVinHouse <img width="180px" alt="Philips" src="ExerciciosM03S01/images/logo-phil.png"/>
## Módulo 03 - Semana 01

Repositório criado para a elaboração dos 7 exercícios referentes a **Testes Unitários** em `Spring` dessa semana. <br>

Para visualizar os exercícios, <a href="https://github.com/GeorgeEnriqueBravo/DEVinHouse-Modulo03-Semana01/archive/refs/heads/main.zip" target="_blank">
    clique aqui
</a>
para baixa-los. <br>

Após o download, abra a pasta "ExerciciosM03S01" utilizando o software `IntelliJ`. Para baixar a última versão grátis do IntelliJ 
<a href="https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC" target="_blank">
    clique aqui
</a>.
  
---

# Lista de exercícios <img width="75px" alt="Philips" src="ExerciciosM03S01/images/lista.png"/>
### [M3S01] Ex 01 - Testes Unitários

a) Implementar uma classe 'FolhaDePagamento' conforme exemplo abaixo. </br>
b) Implementar testes unitários para o método 'calcularSalarioBruto'. </br>
c) Implementar testes unitários para o outro método 'calcularSalarioLiquido'. </br>
```
import java.util.List;

public class FolhaDePagamento {

 public Double calcularSalarioBruto(Double salarioBase, Double gratificacao, String funcao) {
    Double salario = salarioBase;
    if (gratificacao != null) {
        salario += gratificacao;
    }
    if (funcao.equals("gerente")) {
        salario *= 1.30;
    }
    return salario;
}

public Double calcularSalarioLiquido(Double salario, List<Double> descontos) {
    if (descontos == null || descontos.isEmpty()) {
        return salario;
    }
    for(Double desconto : descontos) {
        salario -= desconto;
    }
    if (salario < 0) {
        throw new IllegalStateException();
    }
    return salario;
 }
}
```

### [M3S01] Ex 02 - Revisão API REST com Testes (parte 1)

Criar uma API REST com SpringBoot sobre 'Veiculos'.
Use os conceitos e organização do projeto conforme exemplos visto em aula.

A classe de modelo **Veiculo** deve conter os seguintes atributos:

- placa (String - identificador do veículo - PK)
- tipo (String - tipo de veículo : carro, caminhão, ônibus, etc...)
- cor (String)
- anoDeFabricacao (Integer)
- qtdMultas (Integer - representa a quantidade de multas)

### [M3S01] Ex 03 - Revisão API REST com Testes (parte 2)

Sobre a API, agora crie uma classe VeiculosController que contenha os endpoints com os seguintes serviços REST disponíveis:
```
POST ==> /api/veiculos   (para cadastrar veículo)

GET ==> /api/veiculos  (para consultar todos os veículos cadastrados)

GET ==> /api/veiculos/{placa} (para consultar um veículo pela placa)

DELETE ==> /api/veiculos/{placa} (para excluir um veículo pela placa)

PUT ==> /api/veiculos/{placa}/multas (para adicionar uma multa ao veículo)
```
O código HTTP de response dos respectivos endpoints deve ser coerente com cada operacao.

### [M3S01] Ex 04 - Revisão API REST com Testes (parte 3)

Crie então uma classe/interface de Repository usando SpringData (VeiculoRepository).
Pode ser usado um banco de dados embarcado na aplicação (H2) para persistência dos dados.

### [M3S01] Ex 05 - Revisão API REST com Testes (parte 4)

Crie uma classe de Service (VeiculoService) contendo as regras de negócio para prover os serviços previstos no controller.

Nos métodos do Service devem estar previstos o atendimento das seguintes regras:

a) Não pode ser cadastrado mais de um veículo com a mesma placa </br>
b) Quando a consulta por um veículo nao tiver resultado, deve ser lançado exceção no Service, e retornado o código http 404 pro cliente da API. </br>
c) Não pode ser excluído um veículo que tenha multas cadastradas para ele (multas > 0) </br>

### [M3S01] Ex 06 - Revisão API REST com Testes (parte 5)

Implemente testes unitários (JUnit) para a classe de Service (VeiculoService) da API construída, usando Mocks (Mockito) quando necessário.

### [M3S01] Ex 07 - Revisão API REST com Testes (parte 6)

Implemente testes unitários (JUnit) para a classe de Controller da API construída, usando MockMvc e Mockito quando necessário (conforme visto em aula).

---

# O que é DEVinHouse?
DEVinhouse é uma jornada de aceleração da carreira com: grade curricular direcionada, professores do mercado, prática constante, interação frequente com as houses parceiras de cada turma, simulação do dia-a-dia DEV e vagas exclusivas que são abertas pelas Houses durante a jornada.

No DEVinHouse você vira um desenvolvedor Fullstack em 9 meses, ao longo de 900 horas de conteúdo, divididas em três módulos de 3 meses cada, com intervalo de uma semana entre cada um. Aproximadamente 25 horas de dedicação por semana entre aulas, atividades e vivências.

__1º módulo:__ Front-End – JavaScript e Angular <br/>
__2º módulo:__ Back-End – Java, Spring e SQL <br/>
__3º módulo:__ Full-Stack – Scrum, DevOps, Clean Code e Design Patterns <br/>
__Ferramentas__ – GitHub, Trello e Slack

---

# Tecnologias Utilizadas <img width="35px" alt="🌐" src="ExerciciosM03S01/images/tag.gif"/>
Nos exercícios dessa semana foram utilizadas as seguintes tecnologias:
<div style="display: inline_block">
    <img align="center" alt="Java" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
    <img align="center" alt="Spring" src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
    <img align="center" alt="Intellij" src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white"/>
    <img align="center" alt="Oracle" src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=black"/>
    <img align="center" alt="Trello" src="https://img.shields.io/badge/Trello-0052CC?style=for-the-badge&logo=trello&logoColor=white"/>
    <img align="center" alt="GitHub" src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"/>
</div>

