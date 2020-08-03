package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.*;
import com.codegym.checkinhotel.service.booking.IRoomBookingService;
import com.codegym.checkinhotel.service.city.ICityService;
import com.codegym.checkinhotel.service.hotel.IHotelService;
import com.codegym.checkinhotel.service.room.IRoomService;
import com.codegym.checkinhotel.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "booking/create";
    }

    @PostMapping("/create-booking")
    public String createRoomBooking(@ModelAttribute RoomBooking roomBooking, Model model){
        AppUser user = userService.getUserByUserName(getPrincipal());
        if (user != null){
            roomBooking.setUser(user);
        }
        roomBookingService.save(roomBooking);
        model.addAttribute("booking",new RoomBooking());
        return "booking/create";
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
