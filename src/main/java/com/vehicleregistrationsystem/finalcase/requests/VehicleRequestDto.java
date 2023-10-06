package com.vehicleregistrationsystem.finalcase.requests;

import lombok.Data;

@Data
public class VehicleRequestDto {
    private String name;
    private String brand;
    private String model;
    private String plateCode;
    private int modelYear;
}
