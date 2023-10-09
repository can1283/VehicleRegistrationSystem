package com.vehicleregistrationsystem.finalcase.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = {UniquePlateCodeValidator.class})
public @interface UniquePlateCode {
    String message() default "Plate code must be unique!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
