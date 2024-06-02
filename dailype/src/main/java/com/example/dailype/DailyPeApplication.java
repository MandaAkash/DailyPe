package com.example.dailype;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.dailype.repository")
public class DailyPeApplication {
    public static void main(String[] args) {
        SpringApplication.run(DailyPeApplication.class, args);
    }
}