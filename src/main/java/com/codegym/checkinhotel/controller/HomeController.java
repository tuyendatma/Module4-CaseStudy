package com.codegym.checkinhotel.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
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

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("user",getPrincipal());
        return "home";
    }
}
