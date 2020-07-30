package com.codegym.checkinhotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
    @GetMapping("/access-denied")
    private String accessDenied(){
        return "access-denied";
    }

    @GetMapping("/login")
    private String loginPage(){
        return "index/login";
    }

    @GetMapping("/fail-login")
    private String failedLogin(){
        return "fail-login";
    }
}
