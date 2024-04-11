package com.codegym.service;

import com.codegym.model.Customer;

import java.util.Optional;

public interface IGenerateService <T> {
    Iterable<T> findAll();
    void save(T t);
    Optional<T> findById(long id);
    void remove(long id);

}
