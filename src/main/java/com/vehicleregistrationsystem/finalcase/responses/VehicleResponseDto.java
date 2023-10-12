package com.vehicleregistrationsystem.finalcase.responses;

import lombok.Data;

@Data
public class VehicleResponseDto {
    private Long id;
    private String name;
    private String brand;
    private String model;
    private String plateCode;
    private String vehiclesCreationDate;
    private int modelYear;
    private boolean active;
}
