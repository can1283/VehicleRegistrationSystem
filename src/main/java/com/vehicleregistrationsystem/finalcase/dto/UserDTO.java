package com.vehicleregistrationsystem.finalcase.dto;

import com.vehicleregistrationsystem.finalcase.model.entity.Vehicle;
import com.vehicleregistrationsystem.finalcase.model.enums.Cities;
import lombok.Data;

import java.util.List;

/*
        ! private UUID id;
        * private String userName;
        ! private String password;
        * private String firstName;
        * private String lastName;
        * private Cities city;
        ! private Date accountCreationDate;
        * private List<Vehicle> vehicles;
 */
@Data
public class UserDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private Cities city;
    private List<Vehicle> vehicles;
}
