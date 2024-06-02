# Agência de Viagem - API RESTful

Este repositório contém o código fonte de uma API RESTful para gerenciar destinos turísticos, construída com Spring Boot. A API fornece endpoints para criar, ler, atualizar e deletar destinos, além de consultar informações específicas como a descrição de um destino e a sua classificação.

## Descrição do Sistema

A API é projetada para ser utilizada por uma aplicação web ou móvel que oferece serviços de agenciamento de viagens. As funcionalidades da API incluem:

* **Gerenciamento de Destinos:**
* Criar novos destinos, com nome, localização, descrição e classificação.
* Buscar todos os destinos cadastrados.
* Buscar um destino específico pelo seu ID, nome ou localização.
* Atualizar a classificação de um destino.
* Deletar um destino.
* Buscar a descrição de um destino específico pelo seu ID.

## Estrutura do Projeto

O projeto é estruturado da seguinte forma:

* **`com.example.agencia_de_viagem`:** Pacote principal do projeto.
* **`AgenciaDeViagemApplication`:** Classe principal do Spring Boot, responsável por iniciar a aplicação.
* **`model`:**
* **`Destination`:** Classe que representa um destino turístico, com atributos como nome, localização, descrição e classificação.
* **`DestinationDescription`:** Classe que representa a descrição de um destino, com um atributo para a descrição textual.
* **`controller`:**
* **`DestinationController`:** Classe que controla as requisições HTTP para a API, expondo os endpoints RESTful para gerenciar destinos.
* **`service`:**
* **`DestinationService`:** Classe que atua como uma camada intermediária entre o controlador (DestinationController) e o modelo de dados (Destination). Ela encapsula a lógica de negócios relacionada aos destinos turísticos

## Pré-requisitos

* Java Development Kit (JDK) 11 ou superior.
* Apache Maven 3.6 ou superior.
* IDE de desenvolvimento (ex: Eclipse, IntelliJ IDEA).

## Instalação e Execução

1. Clone este repositório.
2. Abra o projeto em sua IDE.
3. Compile o projeto usando o comando `mvn clean install`.
4. Execute a aplicação usando o comando `mvn spring-boot:run`.
5. A aplicação irá rodar no endereço `http://127.0.0.1:8080/api/v1/destination `

## Documentação da API com Swagger

A API é documentada com o Swagger, o que facilita o seu uso e exploração. A documentação interativa pode ser acessada no seguinte caminho:

http://127.0.0.1:8080/swagger-ui.html

## Documentação da API com Postman

A API também foi documentada no Postman.

https://www.postman.com/martian-rocket-149683/workspace/desenvolvimento-de-sistemas-web

## Endpoints da API

A API expõe os seguintes endpoints:

| Endpoint | Método | Descrição |
|---|---|---|
| `/api/v1/destination` | POST | Cria um novo destino. |
| `/api/v1/destination` | GET | Lista todos os destinos. |
| `/api/v1/destination/{id}` | GET | Busca um destino pelo seu ID. |
| `/api/v1/destination/name/{name}` | GET | Busca um destino pelo seu nome. |
| `/api/v1/destination/location/{location}` | GET | Busca um destino pela sua localização. |
| `/api/v1/destination/description/{id}` | GET | Busca a descrição de um destino pelo seu ID. |
| `/api/v1/destination/rating/{id}` | PUT | Atualiza a classificação de um destino. |
| `/api/v1/destination/{id}` | DELETE | Deleta um destino. |

## Exemplos de Uso

### Criar um novo destino:

```
POST /api/v1/destination
Content-Type: application/json

{
"name": "Paris",
"location": "França",
"description": "Cidade romântica com a Torre Eiffel.",
"rating": 4.5
}
```

### Buscar todos os destinos:

```
GET /api/v1/destination
```

### Buscar um destino pelo seu ID:

```
GET /api/v1/destination/1
```

### Atualizar a classificação de um destino:

```
PUT /api/v1/destination/rating/1
Content-Type: application/json

{
"rating": 5.0
}
```

## Considerações

* O código apresentado é um exemplo básico e pode ser expandido com novas funcionalidades e recursos, como autenticação, autorização, documentação da API e testes.
* As informações sobre destinos e avaliações são simuladas neste exemplo. Em um sistema real, esses dados seriam armazenados em um banco de dados.
* A API não inclui funcionalidades de reserva ou compra de pacotes turísticos.

## Contato

Para quaisquer dúvidas ou sugestões, entre em contato através do email: [lucastadeusalomao@gmail.com](mailto:lucastadeusalomao@gmail.com).