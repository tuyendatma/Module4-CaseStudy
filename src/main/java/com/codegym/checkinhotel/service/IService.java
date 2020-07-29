package com.codegym.checkinhotel.service;

import java.util.Optional;

public interface IService<T> {
    Iterable<T> listCustomer();
    Optional<T> findById(Long id);
    T save(T t);
    void remove(Long id);
}
