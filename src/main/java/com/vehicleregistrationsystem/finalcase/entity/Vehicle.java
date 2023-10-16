package com.vehicleregistrationsystem.finalcase.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String model;

    @Column(unique = true)
    private String plateCode;
    private String vehiclesCreationDate;
    private int modelYear;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    public Vehicle(String name, String brand, String model, String plateCode, String vehiclesCreationDate, int modelYear, boolean active, User user) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.plateCode = plateCode;
        this.vehiclesCreationDate = vehiclesCreationDate;
        this.modelYear = modelYear;
        this.active = active;
        this.user = user;
    }
}
