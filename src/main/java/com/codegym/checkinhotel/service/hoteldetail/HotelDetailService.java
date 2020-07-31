package com.codegym.checkinhotel.service.hoteldetail;

import com.codegym.checkinhotel.model.HotelDetails;
import com.codegym.checkinhotel.repository.IHotelDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HotelDetailService implements IHotelDetailService{

    @Autowired
    IHotelDetailRepository hotelDetailRepository;

    @Override
    public Iterable<HotelDetails> findAll() {
        return hotelDetailRepository.findAll();
    }

    @Override
    public Optional<HotelDetails> findById(Long id) {
        return hotelDetailRepository.findById(id);
    }

    @Override
    public HotelDetails save(HotelDetails hotelDetails) {
        return hotelDetailRepository.save(hotelDetails);
    }

    @Override
    public void remove(Long id) {
        hotelDetailRepository.deleteById(id);
    }
}
