package com.vehicleregistrationsystem.finalcase.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String brand;
    private String model;
    private String plateCode;
    private String modelYear;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    public Vehicle(String name, String brand, String model, String plateCode, String modelYear, User user) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.plateCode = plateCode;
        this.modelYear = modelYear;
        this.user = user;
    }
}
