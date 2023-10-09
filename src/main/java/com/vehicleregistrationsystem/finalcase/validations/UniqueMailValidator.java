package com.vehicleregistrationsystem.finalcase.validations;

import com.vehicleregistrationsystem.finalcase.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueMailValidator implements ConstraintValidator<UniqueMail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String mail, ConstraintValidatorContext context) {
        return !userRepository.existsUserByMail(mail);
    }
}
