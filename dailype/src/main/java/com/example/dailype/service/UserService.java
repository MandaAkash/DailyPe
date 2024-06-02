package com.example.dailype.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dailype.model.Manager;
import com.example.dailype.model.User;
import com.example.dailype.repository.ManagerRepository;
import com.example.dailype.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManagerRepository managerRepository;

    public User createUser(User newUser) {
        validateUser(newUser);
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());
        newUser.setActive(true);
        newUser.setUserId(UUID.randomUUID().toString());
        return userRepository.save(newUser);
    }

    private void validateUser(User user) {
        if (user.getFullName() == null || user.getFullName().isEmpty()) {
            throw new IllegalArgumentException("Full name must not be empty.");
        }

        String mobNum = user.getMobNum();
        if (mobNum == null || !mobNum.matches("^\\+?\\d{10,12}$")) {
            throw new IllegalArgumentException("Invalid mobile number.");
        }
        if (mobNum.startsWith("+91")) {
            mobNum = mobNum.substring(3);
        } else if (mobNum.startsWith("0")) {
            mobNum = mobNum.substring(1);
        }
        user.setMobNum(mobNum);
        String panNum = user.getPanNum();
        if (panNum == null || !panNum.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
            throw new IllegalArgumentException("Invalid PAN number.");
        }
        user.setPanNum(panNum.toUpperCase());

        Optional<Manager> managerOptional = managerRepository.findByCustomId(user.getManagerId());

        if (!managerOptional.isPresent() || !managerOptional.get().isActive()) {
            throw new IllegalArgumentException("Manager ID must be valid and belong to an active manager.");
        }
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findByUserId(userId);
    }

    public List<User> getUsersByMobNum(String mobNum) {
        return userRepository.findByMobNum(mobNum);
    }

    public void deleteUserById(String userId) {
        userRepository.findByUserId(userId).ifPresent(userRepository::delete);
    }

    public void deleteUserByMobNum(String mobNum) {
        List<User> users = userRepository.findByMobNum(mobNum);
        if (!users.isEmpty()) {
            userRepository.deleteAll(users);
        }
    }

    public void updateUser(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        validateUser(user); // Ensure validation when updating

        Optional<User> existingUserOptional = userRepository.findByUserId(user.getUserId());
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            // Update only if the user exists
            existingUser.setFullName(user.getFullName());
            existingUser.setMobNum(user.getMobNum());
            existingUser.setPanNum(user.getPanNum());
            existingUser.setManagerId(user.getManagerId());
            userRepository.save(existingUser);
        } else {
            // Handle case when user is not found
            throw new IllegalArgumentException("User with userId " + user.getUserId() + " not found.");
        }
    }

    public List<User> findUsersByIds(List<String> userIds) {
        return userRepository.findAllById(userIds);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
