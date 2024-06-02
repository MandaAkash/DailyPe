package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create_user")
    public ResponseEntity<?> createUser(@Valid @RequestBody User userInput) {
        User createdUser = userService.createUser(userInput);
        return ResponseEntity.ok().body(createdUser);
    }

    @PostMapping("/get_users")
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/delete_user")
    public ResponseEntity<?> deleteUser(@RequestBody Map<String, UUID> userInput) {
        UUID userId = userInput.get("user_id");
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("User deleted successfully.");
    }

    @PostMapping("/update_user")
    public ResponseEntity<?> updateUser(@RequestBody User userInput) {
        User updatedUser = userService.updateUser(userInput);
        return ResponseEntity.ok().body(updatedUser);
    }
}
