package com.vehicleregistrationsystem.finalcase.service.impl;

import com.vehicleregistrationsystem.finalcase.entity.User;
import com.vehicleregistrationsystem.finalcase.entity.Vehicle;
import com.vehicleregistrationsystem.finalcase.repository.UserRepository;
import com.vehicleregistrationsystem.finalcase.repository.VehicleRepository;
import com.vehicleregistrationsystem.finalcase.requests.VehicleRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.VehicleResponseDto;
import com.vehicleregistrationsystem.finalcase.service.interfaces.VehicleService;
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
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public VehicleResponseDto createVehicle(Long userId, VehicleRequestDto vehicleRequestDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found!"));

        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleRequestDTO, vehicle);
        vehicle.setUser(user);

        vehicle.setVehiclesCreationDate(formatDate());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        VehicleResponseDto responseDTO = new VehicleResponseDto();
        BeanUtils.copyProperties(savedVehicle, responseDTO);
        return responseDTO;
    }

    @Override
    public VehicleResponseDto updateVehicle(Long vehicleId, VehicleRequestDto vehicleRequestDTO) {
        Vehicle existingVehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found!"));

        BeanUtils.copyProperties(vehicleRequestDTO, existingVehicle);

        existingVehicle.setVehiclesCreationDate(formatDate());

        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        VehicleResponseDto responseDTO = new VehicleResponseDto();
        BeanUtils.copyProperties(updatedVehicle, responseDTO);
        return responseDTO;
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        Vehicle existingVehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found!"));
        vehicleRepository.delete(existingVehicle);
    }

    @Override
    public VehicleResponseDto getVehicleById(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found!"));

        VehicleResponseDto responseDTO = new VehicleResponseDto();
        BeanUtils.copyProperties(vehicle, responseDTO);
        return responseDTO;
    }

    @Override
    public List<VehicleResponseDto> getAllVehiclesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        List<Vehicle> userVehicles = vehicleRepository.findByUser(user);
        List<VehicleResponseDto> responseDTOList = new ArrayList<>();

        userVehicles.forEach(vehicle -> {
            VehicleResponseDto responseDTO = new VehicleResponseDto();
            BeanUtils.copyProperties(vehicle, responseDTO);
            responseDTOList.add(responseDTO);
        });

        return responseDTOList;
    }

    @Override
    public List<VehicleResponseDto> getAllVehiclesSorted(Long userId, Sort.Direction direction, String sortBy) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        Sort sort = Sort.by(direction, sortBy);
        List<Vehicle> vehicles = vehicleRepository.findByUser(user, sort);

        return vehicles.stream()
                .map(this::mapVehicleToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleResponseDto> sliceVehiclesByUserId(Long userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("modelYear"))); // Sıralama örneği, ihtiyaca göre değiştirilebilir
        Page<Vehicle> vehiclePage = vehicleRepository.findByUserId(userId, pageable);

        return vehiclePage.getContent().stream()
                .map(this::mapVehicleToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleResponseDto> getVehiclesByBrand(String brand) {
        List<Vehicle> vehicles = vehicleRepository.findByBrand(brand);
        return vehicles.stream()
                .map(this::mapVehicleToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleResponseDto> getVehiclesByModel(String model) {
        List<Vehicle> vehicles = vehicleRepository.findByModel(model);
        return vehicles.stream()
                .map(this::mapVehicleToResponseDto)
                .collect(Collectors.toList());
    }

    private VehicleResponseDto mapVehicleToResponseDto(Vehicle vehicle) {
        VehicleResponseDto responseDto = new VehicleResponseDto();
        BeanUtils.copyProperties(vehicle, responseDto);
        return responseDto;
    }
    public Date getCurrentDateTime() {
        return new Date();
    }

    public String formatDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(getCurrentDateTime());
    }
}
