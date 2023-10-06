package com.vehicleregistrationsystem.finalcase.service;

import com.vehicleregistrationsystem.finalcase.requests.UserRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.UserResponseDto;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDTO);
    UserResponseDto updateUser(Long userId, UserRequestDto userRequestDTO);
    void deleteUser(Long userId);
    UserResponseDto getUserById(Long userId);
    List<UserResponseDto> getAllUsers();
}
