package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.City;
import com.codegym.checkinhotel.service.city.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private Environment environment;

    @GetMapping
    public String showAllCity(Model model) {
        model.addAttribute("cities", cityService.findAll());
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
    public String editCity(@ModelAttribute("city") City city, Model model){
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
    public String deleteCity (@PathVariable("id") Long id){
        cityService.remove(id);
        return "redirect:/cities";
    }
}



