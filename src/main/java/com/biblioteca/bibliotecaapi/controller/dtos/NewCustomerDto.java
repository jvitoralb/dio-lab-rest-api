package com.biblioteca.bibliotecaapi.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerDto {
    @NotBlank
    @Size(max=65)
    private String name;
    @NotBlank
    @Size(min=11, max=11)
    private String cpf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
