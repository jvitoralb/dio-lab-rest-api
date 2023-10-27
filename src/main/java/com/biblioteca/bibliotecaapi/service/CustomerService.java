package com.biblioteca.bibliotecaapi.service;

import com.biblioteca.bibliotecaapi.controller.exception.BusinessException;
import com.biblioteca.bibliotecaapi.dao.model.Customer;
import com.biblioteca.bibliotecaapi.dao.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CustomerService implements ServiceOperations<Customer, UUID> {
    @Autowired
    private CustomerRepository repository;

    public boolean exists(UUID id) {
        return repository.existsById(id);
    }

    public void insert(Customer customer) {
        repository.save(customer);
    }

    public List<Customer> getAll() {
        return null;
    }

    public Customer getOne(UUID id) {
        Optional<Customer> target = repository.findById(id);

        if (target.isEmpty()) {
            throw new BusinessException("Não foi possível encontrar recurso de ID " + id);
        }
        return target.get();
    }

    public Customer getOneByCPF(String cpf) {
        Customer target = repository.findByCpf(cpf);

        if (target == null) {
            throw new BusinessException("Não foi possível encontrar recurso de CPF " + cpf);
        }
        return target;
    }

    public Customer update(UUID id, Customer customerUpdates) {
        Optional<Customer> oldCustomerInfo = repository.findById(id);

        if (oldCustomerInfo.isEmpty()) {
            throw new BusinessException("Não foi possível encontrar recurso de ID " + id);
        }
        Customer newCustomerInfo = new Customer();
        BeanUtils.copyProperties(customerUpdates, newCustomerInfo);
        newCustomerInfo.setId(oldCustomerInfo.get().getId());

        return repository.save(newCustomerInfo);
    }

    public void delete(UUID id) {
        if (!exists(id)) {
            throw new BusinessException("Não foi possível encontrar recurso de ID " + id);
        }
        repository.deleteById(id);
    }
}
