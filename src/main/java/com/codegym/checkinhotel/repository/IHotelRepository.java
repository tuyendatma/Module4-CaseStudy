package com.codegym.checkinhotel.repository;

import com.codegym.checkinhotel.model.Hotel;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IHotelRepository extends PagingAndSortingRepository<Hotel,Long> {
}
