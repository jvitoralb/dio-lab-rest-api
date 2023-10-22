package com.biblioteca.bibliotecaapi.controller;

import com.biblioteca.bibliotecaapi.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok("All books");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getSingle(@PathVariable String id) {
        return ResponseEntity.ok("Books: " + id);
    }
}
