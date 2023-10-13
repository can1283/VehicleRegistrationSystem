package com.vehicleregistrationsystem.finalcase.controller;

import com.vehicleregistrationsystem.finalcase.requests.LoginRequestDto;
import com.vehicleregistrationsystem.finalcase.requests.PasswordRequestDto;
import com.vehicleregistrationsystem.finalcase.requests.RegisterRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.PasswordResponseDto;
import com.vehicleregistrationsystem.finalcase.responses.UserResponseDto;
import com.vehicleregistrationsystem.finalcase.service.interfaces.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@Valid
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody RegisterRequestDto registerRequestDto) {
        return authService.register(registerRequestDto);
    }

    @PostMapping("/login")
    public UserResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @PostMapping("/change-password/{userId}")
    public PasswordResponseDto changePassword(
            @PathVariable Long userId,
            @RequestBody PasswordRequestDto passwordRequestDto) {
        return authService.changePass(userId, passwordRequestDto);
    }

}
