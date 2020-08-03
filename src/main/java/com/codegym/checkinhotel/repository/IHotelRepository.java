package com.codegym.checkinhotel.repository;

import com.codegym.checkinhotel.model.City;
import com.codegym.checkinhotel.model.Hotel;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IHotelRepository extends PagingAndSortingRepository<Hotel,Long> {
    List<Hotel> findAllByCity(City city);
}
