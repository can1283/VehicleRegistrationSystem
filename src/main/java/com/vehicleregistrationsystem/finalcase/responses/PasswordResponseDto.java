package com.vehicleregistrationsystem.finalcase.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResponseDto {
    private Long id;
    private String password;
}
