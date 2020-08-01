package com.codegym.checkinhotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
    @GetMapping("/access-denied")
    private String accessDenied(){
        return "access-denied";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "index/login";
    }

    @GetMapping("/fail-login")
    public String failedLogin(){
        return "fail-login";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "index/register";
    }
}
