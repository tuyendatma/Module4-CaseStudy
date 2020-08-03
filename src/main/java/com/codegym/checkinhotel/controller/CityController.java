package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.City;
import com.codegym.checkinhotel.model.Hotel;
import com.codegym.checkinhotel.service.city.ICityService;
import com.codegym.checkinhotel.service.hotel.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private Environment environment;

    @Autowired
    private IHotelService hotelService;

    @GetMapping
    public String showAllCity(Model model) {
        model.addAttribute("cities", cityService.findAll());
        return "cities/faker";
    }

    @GetMapping("/create-city")
    public String showCreateCity(Model model) {
        model.addAttribute("city", new City());
        return "cities/create";
    }

    @PostMapping("/create-city")
    public String createHotel(@ModelAttribute City city, Model model) {
        MultipartFile file = city.getImageFile();
        String fileName = file.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.city").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        city.setImage(fileName);
        cityService.save(city);
        model.addAttribute("city", new City());
        return "cities/create";
    }

    @GetMapping("/edit-city/{id}")
    public String showEditCity(Model model, @PathVariable("id") Long id) {
        model.addAttribute("city", cityService.findById(id));
        return "cities/edit";
    }

    @PostMapping("/edit-city")
    public String editCity(@ModelAttribute("city") City city, Model model) {
        MultipartFile file = city.getImageFile();
        String fileName = file.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.city").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        city.setImage(fileName);
        cityService.save(city);
        model.addAttribute("city", city);
        return "redirect:/cities";
    }

    @GetMapping("/delete-city/{id}")
    public String deleteCity(@PathVariable("id") Long id) {
        cityService.remove(id);
        return "redirect:/cities";
    }

    @GetMapping("/{id}/hotels")
    public ResponseEntity<List<Hotel>> getHotelsByCity(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Hotel> hotels = hotelService.getAllByCity(cityOptional.get());
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @PostMapping("/getHotelByCity")
    public ResponseEntity<Iterable<Hotel>> getHotelByCity(@RequestBody City city) {
        return new ResponseEntity<>(hotelService.getAllByCity(city), HttpStatus.OK);
    }
}



