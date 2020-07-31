package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.AppRole;
import com.codegym.checkinhotel.model.AppUser;
import com.codegym.checkinhotel.service.role.IRoleService;
import com.codegym.checkinhotel.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IAppUserService userService;

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public String showAllHotel(Model model){
        model.addAttribute("users",userService.findAll());
        return "appuser/index";
    }

    @GetMapping("/create-user")
    public ModelAndView showFormLogin(){
        ModelAndView modelAndView = new ModelAndView("appuser/create");
        modelAndView.addObject("user",new AppUser());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView createUser(@ModelAttribute AppUser user){
        ModelAndView modelAndView = new ModelAndView("appuser/create");

        Optional<AppRole> role = roleService.findById(2L);
        role.ifPresent(user::setRole);

        userService.save(user);
        modelAndView.addObject("user",new AppUser());
        return modelAndView;
    }

    @GetMapping("/edit-user/{id}")
    public ModelAndView showFormEditUser(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("appuser/edit");
        modelAndView.addObject("user",userService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit-user")
    public ModelAndView editUser(@ModelAttribute AppUser appUser){
        ModelAndView modelAndView = new ModelAndView("redirect:/users");
        userService.save(appUser);
        modelAndView.addObject("user", appUser);
        return modelAndView;
    }

    @GetMapping("/delete-user/{id}")
    public String deleteCustomer (@PathVariable("id") Long id, RedirectAttributes model){
        Optional<AppUser> user = userService.findById(id);
        if(user.isPresent()){
            AppUser appUser = user.get();
            System.out.println(appUser.getRole());
            if (appUser.getRole().getName().equals("ROLE_ADMIN")){
                model.addFlashAttribute("messages","Can't delete ROLE_ADMIN");
            }else {
                appUser.setRole(null);
                userService.save(appUser);
                userService.remove(id);
            }
        }
        return "redirect:/users";
    }
}
