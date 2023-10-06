package com.vehicleregistrationsystem.finalcase.requests;

import com.vehicleregistrationsystem.finalcase.enums.Cities;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserRequestDto {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Cities city;
    private Date accountCreationDate;
    private List<VehicleRequestDto> vehicles;
}
