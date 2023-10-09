package com.vehicleregistrationsystem.finalcase.service.interfaces;

import com.vehicleregistrationsystem.finalcase.requests.VehicleRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.VehicleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.awt.*;
import java.util.List;

public interface VehicleService {
    VehicleResponseDto createVehicle(Long userId, VehicleRequestDto vehicleRequestDTO);
    VehicleResponseDto updateVehicle(Long vehicleId, VehicleRequestDto vehicleRequestDTO);
    void deleteVehicle(Long vehicleId);
    VehicleResponseDto getVehicleById(Long vehicleId);
    List<VehicleResponseDto> getAllVehiclesByUserId(Long userId);
    List<VehicleResponseDto> getAllVehiclesSorted(Long userId, Sort.Direction direction, String sortBy);
    List<VehicleResponseDto> sliceVehiclesByUserId(Long userId, int pageNo, int pageSize);
}
