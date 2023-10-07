package com.vehicleregistrationsystem.finalcase.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomException {
    private String path;
    private LocalDateTime dateTime;
    private String message;
}
