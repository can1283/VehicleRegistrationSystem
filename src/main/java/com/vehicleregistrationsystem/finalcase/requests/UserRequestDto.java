package com.vehicleregistrationsystem.finalcase.requests;

import com.vehicleregistrationsystem.finalcase.enums.Cities;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotNull(message = "Username cannot be null!")
    @NotBlank(message = "Username is required!")
    @Size(min = 4, max = 16, message = "Username size must be between {min} and {max}!")
    private String userName;

    @NotNull(message = "Mail cannot be null!")
    @NotBlank(message = "Mail is required!")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Mail is not valid!")
    private String mail;

    @NotNull(message = "Password cannot be null!")
    @Size(min = 8, max = 16, message = "Password size must be between {min} and {max}!")
    @NotBlank(message = "Password is required!")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Password is not valid!")
    private String password;

    @NotNull(message = "First name cannot be null!")
    @Size(min = 2, max = 15, message = "First name size must be between {min} and {max}!")
    private String firstName;

    @NotNull(message = "Last name cannot be null!")
    @Size(min = 2, max = 15, message = "Last name size must be between {min} and {max}!")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Nullable
    private Cities city;

    @NotNull(message = "Vehicles cannot be null!")
    private List<VehicleRequestDto> vehicles;
}
