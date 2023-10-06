package com.vehicleregistrationsystem.finalcase.service;

import com.vehicleregistrationsystem.finalcase.requests.VehicleRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.VehicleResponseDto;

import java.util.List;

public interface VehicleService {
    VehicleResponseDto createVehicle(Long userId, VehicleRequestDto vehicleRequestDTO);
    VehicleResponseDto updateVehicle(Long vehicleId, VehicleRequestDto vehicleRequestDTO);
    void deleteVehicle(Long vehicleId);
    VehicleResponseDto getVehicleById(Long vehicleId);
    List<VehicleResponseDto> getAllVehiclesByUserId(Long userId);
}
