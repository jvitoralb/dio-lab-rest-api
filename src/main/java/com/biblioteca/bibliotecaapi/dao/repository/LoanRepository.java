package com.biblioteca.bibliotecaapi.dao.repository;

import com.biblioteca.bibliotecaapi.dao.model.Book;
import com.biblioteca.bibliotecaapi.dao.model.Customer;
import com.biblioteca.bibliotecaapi.dao.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
    @Query("SELECT l FROM tb_loans l WHERE l.customer=?1 AND l.closed=false")
    Optional<Loan> findOpenLoanByCustomer(Customer customer);

    @Query("SELECT l FROM tb_loans l WHERE l.customer=?1 AND l.book=?2 AND l.closed=false")
    Optional<Loan> findLoanToClose(Customer customer, Book book);
}
