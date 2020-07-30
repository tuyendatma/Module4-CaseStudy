package com.codegym.checkinhotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {
    @GetMapping("/about")
    public String about() {
        return "index/about";
    }

    @GetMapping("/services")
    public String services() {
        return "index/services";
    }

    @GetMapping("/blogs")
    public String blog() {
        return "index/blog";
    }

    @GetMapping("/single-blog")
    public String singleBlog() {
        return "index/single-blog";
    }

    @GetMapping("/contacts")
    public String contact() {
        return "index/contact";
    }
}
