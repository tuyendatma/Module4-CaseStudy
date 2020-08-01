package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.HotelDetails;
import com.codegym.checkinhotel.model.RoomDetails;
import com.codegym.checkinhotel.service.roomdetail.IRoomDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roomdetail")
public class RoomDetailsController {
    @Autowired
    private IRoomDetailService roomDetailService;

    @GetMapping
    public String showAllRoomDetail(Model model){
        model.addAttribute("roomdetails",roomDetailService.findAll());
        return "roomdetail/index";
    }

    @GetMapping("/create-roomdetail")
    public String showCreateRoomDetail(Model model) {
        model.addAttribute("roomdetail", new RoomDetails());
        return "roomdetail/create";
    }

    @PostMapping("/create-roomdetail")
    public String createRoomDetail(@ModelAttribute RoomDetails roomDetails, Model model){
        roomDetailService.save(roomDetails);
        model.addAttribute("roomdetail",new RoomDetails());
        return "roomdetail/create";
    }

    @GetMapping("/edit-roomdetail/{id}")
    public String showEditRoomDetail(Model model, @PathVariable("id") Long id){
        model.addAttribute("roomdetail",roomDetailService.findById(id));
        return "roomdetail/edit";
    }

    @PostMapping("/edit-roomdetail")
    public String editRoomDetail(@ModelAttribute("roomdetail") RoomDetails roomDetails, Model model){
        roomDetailService.save(roomDetails);
        model.addAttribute("roomdetail", roomDetails);
        return "redirect:/roomdetail";
    }

    @GetMapping("/delete-roomdetail/{id}")
    public String deleteRoomDetail (@PathVariable("id") Long id){
        roomDetailService.remove(id);
        return "redirect:/roomdetail";
    }





}
