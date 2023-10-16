package com.vehicleregistrationsystem.finalcase.responses;

import com.vehicleregistrationsystem.finalcase.enums.Cities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String accountCreationDate;
    private String userName;
    private String mail;
    private String firstName;
    private String lastName;
    private Cities city;
    private List<VehicleResponseDto> vehicles;
}
