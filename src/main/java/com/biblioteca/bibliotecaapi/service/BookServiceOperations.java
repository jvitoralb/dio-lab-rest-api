package com.biblioteca.bibliotecaapi.service;

import com.biblioteca.bibliotecaapi.dao.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookServiceOperations {
    void insert(Book book);
    Book getOne(UUID id);
    List<Book> getAll();
    Book update(UUID id, Book bookUpdates);
    void updateAvailability(UUID id);
    void delete(UUID id);
    boolean exists(UUID id);
}
