//package ru.kata.spring.boot_security.demo.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.model.Role;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//import java.util.*;
//
//
//@Controller
//@RequestMapping("/")
//public class UserController {
//
//
//
//    private final UserService userService;
//
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/")
//    public  String home(){
//        return "home";
//    }
//    @GetMapping("/users")
//    public String getAllUsers(Model model) {
//        List<User> users = userService.getAllUsers();
//
//        model.addAttribute("users", users);
//        return "/users";
//    }
//
//    @GetMapping("/add-user")
//    public String add(@ModelAttribute("user") User user) {
//
//
//        return "/add-user";
//    }
//
//    @PostMapping()
//    public String saveUser(@ModelAttribute("user") User user) {
//        user.setActive(true);
//
//        userService.addUser(user);
//        return "redirect:/";
//    }
//
//    @GetMapping("/user_{id}/edit-user")
//    public String edit(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "/edit-user";
//    }
//
//    @GetMapping("/user_{id}")
//    public String update(@ModelAttribute("user")  User user) {
//
//        userService.updateUser(user);
//        return "redirect:/";
//    }
//
//    @GetMapping("delete/user_{id}")
//    public String delete(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/";
//    }
//}
