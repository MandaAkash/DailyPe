package com.example.dailype.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.dailype.model.Manager;

public interface ManagerRepository extends MongoRepository<Manager, String> {
    @Query("{ 'id' : ?0 }")
    Optional<Manager> findByCustomId(String id);
}
