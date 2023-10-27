package com.biblioteca.bibliotecaapi.controller;

import com.biblioteca.bibliotecaapi.controller.dtos.BookDto;
import com.biblioteca.bibliotecaapi.controller.dtos.CustomerDto;
import com.biblioteca.bibliotecaapi.controller.response.CustomResponseBody;
import com.biblioteca.bibliotecaapi.dao.model.Book;
import com.biblioteca.bibliotecaapi.dao.model.Customer;
import com.biblioteca.bibliotecaapi.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService service;
    private CustomResponseBody res;

    public BookController() {
        res = new CustomResponseBody(1);
    }

    @PostMapping
    public ResponseEntity<CustomResponseBody> create(@RequestBody @Valid BookDto bookDto) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDto, book);

        service.insert(book);

        res.setMessage(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping
    public ResponseEntity<CustomResponseBody> readAll() {
        res.setMessage(service.getAll());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponseBody> read(@PathVariable UUID id) {
        res.setMessage(service.getOne(id));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponseBody> update(@PathVariable UUID id, @RequestBody @Valid BookDto bookDtoUpdates) {
        Book bookUpdates = new Book();
        BeanUtils.copyProperties(bookDtoUpdates, bookUpdates);

        res.setMessage(service.update(id, bookUpdates));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/{id}/loans")
    public ResponseEntity<CustomResponseBody> createLoan(@PathVariable UUID id, @RequestBody @Valid CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);

        res.setMessage(service.loanBook(id, customer));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/{id}/loans")
    public ResponseEntity<CustomResponseBody> updateLoan(@PathVariable UUID id, @RequestBody @Valid CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);

        res.setMessage(service.returnBook(id, customer));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        service.delete(id);
        res.setMessage(null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
