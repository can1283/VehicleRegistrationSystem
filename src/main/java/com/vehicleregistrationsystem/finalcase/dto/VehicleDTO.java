package com.vehicleregistrationsystem.finalcase.dto;

import lombok.Data;

/*
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
 */
@Data
public class VehicleDTO {
    private String name;
    private String brand;
    private String model;
    private String plateCode;
    private String modelYear;
}
