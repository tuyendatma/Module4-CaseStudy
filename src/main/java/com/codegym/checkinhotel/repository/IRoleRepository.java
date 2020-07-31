package com.codegym.checkinhotel.repository;

import com.codegym.checkinhotel.model.AppRole;
import org.springframework.data.repository.CrudRepository;

import javax.management.relation.Role;

public interface IRoleRepository extends CrudRepository<AppRole,Long> {
}
