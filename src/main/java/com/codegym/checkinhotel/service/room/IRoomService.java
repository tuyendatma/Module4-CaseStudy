package com.codegym.checkinhotel.service.room;

import com.codegym.checkinhotel.model.Hotel;
import com.codegym.checkinhotel.model.Room;
import com.codegym.checkinhotel.model.RoomDetails;
import com.codegym.checkinhotel.service.IService;

import java.util.List;

public interface IRoomService extends IService<Room> {
    List<Room> getAllByHotel(Hotel hotel);

    List<Room> getAllByRoomDetails(RoomDetails roomDetails);
}
