package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repo.UserRepo;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminRoleController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleService roleService;

//    @GetMapping("")
//    public  String home(){
//        return "24";
//    }
        @GetMapping("")
    public String getAllUsers(Model model, Principal principal) {
        List<User> users = userService.getAllUsers();
        User usersave = new User();
        model.addAttribute("roles", roleService.getRolesSet());
        model.addAttribute("usersave", usersave);
        model.addAttribute("userI",userRepo.findUserByUsername(principal.getName()));
        model.addAttribute("users", users);
        return "24";
    }

//    @GetMapping("/add-user")
//    public String add(@ModelAttribute("user") User user) {
//
//
//        return "/add-user";
//    }
    @GetMapping("adr")
    public String saveUser(@ModelAttribute("user") User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        userService.addUser(user);
        return "redirect:/admin";
    }
//    @GetMapping("/user_{id}/edit-user")
//    public String edit(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "/edit-user";
//    }

//    @GetMapping("/user_{id}")
//    public String update(@ModelAttribute("user")  User user) {
//
//        userService.updateUser(user);
//        return "redirect:/admin/userforAdmin";
//    }
//
//    @GetMapping("delete/user_{id}")
//    public String delete(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin/userforAdmin";
//    }
    @GetMapping("update/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam("roleIds") Set<Long> roleIds) {
        userService.setUserRoles(user, roleIds);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
