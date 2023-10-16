package com.vehicleregistrationsystem.finalcase.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRequestDto {
    @NotNull(message = "Password cannot be null!")
    @Size(min = 8, max = 16, message = "Password size must be between {min} and {max}!")
    @NotBlank(message = "Password is required!")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Password is not valid!")
    private String currentPassword;

    @NotNull(message = "Password cannot be null!")
    @Size(min = 8, max = 16, message = "Password size must be between {min} and {max}!")
    @NotBlank(message = "Password is required!")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Password is not valid!")
    private String newPassword;
}
