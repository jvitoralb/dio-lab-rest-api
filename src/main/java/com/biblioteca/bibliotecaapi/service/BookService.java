package com.biblioteca.bibliotecaapi.service;


import com.biblioteca.bibliotecaapi.dao.model.Book;
import com.biblioteca.bibliotecaapi.dao.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService implements BookServiceOperations {
    @Autowired
    private BookRepository repository;

    public boolean exists(UUID id) {
        return repository.existsById(id);
    }

    public void insert(Book book) {
        repository.save(book);
    }

    public Book getOne(UUID id) {
        Optional<Book> bookTarget = repository.findById(id);

        if (bookTarget.isEmpty()) {
            throw new RuntimeErrorException(new Error(), "Could not find book");
        }
        return bookTarget.get();
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book update(UUID id, Book bookUpdates) {
        Optional<Book> oldBook = repository.findById(id);

        if (oldBook.isEmpty()) {
            throw new RuntimeErrorException(new Error(), "Could not find book");
        }
        Book newBook = new Book();
        BeanUtils.copyProperties(bookUpdates, newBook);
        newBook.setId(oldBook.get().getId());

        return repository.save(newBook);
    }

    public void updateAvailability(UUID id) {
        Optional<Book> target = repository.findById(id);

        if (target.isEmpty()) {
            throw new RuntimeErrorException(new Error(), "Could not find book");
        }
        target.get().setAvailable(!target.get().isAvailable());
        repository.save(target.get());
    }

    public void delete(UUID id) {
        if (!exists(id)) {
            throw new RuntimeErrorException(new Error(), "Could not find book");
        }
        repository.deleteById(id);
    }
}
