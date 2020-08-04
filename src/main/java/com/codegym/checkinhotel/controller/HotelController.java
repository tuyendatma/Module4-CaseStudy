package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.*;
import com.codegym.checkinhotel.service.city.ICityService;
import com.codegym.checkinhotel.service.hotel.IHotelService;
import com.codegym.checkinhotel.service.hoteldetail.IHotelDetailService;
import com.codegym.checkinhotel.service.room.IRoomService;
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
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private IHotelService hotelService;

    @Autowired
    private Environment environment;

    @Autowired
    private ICityService cityService;

    @Autowired
    private IHotelDetailService hotelDetailService;

    @Autowired
    private IRoomService roomService;

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

    @ModelAttribute("cities")
    public Iterable<City> listCities(){return cityService.findAll();}

    @ModelAttribute("hotelDetails")
    public Iterable<HotelDetails> listHotelDetails(){return hotelDetailService.findAll();}

    @GetMapping
    public String showAllHotel(Model model){
        model.addAttribute("hotels",hotelService.findAll());
        AppUser user  =userService.getUserByUserName(getPrincipal());
        if (user!=null){
            model.addAttribute("user",user);
        }
        return "hotel/faker";
    }

    @GetMapping("/create-hotel")
    public String showCreateHotel(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "hotel/create";
    }

    @PostMapping("/create-hotel")
    public String createHotel( @ModelAttribute Hotel hotel, Model model) {
        MultipartFile file = hotel.getImageFile();
        String fileName = file.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.hotel").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        hotel.setImage(fileName);
        hotelService.save(hotel);
        model.addAttribute("hotel", new Hotel());
        return "hotel/create";
    }

    @GetMapping("/edit-hotel/{id}")
    public String showEditHotel(Model model, @PathVariable("id") Long id){
        model.addAttribute("hotel",hotelService.findById(id));
        return "hotel/edit";
    }

    @PostMapping("/edit-hotel")
    public String editCity(@ModelAttribute("hotel") Hotel hotel, Model model){
        MultipartFile file = hotel.getImageFile();
        String fileName = file.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.hotel").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        hotel.setImage(fileName);
        hotelService.save(hotel);
        model.addAttribute("hotel", hotel);
        return "redirect:/hotels";
    }

    @GetMapping("/delete-hotel/{id}")
    public String deleteHotel (@PathVariable("id") Long id){
        hotelService.remove(id);
        return "redirect:/hotels";
    }

    @GetMapping("/{id}/rooms")
    public ResponseEntity<List<Room>> getRoomsByHotels(@PathVariable Long id){
        Optional<Hotel> hotel = hotelService.findById(id);
        if (!hotel.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Room> rooms = roomService.getAllByHotel(hotel.get());
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }
}
