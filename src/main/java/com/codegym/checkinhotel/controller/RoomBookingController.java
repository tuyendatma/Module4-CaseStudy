package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.Hotel;
import com.codegym.checkinhotel.model.HotelDetails;
import com.codegym.checkinhotel.model.RoomBooking;
import com.codegym.checkinhotel.service.booking.RoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/booking")
public class RoomBookingController {
    @Autowired
    private RoomBookingService roomBookingService;

    @GetMapping
    public String showAllRoomBookings(Model model){
        model.addAttribute("roombooking",roomBookingService.findAll());
        return "booking/index";
    }

    @GetMapping("/create-roombooking")
    public String showCreateRoomBooking(Model model) {
        model.addAttribute("roombooking", new RoomBooking());
        return "booking/create";
    }

    @PostMapping("/create-roombooking")
    public String createRoomBooking(@ModelAttribute RoomBooking roomBooking, Model model){
        roomBookingService.save(roomBooking);
        model.addAttribute("roombooking",new RoomBooking());
        return "booking/create";
    }

    @GetMapping("/edit-roombooking/{id}")
    public String showEditRoomBooking(Model model, @PathVariable("id") Long id){
        model.addAttribute("roombooking",roomBookingService.findById(id));
        return "booking/edit";
    }

    @PostMapping("/edit-roombooking")
    public String editRoomBooking(@ModelAttribute("roombooking") RoomBooking roomBooking, Model model){
        roomBookingService.save(roomBooking);
        model.addAttribute("roombooking", roomBooking);
        return "redirect:/booking";
    }

    @GetMapping("/delete-roombooking/{id}")
    public String deleteRoomBooking (@PathVariable("id") Long id){
        roomBookingService.remove(id);
        return "redirect:/booking";
    }




}
