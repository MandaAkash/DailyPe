package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        user.setUserId(UUID.randomUUID());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(null);
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByMobNum(String mobNum) {
        return userRepository.findByMobNum(mobNum);
    }

    public List<User> getUsersByManagerId(UUID managerId) {
        return userRepository.findByManagerId(managerId);
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    public void deleteUserByMobNum(String mobNum) {
        userRepository.findByMobNum(mobNum).ifPresent(user -> userRepository.deleteById(user.getUserId()));
    }

    public User updateUser(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
}
