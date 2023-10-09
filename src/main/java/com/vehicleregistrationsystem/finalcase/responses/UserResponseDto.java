package com.vehicleregistrationsystem.finalcase.responses;

import com.vehicleregistrationsystem.finalcase.entity.User;
import com.vehicleregistrationsystem.finalcase.entity.Vehicle;
import com.vehicleregistrationsystem.finalcase.enums.Cities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String userName;
    private String mail;
    private String firstName;
    private String lastName;
    private Cities city;
    private String accountCreationDate;
    private List<VehicleResponseDto> vehicles;
}
