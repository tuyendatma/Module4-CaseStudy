package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.AppUser;
import com.codegym.checkinhotel.model.Hotel;
import com.codegym.checkinhotel.model.Room;
import com.codegym.checkinhotel.model.RoomDetails;
import com.codegym.checkinhotel.service.hotel.IHotelService;
import com.codegym.checkinhotel.service.room.IRoomService;
import com.codegym.checkinhotel.service.roomdetail.IRoomDetailService;
import com.codegym.checkinhotel.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @Autowired
    private Environment environment;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IRoomDetailService roomDetailService;

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

    @ModelAttribute("hotels")
    public Iterable<Hotel> hotels(){
        return hotelService.findAll();
    }

    @ModelAttribute("roomDetails")
    public Iterable<RoomDetails> roomDetails(){
        return roomDetailService.findAll();
    }

    @GetMapping
    public String showAllRooms(Model model){
        model.addAttribute("rooms",roomService.findAll());
        AppUser user  =userService.getUserByUserName(getPrincipal());
        if (user!=null){
            model.addAttribute("user",user);
        }
        return "room/faker";
    }

    @GetMapping("/create-room")
    public String showCreateRoom(Model model) {
        model.addAttribute("room", new Room());
        return "room/create";
    }

    @PostMapping("/create-room")
    public String createRoom(@ModelAttribute Room room, Model model) {
        MultipartFile file = room.getImageFile();
        String fileName = file.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.room").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        room.setImage(fileName);
        roomService.save(room);
        model.addAttribute("room", new Room());
        return "room/create";
    }

    @GetMapping("/edit-room/{id}")
    public String showEditRoom(Model model, @PathVariable("id") Long id){
        model.addAttribute("room",roomService.findById(id));
        return "room/edit";
    }

    @PostMapping("/edit-room")
    public String editCity(@ModelAttribute("room") Room room, Model model){
        MultipartFile file = room.getImageFile();
        String fileName = file.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.room").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        room.setImage(fileName);
        roomService.save(room);
        model.addAttribute("room", room);
        return "redirect:/rooms";
    }

    @GetMapping("/delete-room/{id}")
    public String deleteRoom (@PathVariable("id") Long id){
        roomService.remove(id);
        return "redirect:/rooms";
    }
}
