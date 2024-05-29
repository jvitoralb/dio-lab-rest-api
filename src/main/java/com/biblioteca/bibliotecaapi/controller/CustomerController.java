package com.biblioteca.bibliotecaapi.controller;


import com.biblioteca.bibliotecaapi.controller.dtos.CustomerDto;
import com.biblioteca.bibliotecaapi.controller.dtos.NewCustomerDto;
import com.biblioteca.bibliotecaapi.controller.response.WebResponse;
import com.biblioteca.bibliotecaapi.dao.model.Customer;
import com.biblioteca.bibliotecaapi.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private WebResponse res;

    public CustomerController() {
        res = new WebResponse("sucesso");
    }

    @PostMapping
    @Operation(summary = "Criar novo cliente", description = "Salvar os dados de um novo cliente no banco de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso")
    })
    public ResponseEntity<WebResponse> create(@RequestBody @Valid NewCustomerDto newCustomerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(newCustomerDto, customer);

        Customer insertedCustomer = service.insert(customer);
        res.setMessage(insertedCustomer);

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/{registration}")
    @Operation(summary = "Ler cliente", description = "Dado número de registro do cliente, buscar e retornar seus dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Operação falhou por não encontrar cliente")
    })
    public ResponseEntity<WebResponse> read(@PathVariable @Valid String registration) {
        res.setMessage(service.getOneByRegistration(registration));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente", description = "Dado o ID do cliente, buscar, atualizar e retornar seus dados atualizados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Operação falhou por não encontrar cliente")
    })
    public ResponseEntity<WebResponse> update(@PathVariable UUID id, @RequestBody @Valid CustomerDto customerDtoUpdates) {
        Customer customerUpdates = new Customer();
        BeanUtils.copyProperties(customerDtoUpdates, customerUpdates);

        res.setMessage(service.update(id, customerUpdates));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar cliente", description = "Dado o ID do cliente, deletar seus dados do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realizada com sucesso e sem retorno"),
            @ApiResponse(responseCode = "400", description = "Operação falhou por não encontrar cliente")
    })
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        service.delete(id);
        res.setMessage(null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
