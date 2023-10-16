package com.vehicleregistrationsystem.finalcase.service.interfaces;

import com.vehicleregistrationsystem.finalcase.requests.UserRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.UserResponseDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long userId);

    UserResponseDto createUser(UserRequestDto userRequestDTO);

    UserResponseDto updateUser(Long userId, UserRequestDto userRequestDTO);

    void deleteUser(Long userId);

    List<UserResponseDto> slice(int pageNo, int pageSize);

    List<UserResponseDto> getAllUsersSortedByUserName(Sort.Direction direction);

    List<UserResponseDto> getAllUsersSortedByCreationDate(Sort.Direction direction);

}
