package com.biblioteca.bibliotecaapi.service;

import java.util.List;

public interface ServiceOperations<T, K> {
    void insert(T t);
    T getOne(K k);
    List<T> getAll();
    T update(K k, T t);
    void delete(K k);
    boolean exists(K k);
}