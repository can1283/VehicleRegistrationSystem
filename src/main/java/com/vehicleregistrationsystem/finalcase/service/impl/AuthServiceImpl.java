package com.vehicleregistrationsystem.finalcase.service.impl;

import com.vehicleregistrationsystem.finalcase.entity.User;
import com.vehicleregistrationsystem.finalcase.entity.Vehicle;
import com.vehicleregistrationsystem.finalcase.repository.UserRepository;
import com.vehicleregistrationsystem.finalcase.requests.LoginRequestDto;
import com.vehicleregistrationsystem.finalcase.requests.RegisterRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.UserResponseDto;
import com.vehicleregistrationsystem.finalcase.responses.VehicleResponseDto;
import com.vehicleregistrationsystem.finalcase.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto register(RegisterRequestDto registerRequestDto) {
        if (userRepository.existsUserByUserName(registerRequestDto.getUserName())) {
            throw new RuntimeException("Bu kullanıcı adı zaten kullanılıyor!");
        }


        if (userRepository.existsByMail(registerRequestDto.getMail())) {
            throw new RuntimeException("Bu e-posta adresi zaten kullanımda.");
        }

        User user = new User();
        user.setUserName(registerRequestDto.getUserName());
        user.setMail(registerRequestDto.getMail());
        user.setPassword(registerRequestDto.getPassword());
        user.setFirstName(registerRequestDto.getFirstName());
        user.setLastName(registerRequestDto.getLastName());
        user.setCity(registerRequestDto.getCity());
        user.setAccountCreationDate(formatDate());

        User savedUser = userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserName(savedUser.getUserName());
        userResponseDto.setMail(savedUser.getMail());
        userResponseDto.setFirstName(savedUser.getFirstName());
        userResponseDto.setLastName(savedUser.getLastName());
        userResponseDto.setCity(savedUser.getCity());
        userResponseDto.setAccountCreationDate(savedUser.getAccountCreationDate());

        return userResponseDto;
    }

    @Override
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUserName(loginRequestDto.getUsername());

        if (user == null) {
            throw new RuntimeException("Kullanıcı bulunamadı!");
        }

        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new RuntimeException("Hatalı şifre!");
        }

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setAccountCreationDate(user.getAccountCreationDate());
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setMail(user.getMail());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setCity(user.getCity());
        List<VehicleResponseDto> vehicleResponseDtoList = user.getVehicles().stream()
                .map(vehicle -> {
                    VehicleResponseDto vehicleResponseDto = new VehicleResponseDto();
                    vehicleResponseDto.setVehiclesCreationDate(vehicle.getVehiclesCreationDate());
                    vehicleResponseDto.setName(vehicle.getName());
                    vehicleResponseDto.setBrand(vehicle.getBrand());
                    vehicleResponseDto.setModel(vehicle.getModel());
                    vehicleResponseDto.setPlateCode(vehicle.getPlateCode());
                    vehicleResponseDto.setModelYear(vehicle.getModelYear());
                    vehicleResponseDto.setActive(vehicle.isActive());
                    return vehicleResponseDto;
                })
                .collect(Collectors.toList());
        userResponseDto.setVehicles(vehicleResponseDtoList);

        return userResponseDto;
    }

    public String formatDate() {
        Date getCurrentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(getCurrentTime);
    }
}
