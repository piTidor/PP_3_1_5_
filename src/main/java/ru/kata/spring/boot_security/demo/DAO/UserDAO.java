package ru.kata.spring.boot_security.demo.DAO;





import org.springframework.security.authentication.jaas.JaasAuthenticationCallbackHandler;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO  {

    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(Long id);
    void deleteUser(Long id);
    void updateUser(User user);

}
