package com.example.demo.model;

import lombok.Data;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String department;
}
