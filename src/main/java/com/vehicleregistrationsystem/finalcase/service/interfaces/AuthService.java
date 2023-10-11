package com.vehicleregistrationsystem.finalcase.service.interfaces;

import com.vehicleregistrationsystem.finalcase.requests.LoginRequestDto;
import com.vehicleregistrationsystem.finalcase.requests.RegisterRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.UserResponseDto;

public interface AuthService {
    UserResponseDto register(RegisterRequestDto registerRequestDto);
    UserResponseDto login(LoginRequestDto loginRequestDto);

}
