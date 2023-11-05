package com.biblioteca.bibliotecaapi.controller;


import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
public class HomeController {
    @GetMapping
    public ResponseEntity<String> getHomePage() {
        return ResponseEntity.status(200).contentType(MediaType.TEXT_HTML).body(
            "<h1>Bem vindo à API da Biblioteca!</h1>" +
            "<ul>" +
                "<li><a href=\"/swagger-ui/index.html\">Docs</a></li>" +
                "<li><a href=\"https://github.com/jvitoralb/dio-lab-rest-api\" target=\"_blank\">Repo</a></li>" +
            "</ul>"
        );
    }
}
