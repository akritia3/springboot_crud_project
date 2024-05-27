package com.example.crud.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class KYC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kycNumber;

    private Long userID;

    private String name;

}
