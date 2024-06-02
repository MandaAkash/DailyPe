package com.example.dailype.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import  com.example.dailype.model.User;
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserId(String userId); 
    List<User> findByMobNum(String mobNum); 
}
