package com.example.crud.controller;

import com.example.crud.entity.User;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.fetchAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/user/address/{id}")
    public User updateAddress(@PathVariable Long id, @RequestBody String address) {
        return userService.updateAddress(id, address);
    }

    @PutMapping("/user/phone/{id}")
    public User updatePhoneNumber(@PathVariable Long id, @RequestBody String phoneNumber) {
        return userService.updatePhoneNumber(id, phoneNumber);
    }
}

