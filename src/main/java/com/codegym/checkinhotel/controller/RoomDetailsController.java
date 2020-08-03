package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.RoomDetails;
import com.codegym.checkinhotel.service.roomdetail.IRoomDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roomdetails")
public class RoomDetailsController {
    @Autowired
    private IRoomDetailService roomDetailService;

    @GetMapping
    public String showAllRoomDetail(Model model){
        model.addAttribute("roomdetails",roomDetailService.findAll());
        return "roomdetail/list";
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
        return "redirect:/roomdetails";
    }

    @GetMapping("/delete-roomdetail/{id}")
    public String deleteRoomDetail (@PathVariable("id") Long id){
        roomDetailService.remove(id);
        return "redirect:/roomdetails";
    }





}
