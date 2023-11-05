package com.biblioteca.bibliotecaapi.service;


import com.biblioteca.bibliotecaapi.controller.exception.BusinessException;
import com.biblioteca.bibliotecaapi.dao.model.Book;
import com.biblioteca.bibliotecaapi.dao.model.Customer;
import com.biblioteca.bibliotecaapi.dao.model.Loan;
import com.biblioteca.bibliotecaapi.dao.repository.BookRepository;
import com.biblioteca.bibliotecaapi.dao.repository.CustomerRepository;
import com.biblioteca.bibliotecaapi.dao.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
public class BookService implements ServiceOperations<Book, UUID> {
    @Autowired
    private BookRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    public Book exists(UUID id) {
        return repository.findById(id).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar livro de ID " + id)
        );
    }

    public void insert(Book book) {
        repository.save(book);
    }

    public Book getOne(UUID id) {
        return repository.findById(id).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar livro de ID " + id)
        );
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book update(UUID id, Book bookUpdates) {
        Book oldBook = repository.findById(id).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar livro de ID " + id)
        );

        bookUpdates.setId(oldBook.getId());

        return repository.save(bookUpdates);
    }

    public void delete(UUID id) {
        exists(id);
        repository.deleteById(id);
    }

    public void checkForOpenLoans(Customer customer) {
        loanRepository.findCustomerOpenLoan(customer).ifPresent(loan -> {
            throw new BusinessException("Há um empréstimo em aberto do Cliente de CPF " + loan.getCustomer().getCpf());
        });
    }

    public Loan loanBook(UUID id, Customer customer) {
        Customer targetCustomer = customerRepository.findByCpf(customer.getCpf()).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar cliente de CPF " + customer.getCpf())
        );
        checkForOpenLoans(targetCustomer);

        Book targetBook = exists(id);

        if (!targetBook.isAvailable()) {
            throw new BusinessException("O livro não está disponível", HttpStatus.OK);
        }

        LocalDate expiresAt = LocalDate.now().plusDays(7);
        Loan loanDetails = loanRepository.save(new Loan(targetBook, targetCustomer, expiresAt));

        targetBook.setAvailable(false);
        repository.save(targetBook);

        return loanDetails;
    }

    public Loan returnBook(UUID id, Customer customer) {
        Customer targetCustomer = customerRepository.findByCpf(customer.getCpf()).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar cliente de CPF " + customer.getCpf())
        );

        Book targetBook = exists(id);

        Loan loan = loanRepository.findLoanToClose(targetCustomer, targetBook).orElseThrow(
            () -> new BusinessException("Não há empréstimo em aberto do Cliente com o Livro de ID " + id)
        );

        targetBook.setAvailable(true);
        repository.save(targetBook);

        loan.setFinishedAt(LocalDate.now());
        loan.setClosed(true);

        return loanRepository.save(loan);
    }
}
