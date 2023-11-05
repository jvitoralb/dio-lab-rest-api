# Biblioteca API

- [Sobre](#sobre)
- [Como Funciona](#como-funciona)
    - [Usuário](#usuários)
    - [Livro](#livros)


## Sobre
Biblioteca API é uma `REST API` desenvolvida para a conclusão do Santander Bootcamp 2023 - Backend Java.

O objetivo foi simular operações de uma biblioteca, como registrar livros, usuários e emitir empréstimo de livros aos usuários registrados.

A API foi desenvolvida na linguagem `Java` e utilizando `Spring Framework` e `PostgreSQL`.

O projeto está disponível para acesso em [dio-lab-rest-api-production.up.railway.app](https://dio-lab-rest-api-production.up.railway.app/)

## Como Funciona

### Usuários
As operações envolvendo os usuários são feitas no endpoint `/customers`

#### GET
`/customers/{cpf}`  
- **Retorna** uma mensagem de sucesso contendo o usuário correspondente ao cpf indicado.

#### POST
`/customers`  
- **Requer** um corpo contendo as nome e cpf(sem pontos e traços).
- **Retorna** uma mensagem de sucesso.

#### PUT
`/customers/{id}`  
- **Requer** um corpo contendo as atualizações a serem feitas.
- **Retorna** uma mensagem de sucesso contendo as informações atualizadas.

#### DELETE
`/customers/{cpf}`  
- **Retorna** um status code de 204, indicando sucesso na operação e corpo da mensagem vazio.

### Livros
As operações envolvendo os livro são feitas no endpoint `/books`

#### GET
`/books`  
- **Retorna** uma mensagem de sucesso com uma lista contendo todos os livros registrados no banco de dados.

`/books/{id}`  
- **Retorna** uma mensagem de sucesso contendo o livro correspondente ao id indicado.

#### POST
`/books`  
- **Requer** um corpo contendo título, autor e data da publicação.
- **Retorna** uma mensagem de sucesso.

`/books/{id}/loans`  
- **Requer** um corpo contendo os dados do usuário a fazer um empréstimo.
- **Retorna** uma mensagem de sucesso contendo detalhes do empréstimo.

#### PUT
`/books/{id}`  
- **Requer** um corpo contendo as atualizações a serem feitas a um livro.
- **Retorna** uma mensagem de sucesso contendo o livro atualizado.

`/books/{id}/loans`  
- **Requer** um corpo contendo os dados do usuário que realizou o empréstimo.
- **Retorna** uma mensagem de sucesso contendo detalhes atualizados do empréstimo.

#### DELETE
`/books/{id}`  
- **Retorna** um status code de 204, indicando sucesso na operação e corpo da mensagem vazio.
