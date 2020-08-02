package com.codegym.checkinhotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {
    @GetMapping("/about")
    public String about() {
        return "home/about";
    }

    @GetMapping("/services")
    public String services() {
        return "home/services";
    }

    @GetMapping("/blogs")
    public String blog() {
        return "home/blog";
    }

    @GetMapping("/single-blog")
    public String singleBlog() {
        return "home/single-blog";
    }

    @GetMapping("/contacts")
    public String contact() {
        return "home/contact";
    }
}
