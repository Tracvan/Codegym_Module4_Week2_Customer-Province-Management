package com.codegym.service;

import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGenerateService <T> {
    Page<T> findAll(Pageable pageable);
    Iterable<T> findAll();
    void save(T t);
    Optional<T> findById(long id);
    void remove(long id);

}
