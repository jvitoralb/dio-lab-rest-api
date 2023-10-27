package com.biblioteca.bibliotecaapi.controller;


import com.biblioteca.bibliotecaapi.controller.dtos.CustomerDto;
import com.biblioteca.bibliotecaapi.controller.response.CustomResponseBody;
import com.biblioteca.bibliotecaapi.dao.model.Customer;
import com.biblioteca.bibliotecaapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;
    private CustomResponseBody res;

    public CustomerController() {
        res = new CustomResponseBody(1);
    }

    @PostMapping
    public ResponseEntity<CustomResponseBody> create(@RequestBody @Valid CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);

        service.insert(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomResponseBody> read(@PathVariable String cpf) {
        res.setMessage(service.getOneByCPF(cpf));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponseBody> update(@PathVariable UUID id, @RequestBody @Valid CustomerDto customerDtoUpdates) {
        Customer customerUpdates = new Customer();
        BeanUtils.copyProperties(customerDtoUpdates, customerUpdates);

        res.setMessage(service.update(id, customerUpdates));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
