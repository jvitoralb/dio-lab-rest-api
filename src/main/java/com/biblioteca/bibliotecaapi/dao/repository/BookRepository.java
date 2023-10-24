package com.biblioteca.bibliotecaapi.dao.repository;

import com.biblioteca.bibliotecaapi.dao.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface BookRepository extends JpaRepository<Book, UUID> { }
