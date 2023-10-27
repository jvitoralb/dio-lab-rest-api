package com.biblioteca.bibliotecaapi.service;

import com.biblioteca.bibliotecaapi.dao.model.Book;
import com.biblioteca.bibliotecaapi.dao.model.Customer;
import com.biblioteca.bibliotecaapi.dao.model.Loan;

import java.util.UUID;

public interface BookServiceOperations extends ServiceOperations<Book, UUID> {
    Loan loanBook(UUID id, Customer customer);
    Loan returnBook(UUID id, Customer customer);
}
