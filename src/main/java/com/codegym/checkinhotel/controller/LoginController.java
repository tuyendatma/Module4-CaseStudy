package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.AppUser;
import com.codegym.checkinhotel.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
    @Autowired
    private IAppUserService userService;

    @GetMapping("/create-user")
    public ModelAndView showFormLogin(){
        ModelAndView modelAndView = new ModelAndView("appuser/create");
        modelAndView.addObject("appuser",new AppUser());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView createUser(@ModelAttribute AppUser appUser){
        userService.save(appUser);
        ModelAndView modelAndView = new ModelAndView("appuser/create");
        modelAndView.addObject("appuser",new AppUser());
        return modelAndView;
    }

}
