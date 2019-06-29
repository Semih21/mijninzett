package com.mijninzet.projectteamdrie.controller;


import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path="/userss", method= RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @RequestMapping(value = "/userss/{id}", method = RequestMethod.GET)
    public Optional<User> getUserById(@PathVariable("id") int id){
        return userService.getUser(id);
    }

}
