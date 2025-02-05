package com.example.crud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


import java.util.List;
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String name;

    private String address;

    private String phoneNumber;
}
