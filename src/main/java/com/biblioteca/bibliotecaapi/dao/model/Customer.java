package com.biblioteca.bibliotecaapi.dao.model;


import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "tb_customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 65, nullable = false)
    private String name;

//    @Column(length = 65, nullable = false, unique = true)
//    private String email;

    @Column(length = 8, nullable = false, unique = true)
    private String registration;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

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

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
