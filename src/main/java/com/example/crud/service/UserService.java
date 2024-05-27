package com.example.crud.service;

import com.example.crud.ExceptionHandler.InvalidRequestException;
import com.example.crud.ExceptionHandler.NotFoundException;
import com.example.crud.entity.User;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        if (Objects.nonNull(user.getPhoneNumber())) {
            if (!user.getPhoneNumber().matches("^[789]\\d{9}$")) {
                throw new InvalidRequestException("Phone number is invalid");
            }
        }
        return userRepository.save(user);
    }

    public List<User> fetchAllUsers() {
        List<User> listOfUsers = userRepository.findAll();
        return listOfUsers;
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        return user.orElse(null);
    }

    public User updateUser(Long id, User user) {
        Optional<User> originalUserEntry = userRepository.findById(id);

        if (originalUserEntry.isEmpty()) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        User originalUser = originalUserEntry.get();

        if (Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName())) {
            originalUser.setName(user.getName());
        }
        if (Objects.nonNull(user.getAddress()) && !"".equalsIgnoreCase(user.getAddress())) {
            originalUser.setAddress(user.getAddress());
        }
        if (Objects.nonNull(user.getPhoneNumber())){
            if (!user.getPhoneNumber().matches("^[789]\\d{9}$")) {
                throw new InvalidRequestException("Phone number is invalid");
            }
            originalUser.setPhoneNumber(user.getPhoneNumber());
        }
        return userRepository.save(originalUser);
    }

    public User updateAddress(Long id, String address) {
        Optional<User> originalUserEntry = userRepository.findById(id);

        if (originalUserEntry.isEmpty()) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        User originalUser = originalUserEntry.get();
        if (!"".equalsIgnoreCase(address)) {
            originalUser.setAddress(address);
        } else {
            throw new InvalidRequestException("Address cannot be empty");
        }
        return userRepository.save(originalUser);
    }

    public User updatePhoneNumber(Long id, String phoneNumber) {
        Optional<User> originalUserEntry = userRepository.findById(id);

        if (originalUserEntry.isEmpty()) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        User originalUser = originalUserEntry.get();
        if (Objects.isNull(phoneNumber) || "".equalsIgnoreCase(phoneNumber)) {
            throw new InvalidRequestException("Phone Number cannot be empty");
        }
        else if (!phoneNumber.matches("^[789]\\d{9}$")) {
            throw new InvalidRequestException("Phone number is invalid");
        }
        else {
            originalUser.setPhoneNumber(phoneNumber);
        }
        return userRepository.save(originalUser);
    }

    public String deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(id);
        return "Deleted successfully";
    }
}
