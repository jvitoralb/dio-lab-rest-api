package com.biblioteca.bibliotecaapi.controller;

import com.biblioteca.bibliotecaapi.domain.model.Book;
import com.biblioteca.bibliotecaapi.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>> getAll() {
        return ResponseEntity.status(200).body(service.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getSingle(@PathVariable UUID id) {
        Book book = service.getBookById(id);
        return ResponseEntity.status(200).body(book);
    }

    @PostMapping
    public ResponseEntity<Book> postBook(@RequestBody Book book) {
        Book insertedBook = service.insertBook(book);
        return ResponseEntity.status(201).body(insertedBook);
    }
}
