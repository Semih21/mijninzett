package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(User user);

    User findByEmail(String email);

    boolean isUserAlreadyPresent(User user);

    List<User> getAllUsers();

    Optional<User> getUser(int id);

    void updateUser(User user);

    void deleteUserById(int theId);

    User findById(int theId);


}
