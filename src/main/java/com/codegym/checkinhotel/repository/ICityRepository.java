package com.codegym.checkinhotel.repository;

import com.codegym.checkinhotel.model.City;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICityRepository extends PagingAndSortingRepository<City,Long> {

}
