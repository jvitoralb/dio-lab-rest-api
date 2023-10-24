package com.biblioteca.bibliotecaapi.controller;

import com.biblioteca.bibliotecaapi.controller.response.ResponseBody;
import com.biblioteca.bibliotecaapi.dao.model.Book;
import com.biblioteca.bibliotecaapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<ResponseBody> create(@RequestBody Book book) {
        service.insert(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBody(1));
    }

    @GetMapping
    public ResponseEntity<ResponseBody> readAll() {
        ResponseBody resBody = new ResponseBody(1);
        resBody.setMessage(service.getAll());
        return ResponseEntity.status(HttpStatus.OK).body(resBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> read(@PathVariable UUID id) {
        ResponseBody resBody = new ResponseBody(1);
        resBody.setMessage(service.getOne(id));
        return ResponseEntity.status(HttpStatus.OK).body(resBody);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody> update(@PathVariable UUID id, @RequestBody Book bookUpdates) {
        // precisa garantir que todas as são informações do livro passadas no RequestBody
        ResponseBody resBody = new ResponseBody(1);
        resBody.setMessage(service.update(id, bookUpdates));
        return ResponseEntity.status(HttpStatus.OK).body(resBody);
    }

    @PutMapping("/{id}/lend")
    public ResponseEntity<ResponseBody> updateAvailability(@PathVariable UUID id) {
        service.updateAvailability(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBody(1));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
