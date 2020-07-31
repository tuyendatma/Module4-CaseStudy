package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.AppUser;
import com.codegym.checkinhotel.model.City;
import com.codegym.checkinhotel.service.city.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private Environment environment;

    @GetMapping("/index")
    public ModelAndView showCity(){
        Iterable<City> cities = cityService.findAll();
        ModelAndView modelAndView = new ModelAndView("cities/index");
        modelAndView.addObject("cities",cities);
        return modelAndView;
    }

    @GetMapping("/create-city")
    public ModelAndView showFormCreateCity(){
        ModelAndView modelAndView = new ModelAndView("cities/create");
        modelAndView.addObject("city",new City());
        return modelAndView;
    }

    @PostMapping("/create-city")
    public ModelAndView createUser(@ModelAttribute City city){
        MultipartFile file = city.getImageFile();
        String filename = file.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.city").toString();
        try{
            FileCopyUtils.copy(file.getBytes(),new File(fileUpload+filename));
        }catch (IOException e){
            e.printStackTrace();
        }
        City city1 = new City(city.getName(),city.getDescription(),filename);
        cityService.save(city1);
        ModelAndView modelAndView = new ModelAndView("cities/create");
        modelAndView.addObject("city",new City());
        return modelAndView;
    }

//    @GetMapping("/edit/{id}")
//    public ModelAndView showEditCity(@PathVariable Long id){
//        ModelAndView modelAndView = new ModelAndView("cities/edit");
//        modelAndView.addObject("city",cityService.findById(id));
//        return modelAndView;
//    }
//
//    @PostMapping("/edit")
//    public ModelAndView editCity(@ModelAttribute City city){
//        ModelAndView modelAndView = new ModelAndView("cities/edit");
//        cityService.save(city);
//        modelAndView.addObject("city", city);
//        return modelAndView;
//    }

    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable Long id){
        cityService.remove(id);
        return "redirect:/city/index";
    }
}
