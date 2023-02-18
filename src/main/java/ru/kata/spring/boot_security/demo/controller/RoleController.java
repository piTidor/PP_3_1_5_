package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repo.UserRepo;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping()
public class RoleController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo  ;

    @GetMapping("/userPage")
    public String home(Principal principal, Model model) {

        model.addAttribute("users",userRepo.findUserByUsername(principal.getName()));
        System.out.println(userRepo.findUserByUsername(principal.getName()));
        return "/users";
    }

    @GetMapping("/user")
    public String page(){
        return "home";
    }

        @GetMapping("/registration")
    public String add(@ModelAttribute("user") User user) {


        return "/registration";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        user.setActive(true);

        userService.addUser(user);
        return "redirect:/home";
    }
}
