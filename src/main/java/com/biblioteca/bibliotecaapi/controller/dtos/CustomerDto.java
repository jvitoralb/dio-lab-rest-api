package com.biblioteca.bibliotecaapi.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerDto {
    @NotBlank
    @Size(max=65)
    private String name;
    @NotBlank
    @Size(min=8, max=8)
    private String registration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String cpf) {
        this.registration = registration;
    }
}
