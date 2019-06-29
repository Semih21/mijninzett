package com.mijninzet.projectteamdrie.service;

import java.util.*;

import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    public UserServiceImp(BCryptPasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        System.out.println("gegevens User: " + user.getName() + "Roles: " + user.getRolesOfUser());
        user.setRolesOfUser(new ArrayList<>(user.getRolesOfUser()));
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isUserAlreadyPresent(User user) {

        boolean isUserAlreadyExists = false;
        User existingUser = userRepository.findByEmail(user.getEmail());
        // If user is found in database, then then user already exists.
        if (existingUser != null) {
            isUserAlreadyExists = true;
        }
        return isUserAlreadyExists;
    }


    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public User findById(int id) {
        Optional<User> result = userRepository.findById(id);
        User theUser;
        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new RuntimeException("Did not find Employee");
        }
        return theUser;
    }

    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }


}