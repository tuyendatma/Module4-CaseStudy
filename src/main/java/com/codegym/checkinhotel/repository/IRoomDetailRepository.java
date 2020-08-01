package com.codegym.checkinhotel.repository;

import com.codegym.checkinhotel.model.RoomDetails;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IRoomDetailRepository extends PagingAndSortingRepository<RoomDetails,Long> {
}
