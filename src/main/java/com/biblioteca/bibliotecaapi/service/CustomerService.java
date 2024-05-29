package com.biblioteca.bibliotecaapi.service;

import com.biblioteca.bibliotecaapi.controller.exception.BusinessException;
import com.biblioteca.bibliotecaapi.dao.model.Customer;
import com.biblioteca.bibliotecaapi.dao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class CustomerService implements ServiceOperations<Customer, UUID> {
    @Autowired
    private CustomerRepository repository;

    public Customer exists(UUID id) {
        return repository.findById(id).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar cliente de ID " + id)
        );
    }

    public Customer insert(Customer customer) {
        String customersCountStr = String.valueOf(repository.count());

        final int REGISTRATION_LENGTH = 8;
        int digitsToTheLeft = REGISTRATION_LENGTH - customersCountStr.length();
        String newCustomerRegistration = "0".repeat(Math.max(0, digitsToTheLeft)) + customersCountStr;

        customer.setRegistration(newCustomerRegistration);
        return repository.save(customer);
    }

    public List<Customer> getAll() {
        return null;
    }

    public Customer getOne(UUID id) {
        return repository.findById(id).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar cliente de ID " + id)
        );
    }

    public Customer getOneByRegistration(String registration) {
        return repository.findByRegistration(registration).orElseThrow(
                () -> new BusinessException("Não foi possível encontrar cliente com matrícula " + registration)
        );
    }

    public Customer getOneByCPF(String cpf) {
        return repository.findByCpf(cpf).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar cliente de CPF " + cpf)
        );
    }

    public Customer update(UUID id, Customer newCustomerInfo) {
        Customer oldCustomerInfo = repository.findById(id).orElseThrow(
            () -> new BusinessException("Não foi possível encontrar cliente de ID " + id)
        );

        newCustomerInfo.setId(oldCustomerInfo.getId());

        return repository.save(newCustomerInfo);
    }

    public void delete(UUID id) {
        exists(id);
        repository.deleteById(id);
    }
}
