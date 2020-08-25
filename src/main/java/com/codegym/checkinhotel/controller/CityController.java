package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.AppUser;
import com.codegym.checkinhotel.model.City;
import com.codegym.checkinhotel.model.Hotel;
import com.codegym.checkinhotel.model.Room;
import com.codegym.checkinhotel.service.city.ICityService;
import com.codegym.checkinhotel.service.hotel.IHotelService;
import com.codegym.checkinhotel.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private IAppUserService userService;

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails){
            userName = ((UserDetails)principal).getUsername();
        }else {
            userName = principal.toString();
        }
        return userName;
    }

    @GetMapping
    public String showAllCity(Model model) {
        model.addAttribute("cities", cityService.findAll());
        AppUser user  =userService.getUserByUserName(getPrincipal());
        if (user!=null){
            model.addAttribute("user",user);
        }
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

//    @GetMapping("/getAllHotelByCity")
//    public ResponseEntity<Iterable<Hotel>> getAllHotelByCity(@RequestBody City city) {
//        return new ResponseEntity<>(hotelService.getAllByCity(city), HttpStatus.OK);
//    }

    @GetMapping("/hotel/{id}")
    public String getAllHotelByCity(@PathVariable Long id, Model model) {
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()){
            List<Hotel> hotels = hotelService.getAllByCity(city.get());
            model.addAttribute("hotels",hotels);
        }
        return "hotel/faker";
    }

    @GetMapping("/view-city/{id}")
    public String showViewCity(Model model, @PathVariable("id") Long id){
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()){
            List<Hotel> hotels = hotelService.getAllByCity(city.get());
            model.addAttribute("hotels",hotels);
            AppUser user = userService.getUserByUserName(getPrincipal());
            if (user != null) {
                model.addAttribute("user", user);
            }
        }
        model.addAttribute("city",city);
        return "cities/info";
    }
}



