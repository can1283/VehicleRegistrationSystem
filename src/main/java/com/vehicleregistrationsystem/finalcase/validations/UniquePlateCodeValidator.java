package com.vehicleregistrationsystem.finalcase.validations;

import com.vehicleregistrationsystem.finalcase.repository.VehicleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniquePlateCodeValidator implements ConstraintValidator<UniquePlateCode, String> {

    private final VehicleRepository vehicleRepository;

    @Override
    public boolean isValid(String plateCode, ConstraintValidatorContext context) {
        return !vehicleRepository.existsUserByPlateCode(plateCode);
    }
}
