package com.codegym.checkinhotel.repository;

import com.codegym.checkinhotel.model.RoomBooking;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IRoomBookingRepository extends PagingAndSortingRepository<RoomBooking,Long> {
}
