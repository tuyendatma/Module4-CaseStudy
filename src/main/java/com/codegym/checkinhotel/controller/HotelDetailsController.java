package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.HotelDetails;
import com.codegym.checkinhotel.service.hoteldetail.IHotelDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hoteldetails")
public class HotelDetailsController {
    @Autowired
    private IHotelDetailService hotelDetailService;

    @GetMapping
    public String showAllHotelDetail(Model model){
        model.addAttribute("hoteldetails",hotelDetailService.findAll());
        return "hoteldetail/list";
    }

    @GetMapping("/create-hoteldetail")
    public String showCreateHotelDetail(Model model) {
        model.addAttribute("hoteldetail", new HotelDetails());
        return "hoteldetail/create";
    }

    @PostMapping("/create-hoteldetail")
    public String createHotelDetail(@ModelAttribute HotelDetails hotelDetails, Model model){
        hotelDetailService.save(hotelDetails);
        model.addAttribute("hoteldetail",new HotelDetails());
        return "hoteldetail/create";
    }

    @GetMapping("/edit-hoteldetail/{id}")
    public String showEditHotelDetail(Model model, @PathVariable("id") Long id){
        model.addAttribute("hoteldetail",hotelDetailService.findById(id));
        return "hoteldetail/edit";
    }

    @PostMapping("/edit-hoteldetail")
    public String editCity(@ModelAttribute("hoteldetail") HotelDetails hotelDetails, Model model){
        hotelDetailService.save(hotelDetails);
        model.addAttribute("hoteldetail", hotelDetails);
        return "redirect:/hoteldetails";
    }

    @GetMapping("/delete-hoteldetail/{id}")
    public String deleteHotel (@PathVariable("id") Long id){
        hotelDetailService.remove(id);
        return "redirect:/hoteldetails";
    }

}
