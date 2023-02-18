package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repo.UserRepo;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminRoleController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @GetMapping("")
    public  String home(){
        return "adminhome";
    }
        @GetMapping("/userforAdmin")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();

        model.addAttribute("users", users);
        return "/userforAdmin";
    }

    @GetMapping("/add-user")
    public String add(@ModelAttribute("user") User user) {


        return "/add-user";
    }
    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        user.setActive(true);

        userService.addUser(user);
        return "redirect:/";
    }
    @GetMapping("/user_{id}/edit-user")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/edit-user";
    }

    @GetMapping("/user_{id}")
    public String update(@ModelAttribute("user")  User user) {

        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("delete/user_{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
