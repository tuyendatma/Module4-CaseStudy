package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.AppUser;
import com.codegym.checkinhotel.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/home")
public class HomeController {
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

    @GetMapping
    public String homePage(Model model){
        AppUser user  =userService.getUserByUserName(getPrincipal());
        if (user!=null){
            model.addAttribute("user",user);
        }
        return "home/index";
    }

//    @GetMapping("/user")
//    public String homeUserPage(Model model){
//        model.addAttribute("user",getPrincipal());
//        return "home/index";
//    }
//
//    @GetMapping("/admin")
//    public String homeAdminPage(Model model){
//        model.addAttribute("user",getPrincipal());
//        return "home/index";
//    }
}
