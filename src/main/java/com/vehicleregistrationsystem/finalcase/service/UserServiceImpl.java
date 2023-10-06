package com.vehicleregistrationsystem.finalcase.service;

import com.vehicleregistrationsystem.finalcase.entity.User;
import com.vehicleregistrationsystem.finalcase.entity.Vehicle;
import com.vehicleregistrationsystem.finalcase.repository.UserRepository;
import com.vehicleregistrationsystem.finalcase.repository.VehicleRepository;
import com.vehicleregistrationsystem.finalcase.requests.UserRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.UserResponseDto;
import com.vehicleregistrationsystem.finalcase.responses.VehicleResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        for (User user : userList) {
            UserResponseDto userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(user, userResponseDto);

            // Kullanıcıya ait araçları çek ve UserResponseDto içine ekle
            List<Vehicle> userVehicles = vehicleRepository.findByUser(user);
            List<VehicleResponseDto> vehicleResponseDtoList = new ArrayList<>();

            for (Vehicle vehicle : userVehicles) {
                VehicleResponseDto vehicleResponseDto = new VehicleResponseDto();
                BeanUtils.copyProperties(vehicle, vehicleResponseDto);
                vehicleResponseDtoList.add(vehicleResponseDto);
            }

            userResponseDto.setVehicles(vehicleResponseDtoList);

            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDTO) {
        User user = new User();
        BeanUtils.copyProperties(userRequestDTO, user);

        User savedUser = userRepository.save(user);
        UserResponseDto responseDto = new UserResponseDto();
        BeanUtils.copyProperties(savedUser, responseDto);

        return responseDto;
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı"));

        BeanUtils.copyProperties(userRequestDTO, existingUser);

        // Diğer gerekli güncelleme işlemlerini burada gerçekleştirin.

        User updatedUser = userRepository.save(existingUser);
        UserResponseDto responseDTO = new UserResponseDto();
        BeanUtils.copyProperties(updatedUser, responseDTO);
        return responseDTO;
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı"));

        userRepository.delete(existingUser);
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı"));

        UserResponseDto responseDTO = new UserResponseDto();
        BeanUtils.copyProperties(user, responseDTO);

        // Kullanıcıya ait araçları çek ve UserResponseDto içine ekle
        List<Vehicle> userVehicles = vehicleRepository.findByUser(user);
        List<VehicleResponseDto> vehicleResponseDtoList = new ArrayList<>();

        for (Vehicle vehicle : userVehicles) {
            VehicleResponseDto vehicleResponseDto = new VehicleResponseDto();
            BeanUtils.copyProperties(vehicle, vehicleResponseDto);
            vehicleResponseDtoList.add(vehicleResponseDto);
        }

        responseDTO.setVehicles(vehicleResponseDtoList);

        return responseDTO;
    }

}
