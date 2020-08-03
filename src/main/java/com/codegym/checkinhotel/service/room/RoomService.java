package com.codegym.checkinhotel.service.room;

import com.codegym.checkinhotel.model.Hotel;
import com.codegym.checkinhotel.model.Room;
import com.codegym.checkinhotel.model.RoomDetails;
import com.codegym.checkinhotel.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {
    @Autowired
    private IRoomRepository roomRepository;

    @Override
    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void remove(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> getAllByHotel(Hotel hotel) {
        return roomRepository.findAllByHotel(hotel);
    }

    @Override
    public List<Room> getAllByRoomDetails(RoomDetails roomDetails) {
        return roomRepository.findAllByRoomDetails(roomDetails);
    }
}
