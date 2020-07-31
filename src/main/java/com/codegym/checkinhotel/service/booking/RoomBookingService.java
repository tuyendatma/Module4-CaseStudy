package com.codegym.checkinhotel.service.booking;

import com.codegym.checkinhotel.model.RoomBooking;
import com.codegym.checkinhotel.repository.IRoomBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomBookingService implements IRoomBookingService {
    @Autowired
    private IRoomBookingRepository roomBookingRepository;

    @Override
    public Iterable<RoomBooking> findAll() {
        return roomBookingRepository.findAll();
    }

    @Override
    public Optional<RoomBooking> findById(Long id) {
        return roomBookingRepository.findById(id);
    }

    @Override
    public RoomBooking save(RoomBooking roomBooking) {
        return roomBookingRepository.save(roomBooking);
    }

    @Override
    public void remove(Long id) {
        roomBookingRepository.deleteById(id);
    }
}
