package com.biblioteca.bibliotecaapi.service;

import com.biblioteca.bibliotecaapi.dao.model.Customer;

import java.util.UUID;

public interface CustomerServiceOperations extends ServiceOperations<Customer, UUID> {
    Customer getOneByCPF(String cpf);
}
