package com.vehicleregistrationsystem.finalcase.responses;

import com.vehicleregistrationsystem.finalcase.enums.Cities;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private Cities city;
    private Date accountCreationDate;
    private List<VehicleResponseDto> vehicles;
}
