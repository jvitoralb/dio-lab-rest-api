package com.biblioteca.bibliotecaapi.controller;

import com.biblioteca.bibliotecaapi.controller.dtos.BookDto;
import com.biblioteca.bibliotecaapi.controller.dtos.CustomerDto;
import com.biblioteca.bibliotecaapi.controller.response.CustomResponseBody;
import com.biblioteca.bibliotecaapi.dao.model.Book;
import com.biblioteca.bibliotecaapi.dao.model.Customer;
import com.biblioteca.bibliotecaapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService service;
    private CustomResponseBody res;

    public BookController() {
        res = new CustomResponseBody(1);
    }

    @PostMapping
    @Operation(summary = "Criar novo livro", description = "Salvar um livro no banco de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso")
    })
    public ResponseEntity<CustomResponseBody> create(@RequestBody @Valid BookDto bookDto) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDto, book);

        service.insert(book);

        res.setMessage(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping
    @Operation(summary = "Ler todos os livros", description = "Ler e retornar todos os livros que estão registrados no banco de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    })
    public ResponseEntity<CustomResponseBody> readAll() {
        res.setMessage(service.getAll());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Ler um livro", description = "Dado um ID, buscar e retornar o livro que está registrado com esse ID no banco de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Operação falhou por não encontrar o livro")
    })
    public ResponseEntity<CustomResponseBody> read(@PathVariable UUID id) {
        res.setMessage(service.getOne(id));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um livro", description = "Dado um ID, buscar, atualizar e retornar o livro atualizado, que está registrado com esse ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Operação falhou por não encontrar  o livro")
    })
    public ResponseEntity<CustomResponseBody> update(@PathVariable UUID id, @RequestBody @Valid BookDto bookDtoUpdates) {
        Book bookUpdates = new Book();
        BeanUtils.copyProperties(bookDtoUpdates, bookUpdates);

        res.setMessage(service.update(id, bookUpdates));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/{id}/loans")
    @Operation(summary = "Criar empréstimo", description = "Dado o ID do livro e os dados do cliente, criar um empréstimo e retornar os detalhes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Operação falhou por não encontrar o livro ou cliente")
    })
    public ResponseEntity<CustomResponseBody> createLoan(@PathVariable UUID id, @RequestBody @Valid CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);

        res.setMessage(service.loanBook(id, customer));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/{id}/loans")
    @Operation(summary = "Atualizar empréstimo", description = "Dado o ID do livro e os dados do cliente, atualizar empréstimo e retornar os detalhes atualizados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Operação falhou por não encontrar o livro ou cliente")
    })
    public ResponseEntity<CustomResponseBody> updateLoan(@PathVariable UUID id, @RequestBody @Valid CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);

        res.setMessage(service.returnBook(id, customer));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um livro", description = "Dado um ID, buscar e deletar o livro, registrado com esse ID, do banco de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Operação realizada com sucesso e sem retorno"),
        @ApiResponse(responseCode = "400", description = "Operação falhou por não encontrar livro correspondente ao ID")
    })
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        service.delete(id);
        res.setMessage(null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
