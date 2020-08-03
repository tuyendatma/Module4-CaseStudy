package com.codegym.checkinhotel.repository;

import com.codegym.checkinhotel.model.Hotel;
import com.codegym.checkinhotel.model.Room;
import com.codegym.checkinhotel.model.RoomDetails;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IRoomRepository extends PagingAndSortingRepository<Room,Long> {
    List<Room> findAllByHotel(Hotel hotel);

    List<Room> findAllByRoomDetails(RoomDetails roomDetails);
}
