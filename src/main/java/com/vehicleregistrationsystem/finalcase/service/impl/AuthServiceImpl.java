package com.vehicleregistrationsystem.finalcase.service.impl;

import com.vehicleregistrationsystem.finalcase.entity.User;
import com.vehicleregistrationsystem.finalcase.repository.UserRepository;
import com.vehicleregistrationsystem.finalcase.requests.*;
import com.vehicleregistrationsystem.finalcase.responses.*;
import com.vehicleregistrationsystem.finalcase.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto register(RegisterRequestDto registerRequestDto) {
        // Check if the username is already in use
        if (userRepository.existsUserByUserName(registerRequestDto.getUserName())) {
            throw new RuntimeException("This username is already in use!");
        }

        // Check if the email address is already in use
        if (userRepository.existsByMail(registerRequestDto.getMail())) {
            throw new RuntimeException("This email address is already in use!");
        }

        // Create a new User object and copy properties from the registration request
        User user = new User();
        BeanUtils.copyProperties(registerRequestDto, user);
        user.setAccountCreationDate(formatDate());

        User savedUser = userRepository.save(user);

        // Create a UserResponseDto and copy properties from the saved user
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(savedUser, userResponseDto);

        return userResponseDto;
    }

    @Override
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        // Find the user by username
        User user = userRepository.findByUserName(loginRequestDto.getUsername());

        // Check if the user exists and the password is correct
        if (user == null || !user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new RuntimeException("User not found or incorrect password!");
        }

        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);

        // Copy vehicle information into the response
        List<VehicleResponseDto> vehicleResponseDtoList = user.getVehicles().stream()
                .map(vehicle -> {
                    VehicleResponseDto vehicleResponseDto = new VehicleResponseDto();
                    BeanUtils.copyProperties(vehicle, vehicleResponseDto);
                    return vehicleResponseDto;
                })
                .collect(Collectors.toList());
        userResponseDto.setVehicles(vehicleResponseDtoList);

        return userResponseDto;
    }

    @Override
    public PasswordResponseDto changePass(Long id, PasswordRequestDto passwordRequestDto) {
        // Find the user by ID
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        User user = optionalUser.get();

        // Check if the current password matches the user's password
        if (!passwordRequestDto.getCurrentPassword().equals(user.getPassword())) {
            throw new RuntimeException("Current password is incorrect!");
        }

        user.setPassword(passwordRequestDto.getNewPassword());
        userRepository.save(user);

        PasswordResponseDto response = new PasswordResponseDto();
        response.setId(user.getId());
        response.setPassword("Password changed successfully!");

        return response;
    }

    // Helper method to format the current date and time
    public String formatDate() {
        Date getCurrentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(getCurrentTime);
    }
}
