package com.vehicleregistrationsystem.finalcase.requests;

import com.vehicleregistrationsystem.finalcase.validations.UniqueUserName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @NotNull(message = "Username cannot be null!")
    @NotBlank(message = "Username is required!")
    @Size(min = 4, max = 16, message = "Username size must be between {min} and {max}!")
    @UniqueUserName
    private String username;

    @NotNull(message = "First name cannot be null!")
    @Size(min = 2, max = 15, message = "First name size must be between {min} and {max}!")
    private String password;
}
