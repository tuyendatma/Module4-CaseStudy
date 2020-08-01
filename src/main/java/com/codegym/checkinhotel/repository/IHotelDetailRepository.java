package com.codegym.checkinhotel.repository;

import com.codegym.checkinhotel.model.HotelDetails;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IHotelDetailRepository extends PagingAndSortingRepository<HotelDetails,Long> {
}
