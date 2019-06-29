package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.comparator.UserNameComparator;
import com.mijninzet.projectteamdrie.model.entity.user.Role;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.RoleRepository;
import com.mijninzet.projectteamdrie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

@RequestMapping("/userDatatable")
public String goHome(Model model) {
    List<Role> rolelist = roleRepository.findAll();
    model.addAttribute("roles", rolelist);
    model.addAttribute("users", userService.getAllUsers());
    return "users2";
}

@RequestMapping(value="/update", method=RequestMethod.POST)
    public String update(@ModelAttribute("user") User theUser){
        userService.updateUser(theUser);
        return "redirect:/users/list";
    }

    @RequestMapping("/list")
    public String getAllUsers(Model model) {
        List<Role> rolelist = roleRepository.findAll();
        List<User> userList=userService.getAllUsers();
        Collections.sort(userList,new UserNameComparator());
        model.addAttribute("roles", rolelist);
        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        User theUser = new User();
        List<Role> rolelist = roleRepository.findAll();
        model.addAttribute("roles", rolelist);
        model.addAttribute("user", theUser);
        // return "user-form";
        return "newUser";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int theId, Model model) {
        List<Role> rolelist = roleRepository.findAll();
        model.addAttribute("roles", rolelist);
        User theUser = userService.findById(theId);
        model.addAttribute("user", theUser);

        return "updateUser";

    }

    //
//
//    @RequestMapping("/users/{id}")
//    public Optional<User> getUser(@PathVariable int id){
//       return userService.getUser(id);
//    }
//
//    @RequestMapping(method = RequestMethod.POST,value = "/users")
//    public void addUser(User user){
//
//        userService.addUser(user);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT,value = "/users/{id}")
//    public void updateUser(int id,User user){
//        userService.updateUser(id,user);
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE,value = "/users/{id}")
//    public void deleteUser(int id){
//
//        userService.deleteUser(id);
//    }
    @GetMapping("/delete")
    public String delete(@RequestParam("userId") int theId, Model model) {
        userService.deleteUserById(theId);

        return "redirect:/users/list";

    }
//    @RequestMapping("/login")
//    public String getLoginForm(){
//
//        return "login";
//    }
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public String login( Model model,
//                         @ModelAttribute("user") User user ) {
//
//
//        List<User> users = userService.getAllUsers();
//
//        for (int i = 0; i < users.size(); i++) {
//
//            String username = users.get(i).getUsername();
//            String password = users.get(i).getPassword();
//
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
//                return "hello";
//            }
//            model.addAttribute("invalidCredentials", true);
//        }
//        return "login";
//    }
//}
}