package com.codegym.checkinhotel.service.roomdetail;

import com.codegym.checkinhotel.model.RoomDetails;
import com.codegym.checkinhotel.repository.IRoomDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoomDetailService implements IRoomDetailService{

    @Autowired
    private IRoomDetailRepository roomDetailRepository;

    @Override
    public Iterable<RoomDetails> findAll() {
        return roomDetailRepository.findAll();
    }

    @Override
    public Optional<RoomDetails> findById(Long id) {
        return roomDetailRepository.findById(id);
    }

    @Override
    public RoomDetails save(RoomDetails roomDetails) {
        return roomDetailRepository.save(roomDetails);
    }

    @Override
    public void remove(Long id) {
        roomDetailRepository.deleteById(id);
    }


}
