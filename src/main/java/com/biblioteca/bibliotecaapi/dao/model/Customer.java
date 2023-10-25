package com.biblioteca.bibliotecaapi.dao.model;


import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "tb_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length=65, nullable=false)
    private String name;

    @Column(length=15, nullable=false, unique = true)
    private String cpf;
//    private Address address;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
