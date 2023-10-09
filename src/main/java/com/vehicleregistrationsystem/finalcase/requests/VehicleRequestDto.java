package com.vehicleregistrationsystem.finalcase.requests;

import com.vehicleregistrationsystem.finalcase.validations.UniquePlateCode;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequestDto {

    @Nullable
    @Size(min = 2, max = 16, message = "Name size must be between {min} and {max}!")
    private String name;

    @NotNull(message = "Brand cannot be null!")
    @Size(min = 1, max = 16, message = "Brand size must be between {min} and {max}!")
    private String brand;

    @NotNull(message = "Model cannot be null!")
    @Size(min = 1, max = 16, message = "Model size must be between {min} and {max}!")
    private String model;

    @NotNull(message = "Model year cannot be null!")
    private int modelYear;

    @NotNull(message = "Plate code cannot be null!")
    @Size(min = 6, max = 8, message = "Plate code size must be between {min} and {max}!")
    @Pattern(regexp = "^(?=.{1,8}$)([0-9]{2}|[01]{2})([A-Za-z]{2,})([0-9]{2,})$", message = "Plate code is not valid!")
    @UniquePlateCode
    private String plateCode;

    @NotNull(message = "Active status cannot be null!")
    @AssertTrue
    private boolean active;
}
