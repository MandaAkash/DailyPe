package com.example.dailype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dailype.model.Manager;
import com.example.dailype.repository.ManagerRepository;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;

    @PostMapping("/create_manager")
    public ResponseEntity<String> createManager(@RequestBody Manager newManager) {
        try {
            managerRepository.save(newManager);
            return new ResponseEntity<>("Manager created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
