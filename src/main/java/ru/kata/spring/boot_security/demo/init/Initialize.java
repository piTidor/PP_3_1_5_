package ru.kata.spring.boot_security.demo.init;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repo.RoleRepo;
import ru.kata.spring.boot_security.demo.repo.UserRepo;


import javax.annotation.PostConstruct;

@Component
public class Initialize {
    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Initialize(UserRepo userRepository, RoleRepo roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void postConstruct(){

//        User admin = new User();
//        admin.setName("admin");
//
//        admin.setProf("admin");
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("admin"));
//
//
//
//
//        userRepository.save(admin);

    }
}
