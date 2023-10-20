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
        if (userRepository.existsUserByUserName(registerRequestDto.getUserName())) {
            throw new RuntimeException("This username is already in use!");
        }

        if (userRepository.existsByMail(registerRequestDto.getMail())) {
            throw new RuntimeException("This email address is already in use!");
        }

        User user = new User();
        BeanUtils.copyProperties(registerRequestDto, user);
        user.setAccountCreationDate(formatDate());

        User savedUser = userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(savedUser, userResponseDto);

        return userResponseDto;
    }

    @Override
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUserName(loginRequestDto.getUsername());

        if (user == null || !user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new RuntimeException("User not found or incorrect password!");
        }

        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);

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
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        User user = optionalUser.get();

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
