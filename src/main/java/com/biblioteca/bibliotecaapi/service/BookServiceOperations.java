package com.biblioteca.bibliotecaapi.service;

import com.biblioteca.bibliotecaapi.dao.model.Book;

import java.util.UUID;

public interface BookServiceOperations extends ServiceOperations<Book, UUID> {
    void updateAvailability(UUID id);
}
