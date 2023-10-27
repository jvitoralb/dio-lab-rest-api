package com.biblioteca.bibliotecaapi.dao.repository;

import com.biblioteca.bibliotecaapi.dao.model.Customer;
import com.biblioteca.bibliotecaapi.dao.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
    Loan findByCustomer(Customer customer);
}
