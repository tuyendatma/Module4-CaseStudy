package com.codegym.checkinhotel.repository;

import com.codegym.checkinhotel.model.City;
import com.codegym.checkinhotel.model.Room;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ICityRepository extends PagingAndSortingRepository<City,Long> {
}
