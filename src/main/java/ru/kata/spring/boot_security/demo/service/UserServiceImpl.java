package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repo.UserRepo;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService , UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepo userRepo;

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepo userRepo,RoleService roleService, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    @Override
    public void addUser(User user) {
        User userFromDB = userRepo.findUserByUsername(user.getUsername());

        if (userFromDB != null) {

        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

    }
//    @Transactional
//    public void addRole(Role user) {
//        entityManager.persist(user);
//    }

    @Transactional (readOnly = true)
    @Override

    public List<User> getAllUsers() {
        return userRepo.findAll();
//        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Transactional (readOnly = true)
    @Override
    public User getUserById(Long id) {
        Optional<User> foundUser = userRepo.findById(id);
        return foundUser.orElse(null);
    }


    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);;
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        if (!user.getPassword().equals(userRepo.getById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepo.save(user);
    }
    @Override
    public void setUserRoles(User user, Set<Long> roleIds) {
        user.setRoles(roleIds.stream()
                .map(roleService::findById)
                .collect(Collectors.toSet()));
    }

    public User findByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }


}

