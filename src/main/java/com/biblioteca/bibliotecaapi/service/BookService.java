package com.biblioteca.bibliotecaapi.service;


import com.biblioteca.bibliotecaapi.domain.model.Book;
import com.biblioteca.bibliotecaapi.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    BookRepository repository;

    public Iterable<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(UUID id) {
        Optional<Book> foundBook = repository.findById(id);
        if (foundBook.isEmpty()) {
            throw new RuntimeException("Could not find book");
        }
        return foundBook.get();
    }

    public Book insertBook(Book book) {
        return repository.save(book);
    }
}
