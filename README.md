# Teste de DevelCode

Este projeto é uma demonstração da criação de dois microsserviços para um sistema de pedidos de compra. O objetivo é criar um fluxo onde, caso o pagamento de um pedido falhe, o pedido seja automaticamente cancelado.

## Microsserviços

### 1. Checkout Service (Java/Spring Boot)
- **Descrição**: Este serviço é responsável por criar pedidos de compra.
- **Tecnologia**: Java com Spring Boot.
- **Banco de Dados**: MySQL.

### 2. Payment Gateway Service (Node.js/NestJS)
- **Descrição**: Este serviço simula o processamento de pagamentos. Ele introduz intencionalmente instabilidades, retornando um erro 400 em algumas requisições para simular falhas de pagamento.
- **Tecnologia**: Node.js com NestJS.
- **Banco de Dados**: MongoDB.

## Decisões de Arquitetura

- **Banco de Dados Separados**: Utilizei bancos de dados distintos para cada microsserviço, demonstrando como diferentes tecnologias podem ser usadas em conjunto.
- **Fila de Mensagens Não Utilizada**: Por interpretação do problema, decidi não utilizar sistemas de filas como Kafka ou RabbitMQ. Acredita-se que o serviço de pagamento seja crítico, portanto, não deve ser processado em fila, mas sim de forma síncrona.
- **Simulação de Instabilidade**: O serviço de pagamento inclui uma simulação de instabilidade, retornando um erro 400 de forma intermitente para testar a robustez do sistema.

## Estrutura do Projeto

```plaintext
develcode-test/
│
├── checkout_service/            # Código-fonte do serviço de checkout
│   ├── controllers/             # Controladores do serviço
│   ├── converters/              # Conversores de entidades
│   ├── dtos/                    # Data Transfer Objects
│   ├── repositories/            # Repositórios para acesso ao banco de dados
│   ├── services/                # Lógica de negócios
│   ├── model/                   # Modelos de dados
│   ├── payment/                 # Integração com o serviço de pagamento
│   ├── service/                 # Serviços auxiliares
│   ├── exceptions/              # Tratamento de exceções
│   └── config/                  # Configurações do serviço
│
├── payment_service/             # Código-fonte do serviço de pagamento
│   └── payment/                 # Diretório principal do serviço de pagamento
│       ├── dto/                 # Data Transfer Objects
│       └── repository/          # Repositórios para acesso ao banco de dados
│
├── docker-compose.yml           # Arquivo de configuração do Docker Compose
└── README.md                    # Documentação do projeto

Essa estrutura detalha as diferentes pastas e arquivos dentro dos microsserviços `checkout_service` e `payment_service`, organizando os componentes de forma clara e lógica.

## Instruções de Uso

### Pré-requisitos

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)

### Executando a Aplicação

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/develcode-test.git
   cd develcode-test
2. Construa e inicie os containers:
    docker-compose up --build

3. Api expostas

- **Checkout Service**:
  - Swagger UI: [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)
  - Porta: `8080`
  
- **Payment Gateway Service**:
  - Porta: `3000`
  - **Nota**: Esta API está exposta para fins de demonstração, mas normalmente seria acessada apenas pelo serviço de checkout.

### Considerações Finais
Este projeto foi desenvolvido para demonstrar a criação e integração de microsserviços em diferentes tecnologias, utilizando uma arquitetura containerizada. A escolha de não utilizar filas foi feita para garantir que o serviço de pagamento fosse tratado como uma operação crítica e síncrona.