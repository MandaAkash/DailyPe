package com.example.demo.service;

import com.example.demo.model.Manager;
import com.example.demo.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    public Optional<Manager> getManagerById(UUID managerId) {
        return managerRepository.findById(managerId);
    }

    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }
}
