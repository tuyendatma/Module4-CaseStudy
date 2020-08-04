package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.AppUser;
import com.codegym.checkinhotel.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {
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

    @GetMapping("/about")
    public String about(Model model) {
        AppUser user  =userService.getUserByUserName(getPrincipal());
        if (user!=null){
//            model.addAttribute("name",user.getName());
            model.addAttribute("user",user);
        }
        return "home/about";
    }

    @GetMapping("/services")
    public String services(Model model) {
        AppUser user  =userService.getUserByUserName(getPrincipal());
        if (user!=null){
//            model.addAttribute("name",user.getName());
            model.addAttribute("user",user);
        }
        return "home/services";
    }

    @GetMapping("/blogs")
    public String blog(Model model) {
        AppUser user  =userService.getUserByUserName(getPrincipal());
        if (user!=null){
//            model.addAttribute("name",user.getName());
            model.addAttribute("user",user);
        }
        return "home/blog";
    }

    @GetMapping("/single-blog")
    public String singleBlog(Model model) {
        AppUser user  =userService.getUserByUserName(getPrincipal());
        if (user!=null){
//            model.addAttribute("name",user.getName());
            model.addAttribute("user",user);
        }
        return "home/single-blog";
    }

    @GetMapping("/contacts")
    public String contact(Model model) {
        AppUser user  =userService.getUserByUserName(getPrincipal());
        if (user!=null){
//            model.addAttribute("name",user.getName());
            model.addAttribute("user",user);
        }
        return "home/contact";
    }
}
