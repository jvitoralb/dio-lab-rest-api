package com.biblioteca.bibliotecaapi.service;

import com.biblioteca.bibliotecaapi.domain.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookServiceOperations {
    void insert(Book book);
    Book getOne(UUID id);
    List<Book> getAll();
    void update(UUID id, Book bookUpdates);
    void delete(UUID id);
    boolean exists(UUID id);
}
