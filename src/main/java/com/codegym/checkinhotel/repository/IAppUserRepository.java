package com.codegym.checkinhotel.repository;

import com.codegym.checkinhotel.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface IAppUserRepository extends CrudRepository<AppUser,Long> {
    AppUser findAppUserByUsername(String username);
}
