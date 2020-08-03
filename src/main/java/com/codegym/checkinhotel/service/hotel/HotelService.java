package com.codegym.checkinhotel.service.hotel;

import com.codegym.checkinhotel.model.City;
import com.codegym.checkinhotel.model.Hotel;
import com.codegym.checkinhotel.repository.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService implements IHotelService {
    @Autowired
    private IHotelRepository hotelRepository;

    @Override
    public Iterable<Hotel> findAll() {
       return hotelRepository.findAll();
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public void remove(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public List<Hotel> getAllByCity(City city) {
        return hotelRepository.findAllByCity(city);
    }
}
