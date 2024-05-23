package com.example.crud.service;

import com.example.crud.entity.User;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class Implementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchAllUsers() {
        List<User> listOfUsers = userRepository.findAll();
        return listOfUsers;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    // TODO :  Phone Number with regex validations
    @Override
    public User updateUser(Long id, User user) {
        Optional<User> user1 = userRepository.findById(id);

        if (user1.isPresent()) {
            User originalUser = user1.get();

            if (Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName())) {
                originalUser.setName(user.getName());
            }
            if (Objects.nonNull(user.getAddress()) && !"".equalsIgnoreCase(user.getAddress())) {
                originalUser.setAddress(user.getAddress());
            }
            return userRepository.save(originalUser);
        }
        return null;
    }

    // TODO : Add exception
    @Override
    public String deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return "Deleted successfully";
        }
        return "No such entry in the database";
    }
}
