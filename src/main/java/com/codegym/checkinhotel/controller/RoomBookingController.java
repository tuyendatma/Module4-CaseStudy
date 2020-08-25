package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.*;
import com.codegym.checkinhotel.service.booking.IRoomBookingService;
import com.codegym.checkinhotel.service.city.ICityService;
import com.codegym.checkinhotel.service.hotel.IHotelService;
import com.codegym.checkinhotel.service.hoteldetail.IHotelDetailService;
import com.codegym.checkinhotel.service.room.IRoomService;
import com.codegym.checkinhotel.service.roomdetail.IRoomDetailService;
import com.codegym.checkinhotel.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class RoomBookingController {
    @Autowired
    private IRoomBookingService roomBookingService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IAppUserService userService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IRoomDetailService roomDetailService;

    @Autowired
    private IHotelDetailService hotelDetailService;

    @ModelAttribute("rooms")
    public Iterable<Room> listRooms(){
        return roomService.findAll();
    }

    @ModelAttribute("hotels")
    public Iterable<Hotel> listHotels(){
        return hotelService.findAll();
    }

    @ModelAttribute("cities")
    public Iterable<City> listCities(){
        return cityService.findAll();
    }

    @ModelAttribute("users")
    public Iterable<AppUser> listUsers(){
        return userService.findAll();
    }

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
    public String showAllRoomBookings(Model model){
        model.addAttribute("bookings",roomBookingService.findAll());
        return "booking/list";
    }

    @GetMapping("/create-booking")
    public String showCreateRoomBooking(Model model) {
        model.addAttribute("booking", new RoomBooking());
        AppUser user  =userService.getUserByUserName(getPrincipal());
        if (user!=null){
            model.addAttribute("user",user);
        }
        return "booking/create";
    }

    @PostMapping("/create-booking")
    public String createRoomBooking(@ModelAttribute RoomBooking roomBooking, Model model) throws ParseException {
        AppUser user = userService.getUserByUserName(getPrincipal());
        if (user == null) {
            return "access-denied";
        } else {
            roomBooking.setUser(user);
        }

        //logic here
//        int totalStandard = 0;
//        int totalSuperior = 0;
//        int totalDeluxe = 0;
//        int totalSuite = 0;
//        int totalConnectingRoom = 0;
//        Iterable<RoomDetails> roomDetailList = roomDetailService.findAll();
//        for (RoomDetails roomDetail:roomDetailList){
//            if (roomDetail.getName().contains("Standard")){
//                totalStandard=roomDetail.getQuantity();
//            }else if (roomDetail.getName().contains("Superior")){
//                totalSuperior=roomDetail.getQuantity();
//            }else if (roomDetail.getName().contains("Deluxe")){
//                totalDeluxe=roomDetail.getQuantity();
//            }else if (roomDetail.getName().contains("Suite")){
//                totalSuite=roomDetail.getQuantity();
//            }else {
//                totalConnectingRoom=roomDetail.getQuantity();
//            }
//        }
//
//        int quantityStandard = 0;
//        int quantitySuperior = 0;
//        int quantityDeluxe = 0;
//        int quantitySuite = 0;
//        int quantityConnectingRoom = 0;
//        Iterable<RoomBooking> roomBookings = roomBookingService.findAll();
//        for (RoomBooking roomBooking1 : roomBookings) {
//            if (roomBooking1.getRoom().getRoomDetails().getName().contains("Standard")) {
//                quantityStandard += 1;
//            } else if (roomBooking1.getRoom().getRoomDetails().getName().contains("Superior")) {
//                quantitySuperior += 1;
//            } else if (roomBooking1.getRoom().getRoomDetails().getName().contains("Deluxe")) {
//                quantityDeluxe += 1;
//            } else if (roomBooking1.getRoom().getRoomDetails().getName().contains("Suite")) {
//                quantitySuite += 1;
//            } else {
//                quantityConnectingRoom += 1;
//            }
//        }


//        DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date1 = dateFormat.parse(roomBooking.getCheckinDate());
        Date date2 = dateFormat.parse(roomBooking.getCheckoutDate());
        long difference = date2.getTime()-date1.getTime();
//        System.out.println(difference);
//        String timestamp = (String.valueOf(System.currentTimeMillis()));
//        Date date3 = dateFormat.parse(timestamp);
//        long b = date1.getTime()-date3.getTime();
//        System.out.println(b);


//        if (difference<0){
//            model.addAttribute("messages","Pls check your booking time!");
//            return "booking/create";
//        }else {
            model.addAttribute("booking", new RoomBooking());
//            model.addAttribute("messages","Your booking room success!");
            roomBookingService.save(roomBooking);
            return "booking/create";
//        }
    }

    @GetMapping("/edit-booking/{id}")
    public String showEditRoomBooking(Model model, @PathVariable("id") Long id){
        model.addAttribute("booking",roomBookingService.findById(id));
        return "booking/edit";
    }

    @PostMapping("/edit-booking")
    public String editRoomBooking(@ModelAttribute RoomBooking roomBooking, Model model){
        roomBookingService.save(roomBooking);
        model.addAttribute("booking", roomBooking);
        return "redirect:/bookings";
    }

    @GetMapping("/delete-booking/{id}")
    public String deleteRoomBooking (@PathVariable("id") Long id){
        roomBookingService.remove(id);
        return "redirect:/bookings";
    }
}
