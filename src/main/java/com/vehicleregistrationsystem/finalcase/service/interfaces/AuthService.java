package com.vehicleregistrationsystem.finalcase.service.interfaces;

import com.vehicleregistrationsystem.finalcase.requests.LoginRequestDto;
import com.vehicleregistrationsystem.finalcase.requests.PasswordRequestDto;
import com.vehicleregistrationsystem.finalcase.requests.RegisterRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.PasswordResponseDto;
import com.vehicleregistrationsystem.finalcase.responses.UserResponseDto;

public interface AuthService {
    UserResponseDto register(RegisterRequestDto registerRequestDto);

    UserResponseDto login(LoginRequestDto loginRequestDto);

    PasswordResponseDto changePass(Long id, PasswordRequestDto passwordRequestDto);

}
