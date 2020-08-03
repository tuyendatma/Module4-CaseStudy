package com.codegym.checkinhotel.service.hotel;

import com.codegym.checkinhotel.model.City;
import com.codegym.checkinhotel.model.Hotel;
import com.codegym.checkinhotel.service.IService;

import java.util.List;

public interface IHotelService extends IService<Hotel> {
    List<Hotel> getAllByCity(City city);

}
