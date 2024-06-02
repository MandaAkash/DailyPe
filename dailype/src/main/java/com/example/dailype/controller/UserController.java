package com.example.dailype.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dailype.model.User;
import com.example.dailype.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@RequestBody User newUser) {
        try {
            userService.createUser(newUser);
            return new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_user")
    public ResponseEntity<Object> getUser(@RequestParam Optional<String> user_id, @RequestParam Optional<String> mob_num) {
        if (user_id.isPresent()) {
            Optional<User> user = userService.getUserById(user_id.get());
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No user found with the provided user_id.", HttpStatus.NOT_FOUND);
            }
        } else if (mob_num.isPresent()) {
            List<User> users = userService.getUsersByMobNum(mob_num.get());
            if (!users.isEmpty()) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No users found with the provided mob_num.", HttpStatus.NOT_FOUND);
            }
        }
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/delete_user")
    public ResponseEntity<String> deleteUser(@RequestParam Optional<String> user_id, @RequestParam Optional<String> mob_num) {
        if (user_id.isPresent()) {
            userService.deleteUserById(user_id.get());
            return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
        } else if (mob_num.isPresent()) {
            List<User> usersToDelete = userService.getUsersByMobNum(mob_num.get());
            if (!usersToDelete.isEmpty()) {
                userService.deleteUserByMobNum(mob_num.get());
                return new ResponseEntity<>("Users deleted successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No users found with the provided mobile number.", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Please provide either user_id or mob_num.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update_user")
    public ResponseEntity<String> updateUser(@RequestBody Map<String, Object> request) {
        @SuppressWarnings("unchecked")
        List<String> user_ids = (List<String>) request.get("user_ids");
        @SuppressWarnings("unchecked")
        Map<String, Object> update_data = (Map<String, Object>) request.get("update_data");
        List<User> users = userService.findUsersByIds(user_ids);
        if (users.isEmpty()) {
            return new ResponseEntity<>("No users found.", HttpStatus.NOT_FOUND);
        }
        for (User user : users) {
            if (update_data.containsKey("fullName")) user.setFullName((String) update_data.get("fullName"));
            if (update_data.containsKey("mobNum")) user.setMobNum((String) update_data.get("mobNum"));
            if (update_data.containsKey("panNum")) user.setPanNum((String) update_data.get("panNum"));
            if (update_data.containsKey("managerId")) user.setManagerId((String) update_data.get("managerId"));
            userService.updateUser(user);
        }
        return new ResponseEntity<>("Users updated successfully.", HttpStatus.OK);
    }
}
