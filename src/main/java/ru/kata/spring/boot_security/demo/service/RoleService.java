package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleService {
    Set<Role> getRolesSet();

    Optional<Role> findByName(String name);

    Role findById(Long id);
}
