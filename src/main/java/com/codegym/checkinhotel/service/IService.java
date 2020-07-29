package com.codegym.checkinhotel.service;

import com.codegym.checkinhotel.model.AppUser;

import java.util.Optional;

public interface IService<T> {
    Iterable<T> listCustomer();
    Optional<T> findById(Long id);
    T save(AppUser appUser);
    void remove(Long id);
}
