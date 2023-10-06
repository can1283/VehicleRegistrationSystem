package com.vehicleregistrationsystem.finalcase.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String plateCode;
    private int modelYear;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    public Vehicle(String name, String brand, String model, String plateCode, int modelYear, User user) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.plateCode = plateCode;
        this.modelYear = modelYear;
        this.user = user;
    }
}
