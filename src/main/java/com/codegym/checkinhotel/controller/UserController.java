package com.codegym.checkinhotel.controller;

import com.codegym.checkinhotel.model.AppRole;
import com.codegym.checkinhotel.model.AppUser;
import com.codegym.checkinhotel.service.role.IRoleService;
import com.codegym.checkinhotel.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IAppUserService userService;

    @Autowired
    private IRoleService roleService;

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

    @ModelAttribute("roles")
    private Iterable<AppRole> roles() {
        return roleService.findAll();
    }

    protected List<String> getRoles() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = new ArrayList<>();
        if (principal instanceof UserDetails) {
            Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();
            //sout authorities [Role [id=1, name=ROLE_ADMIN]]
            for (GrantedAuthority authority : authorities) {
                roles.add(authority.getAuthority());
            }
        }
        return roles;
    }

    @GetMapping
    public String showAllHotel(Model model){
        model.addAttribute("users",userService.findAll());
        AppUser user  =userService.getUserByUserName(getPrincipal());
        if (user!=null){
            model.addAttribute("user",user);
        }
        return "appuser/fake";
    }

    @GetMapping("/create-user")
    public ModelAndView showFormLogin() {
        ModelAndView modelAndView = new ModelAndView("home/register");
        modelAndView.addObject("user", new AppUser());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView createUser(@ModelAttribute AppUser user, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<AppRole> role;
        if (user != null) {
            Iterable<AppUser> users = userService.findAll();
            if (!checkEmailAndUsername(user, users)) {
                modelAndView.setViewName("home/register");
                model.addAttribute("user", user);
                model.addAttribute("messages", "Email or username already in use, Please try again!");
                return modelAndView;
            } else if (getRoles().contains("ROLE_ADMIN")) {
                modelAndView.setViewName("home/register");
            } else {
                modelAndView.setViewName("home/login");
                role = roleService.findById(2L);
                role.ifPresent(user::setRole);
            }
        }
        userService.save(user);
        modelAndView.addObject("user", new AppUser());
        return modelAndView;
    }

    private boolean checkEmailAndUsername(AppUser user, Iterable<AppUser> users) {
        for (AppUser user1 : users) {
            if (user1.getEmail().equals(user.getEmail()) || user1.getUsername().equals(user.getUsername())) {
                return false;
            }
        }
        return true;
    }

    @GetMapping("/view-user/{id}")
    public ModelAndView showViewUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("appuser/info");
        modelAndView.addObject("user", userService.findById(id));
        return modelAndView;
    }

    @GetMapping("/edit-user/{id}")
    public ModelAndView showFormEditUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("appuser/edit");
        modelAndView.addObject("user", userService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit-user")
    public ModelAndView editUser(@ModelAttribute AppUser appUser) {
        ModelAndView modelAndView = new ModelAndView("redirect:/users");
        userService.save(appUser);
        modelAndView.addObject("user", appUser);
        return modelAndView;
    }

    @GetMapping("/delete-user/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, RedirectAttributes model) {
        Optional<AppUser> user = userService.findById(id);
        if (user.isPresent()) {
            AppUser appUser = user.get();
            System.out.println(appUser.getRole());
            if (appUser.getRole().getName().equals("ROLE_ADMIN")) {
                model.addFlashAttribute("messages", "Can't delete ROLE_ADMIN");
            } else {
                appUser.setRole(null);
                userService.save(appUser);
                userService.remove(id);
            }
        }
        return "redirect:/users";
    }
}
