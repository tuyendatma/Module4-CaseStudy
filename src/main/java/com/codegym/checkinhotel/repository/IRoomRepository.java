package com.codegym.checkinhotel.repository;

import com.codegym.checkinhotel.model.Room;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IRoomRepository extends PagingAndSortingRepository<Room,Long> {
}
