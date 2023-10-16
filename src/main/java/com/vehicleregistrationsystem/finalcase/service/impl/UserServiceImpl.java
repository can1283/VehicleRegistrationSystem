package com.vehicleregistrationsystem.finalcase.service.impl;

import com.vehicleregistrationsystem.finalcase.entity.*;
import com.vehicleregistrationsystem.finalcase.repository.UserRepository;
import com.vehicleregistrationsystem.finalcase.requests.UserRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.UserResponseDto;
import com.vehicleregistrationsystem.finalcase.responses.VehicleResponseDto;
import com.vehicleregistrationsystem.finalcase.service.interfaces.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapUserToUserResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDTO) {
        User user = new User();
        BeanUtils.copyProperties(userRequestDTO, user);

        user.setAccountCreationDate(formatDate());

        User savedUser = userRepository.save(user);
        return mapUserToUserResponseDto(savedUser);
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        BeanUtils.copyProperties(userRequestDTO, existingUser);

        existingUser.setAccountCreationDate(formatDate());

        User updatedUser = userRepository.save(existingUser);
        return mapUserToUserResponseDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        userRepository.delete(existingUser);
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));
        return mapUserToUserResponseDto(user);
    }

    @Override
    public List<UserResponseDto> slice(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.getContent().stream()
                .map(this::mapUserToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDto> getAllUsersSortedByUserName(Sort.Direction direction) {
        Sort sort = Sort.by(direction, "userName");
        List<User> userList = userRepository.findAll(sort);
        return userList.stream()
                .map(this::mapUserToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDto> getAllUsersSortedByCreationDate(Sort.Direction direction) {
        Sort sort = Sort.by(direction, "accountCreationDate");
        List<User> userList = userRepository.findAll(sort);
        return userList.stream()
                .map(this::mapUserToUserResponseDto)
                .collect(Collectors.toList());
    }

    private UserResponseDto mapUserToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);

        List<VehicleResponseDto> vehicleResponseDtoList = user.getVehicles().stream()
                .map(this::mapVehicleToVehicleResponseDto)
                .collect(Collectors.toList());

        userResponseDto.setVehicles(vehicleResponseDtoList);

        return userResponseDto;
    }

    private VehicleResponseDto mapVehicleToVehicleResponseDto(Vehicle vehicle) {
        VehicleResponseDto vehicleResponseDto = new VehicleResponseDto();
        BeanUtils.copyProperties(vehicle, vehicleResponseDto);
        return vehicleResponseDto;
    }

    public String formatDate() {
        Date getCurrentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(getCurrentTime);
    }
}
