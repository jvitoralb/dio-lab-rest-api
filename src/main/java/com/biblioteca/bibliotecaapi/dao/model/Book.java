package com.biblioteca.bibliotecaapi.dao.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "tb_books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length=75, nullable=false)
    private String title;

    @Column(length=75, nullable=false)
    private String author;

    @Column(name = "publish_date")
    private LocalDate published;
    private boolean available = true;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
