package com.example.dailype.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "managers")
public class Manager {
    @Id
    private String mongoId; 

    @Field("id")
    private String id; // Custom id field

    private String fullName;
    private boolean active;

    // Getters and Setters
    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Manager{mongoId='" + mongoId + "', id='" + id + "', fullName='" + fullName + "', active=" + active + "}";
    }
}
