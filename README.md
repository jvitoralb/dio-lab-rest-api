# Biblioteca API

- [Sobre](#sobre)
- [Como Funciona](#como-funciona)
    - [Controller](#controller)
    - [Service](#service)
    - [DAO](#dao)
- [Endpoints](#endpoints)
    - [Usuários](#usuários)
    - [Livros](#livros)


## Sobre
Biblioteca API é uma `REST API` desenvolvida para a conclusão do Santander Bootcamp 2023 - Backend Java.

O objetivo foi simular operações de uma biblioteca, como registrar livros, usuários e emitir empréstimo de livros aos usuários registrados.

A API foi desenvolvida na linguagem `Java` e utilizando `Spring Framework` e `PostgreSQL`.

O projeto está disponível para acesso em [dio-lab-rest-api-production.up.railway.app](https://dio-lab-rest-api-production.up.railway.app/)

## Como Funciona

Por seguir a arquitetura `REST`, todas as operações são feitas utilizando `JSON`, tanto as respostas quanto as requisições. E as requisições, por sua vez, são feitas através de metódos HTTP.

As requisições que enviam um corpo, vão ter seu corpo validado na camada mais externa da aplicação. Como a aplicação segue uma arquitetura de 3 camadas, a mais externa seria a - `controller`.

Toda a regra de negócios da API está na camada de serviços - `service`, que por sua vez se comunica com a camada `DAO`. E é nessa última camada, `DAO`, onde é feita a modelagem dos dados e a execução de operações ao banco de dados.

### Controller
Responsável por receber requisições e comunicá-las a camada `service`, receber as respostas e devolvê-las ao cliente. É, também, responsável por validar o corpo das requisições utilizando `DTOs`.  
As exceções, quando jogadas pela aplicação, são tratadas nessa camada.

#### WebResponse
O controller faz o uso de uma classe customizada - WebResponse, para enviar as respostas ao cliente. Esse por sua vez utiliza o `ResponseEntity` para manipular as respostas HTTP a serem enviadas ao cliente.  
Essa classe possui 4 propriedades:
- `status`: mensagens customizadas de status da resposta.
    - *sucesso*: requisição recebida, executada.
        - HttpStatus: `200 - OK` / `201 - CREATED`
    - *sucesso impedido*: requisição recebida porém a execução foi impedida.
        - HttpStatus: `200 - OK`
    - *erro cliente*
        - HttpStatus: `400 - Bad Request`
    - *erro server*
        - HttpStatus: `500 - Server Internal Error`
- `timestamp`: a data da resposta.
- `message`: Um objeto que contém a resposta para a requisição do cliente.

### Service
Por conter a regra de negócio da aplicação, recebe o pedido do cliente e o responde através do `controller`. Possui, também, uma comunicação com a camada `DAO` o que permite acessar o banco de dados.

### DAO
Utilizando o `Spring Data JPA`, faz a modelagem e mapeamento dos dados, e com o `Repository` executa *queries* ao banco de dados.

### Endpoints
Aqui estão todos os endpoints disponíveis na aplicação. Assim como, as respostas esperadas e, no caso dos métodos `POST` e `PUT`, o que se espera no corpo da requisição.

A aplicação conta com o [OpenAPI/Swagger UI](https://dio-lab-rest-api-production.up.railway.app/swagger-ui/index.html) para executar operações nos *endpoints*.

#### Usuários
As operações envolvendo os usuários são feitas no endpoint `/customers`  
O usuário recebe o `registration` após fazer o registro na biblioteca.

##### GET
`/customers/{registration}`  
- **Retorna** uma mensagem de sucesso contendo o usuário correspondente ao cpf indicado.

##### POST
`/customers`  
- **Requer** um corpo contendo as nome e cpf(sem pontos e traços).
- **Retorna** uma mensagem de sucesso com informações do usuário como `name`, `cpf`, `registration`.

##### PUT
`/customers/{id}`  
- **Requer** um corpo contendo as atualizações a serem feitas.
- **Retorna** uma mensagem de sucesso contendo as informações atualizadas.

##### DELETE
`/customers/{id}`  
- **Retorna** um status code de 204, indicando sucesso na operação e corpo da mensagem vazio.

#### Livros
As operações envolvendo os livro são feitas no endpoint `/books`

##### GET
`/books`  
- **Retorna** uma mensagem de sucesso com uma lista contendo todos os livros registrados no banco de dados.

`/books/{id}`  
- **Retorna** uma mensagem de sucesso contendo o livro correspondente ao id indicado.

##### POST
`/books`  
- **Requer** um corpo contendo título, autor e data da publicação.
- **Retorna** uma mensagem de sucesso.

`/books/{id}/loans`  
- **Requer** um corpo contendo os dados do usuário a fazer um empréstimo.
- **Retorna** uma mensagem de sucesso contendo detalhes do empréstimo.

##### PUT
`/books/{id}`  
- **Requer** um corpo contendo as atualizações a serem feitas a um livro.
- **Retorna** uma mensagem de sucesso contendo o livro atualizado.

`/books/{id}/loans`  
- **Requer** um corpo contendo os dados do usuário que realizou o empréstimo.
- **Retorna** uma mensagem de sucesso contendo detalhes atualizados do empréstimo.

##### DELETE
`/books/{id}`  
- **Retorna** um status code de 204, indicando sucesso na operação e corpo da mensagem vazio.

Execute as operações acima com [OpenAPI/Swagger UI](https://dio-lab-rest-api-production.up.railway.app/swagger-ui/index.html).
