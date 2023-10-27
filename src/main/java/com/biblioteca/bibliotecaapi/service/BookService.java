package com.biblioteca.bibliotecaapi.service;


import com.biblioteca.bibliotecaapi.controller.exception.BusinessException;
import com.biblioteca.bibliotecaapi.dao.model.Book;
import com.biblioteca.bibliotecaapi.dao.model.Customer;
import com.biblioteca.bibliotecaapi.dao.model.Loan;
import com.biblioteca.bibliotecaapi.dao.repository.BookRepository;
import com.biblioteca.bibliotecaapi.dao.repository.CustomerRepository;
import com.biblioteca.bibliotecaapi.dao.repository.LoanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class BookService implements ServiceOperations<Book, UUID> {
    @Autowired
    private BookRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    public boolean exists(UUID id) {
        return repository.existsById(id);
    }

    public void insert(Book book) {
        repository.save(book);
    }

    public Book getOne(UUID id) {
        Optional<Book> bookTarget = repository.findById(id);

        if (bookTarget.isEmpty()) {
            throw new BusinessException("Não foi possível encontrar recurso de ID " + id);
        }
        return bookTarget.get();
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book update(UUID id, Book bookUpdates) {
        Optional<Book> oldBook = repository.findById(id);

        if (oldBook.isEmpty()) {
            throw new BusinessException("Não foi possível encontrar recurso de ID " + id);
        }
        Book newBook = new Book();
        BeanUtils.copyProperties(bookUpdates, newBook);
        newBook.setId(oldBook.get().getId());

        return repository.save(newBook);
    }

    public void delete(UUID id) {
        if (!exists(id)) {
            throw new BusinessException("Não foi possível encontrar recurso de ID " + id);
        }
        repository.deleteById(id);
    }

    public Loan loanBook(UUID id, Customer customer) {
        Book targetBook = repository.findById(id).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar livro de ID " + id)
        );
        Customer targetCustomer = customerRepository.findByCpf(customer.getCpf()).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar cliente de CPF " + customer.getCpf())
        );

        LocalDate expiresAt = LocalDate.now().plusDays(7);
        Loan loanDetails = loanRepository.save(new Loan(targetBook, targetCustomer, expiresAt));

        targetBook.setAvailable(false);
        repository.save(targetBook);

        return loanDetails;
    }

    public Loan returnBook(UUID id, Customer customer) {
        Book targetBook = repository.findById(id).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar livro de ID " + id)
        );
        Customer targetCustomer = customerRepository.findByCpf(customer.getCpf()).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar cliente de CPF " + customer.getCpf())
        );

        targetBook.setAvailable(true);
        repository.save(targetBook);

        Loan loan = loanRepository.findByCustomer(targetCustomer).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar empréstimo do Cliente de CPF " + customer.getCpf())
        );

        loan.setFinishedAt(LocalDate.now());
        loan.setClosed(true);
        return loanRepository.save(loan);
    }
}
