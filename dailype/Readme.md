### README.md

# DailyPE Application

## Introduction
This application is built using Spring Boot and MongoDB. It manages users and their associated managers. 

## API Endpoints

### User Endpoints
- `POST /api/users/create_user`: Create a new user.
- `GET /api/users/get_user`: Retrieve user(s) based on `user_id` or `mob_num`. If no parameters are provided, it retrieves all users.
- `DELETE /api/users/delete_user`: Delete a user based on `user_id` or `mob_num`.
- `POST /api/users/update_user`: Update user(s) details.

### Manager Endpoints
- `POST /api/managers/create_manager`: Create a new manager.

## Database Schema

### Manager Schema
The `Manager` schema consists of the following fields:

| Field Name | Data Type | Description |
|------------|------------|-------------|
| `mongoId`  | `String`   | Unique identifier for the manager (MongoDB ID). |
| `id`       | `String`   | Custom ID for the manager. |
| `fullName` | `String`   | Full name of the manager. |
| `active`   | `boolean`  | Indicates if the manager is currently active. |

### Manager Model Class

```java
package com.example.dailype.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "managers")
public class Manager {
    @Id
    private String mongoId; // This will be mapped to MongoDB's _id field

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
```

### Test Data for Managers

Below is the test data used for managers:

```json
[
    {
        "id": "1",
        "fullName": "John Doe",
        "active": true
    },
    {
        "id": "2",
        "fullName": "Jane Smith",
        "active": true
    },
]
```

## User Schema
The `User` schema consists of the following fields:

| Field Name | Data Type | Description |
|------------|------------|-------------|
| `id`       | `String`   | Unique identifier for the user (MongoDB ID). |
| `userId`   | `String`   | Custom ID for the user. |
| `fullName` | `String`   | Full name of the user. |
| `mobNum`   | `String`   | Mobile number of the user. |
| `panNum`   | `String`   | PAN number of the user. |
| `managerId`| `String`   | Custom ID of the user's manager. |
| `createdAt`| `LocalDateTime` | Timestamp of when the user was created. |
| `updatedAt`| `LocalDateTime` | Timestamp of when the user was last updated. |
| `isActive` | `boolean`  | Indicates if the user is currently active. |

## User Model Class

```java
package com.example.dailype.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id ; // Generate UUID v4
    private String userId= UUID.randomUUID().toString();
    private String fullName;
    private String mobNum;
    private String panNum;
    private String managerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive = true;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobNum() {
        return mobNum;
    }

    public void setMobNum(String mobNum) {
        this.mobNum = mobNum;
    }

    public String getPanNum() {
        return panNum;
    }

    public void setPanNum(String panNum) {
        this.panNum = panNum.toUpperCase(); // Convert to uppercase
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
```

## Setting up the Database

Ensure you have MongoDB running locally or remotely and update the application configuration accordingly.

## Running the Application

1. Clone the repository.
2. Navigate to the project directory.
3. Run the application using your preferred method (e.g., `mvn spring-boot:run`).

## Conclusion

This README provides the necessary details to understand the schemas for both users and managers, as well as the test data to help with setup and testing of the application.