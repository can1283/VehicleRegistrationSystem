package com.vehicleregistrationsystem.finalcase.service.interfaces;

import com.vehicleregistrationsystem.finalcase.entity.User;
import com.vehicleregistrationsystem.finalcase.requests.UserRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.UserResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
