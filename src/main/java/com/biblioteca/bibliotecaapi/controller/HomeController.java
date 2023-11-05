package com.biblioteca.bibliotecaapi.controller;


import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
public class HomeController {
    @GetMapping
    public ResponseEntity<String> getHomePage() {
        return ResponseEntity.status(200).body("Bem vindo ao Gestor da Biblioteca!");
    }
}
