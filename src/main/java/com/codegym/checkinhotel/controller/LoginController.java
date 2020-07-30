package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.AppUser;
import com.codegym.checkinhotel.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
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

    @GetMapping("/edit-user/{id}")
    public ModelAndView showFormEditUser(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("appuser/edit");
        modelAndView.addObject("user",userService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit-user")
    public ModelAndView editUser(@ModelAttribute AppUser appUser){
        ModelAndView modelAndView = new ModelAndView("appuser/edit");
        userService.save(appUser);
        modelAndView.addObject("user", appUser);
        return modelAndView;
    }

}
