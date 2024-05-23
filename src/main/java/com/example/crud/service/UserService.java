package com.example.crud.service;

import com.example.crud.entity.User;

import java.util.List;

// TODO : Interface vs class
public interface UserService {

    User addUser(User user);

    List<User> fetchAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, User user);

    String deleteUser(Long id);
}
