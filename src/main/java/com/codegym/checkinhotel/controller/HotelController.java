package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.Hotel;
import com.codegym.checkinhotel.service.hotel.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private IHotelService hotelService;

    @Autowired
    private Environment environment;

    @GetMapping
    public String showAllHotel(Model model){
        model.addAttribute("hotels",hotelService.findAll());
        return "hotel/index";
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
}
