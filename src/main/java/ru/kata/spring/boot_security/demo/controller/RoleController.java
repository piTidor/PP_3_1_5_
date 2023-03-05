package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.repo.UserRepo;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping()
public class RoleController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo  ;

    @GetMapping("/user")
    public String home(Principal principal, Model model) {

        model.addAttribute("users",userRepo.findUserByUsername(principal.getName()));
        System.out.println(userRepo.findUserByUsername(principal.getName()));
        return "user";
    }


}
