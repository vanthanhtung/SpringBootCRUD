package com.demo.service;

import java.util.Optional;

public interface GeneralService<T> {
    public Iterable<T> findAll();
    public Optional<T> findById(Long id);
    public T save(T t);
    public void remove(Long id);
}
