package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repo.RoleRepo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepository;

    public RoleServiceImpl(RoleRepo roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getRolesSet() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> foundRole = roleRepository.findById(id);
        if (foundRole.isPresent()) {
            return foundRole.get();
        } else {
            throw new UsernameNotFoundException(String.format("Role with id - %s not found", id));
        }
    }
}
