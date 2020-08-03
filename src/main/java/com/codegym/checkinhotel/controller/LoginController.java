package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.AppUser;
import com.codegym.checkinhotel.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {
    @Autowired
    private IAppUserService userService;

    @GetMapping("/access-denied")
    private String accessDenied(){
        return "access-denied";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "home/login";
    }

    @GetMapping("/fail-login")
    public String failedLogin(){
        return "home/login";
    }

//    @GetMapping("/register")
//    public String registerPage(){
//        return "home/register";
//    }
}
