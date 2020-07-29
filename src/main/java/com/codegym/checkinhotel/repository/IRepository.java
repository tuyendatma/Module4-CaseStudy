package com.codegym.checkinhotel.repository;

import com.codegym.checkinhotel.model.AppUser;

import java.util.Optional;

public interface IRepository<T> {

    Iterable<T> listCustomer();
    Optional<T> findById(Long id);
    T save(AppUser appUser);
    void remove(Long id);
}
