package com.vehicleregistrationsystem.finalcase.controller;

import com.vehicleregistrationsystem.finalcase.requests.VehicleRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.VehicleResponseDto;
import com.vehicleregistrationsystem.finalcase.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/user/{userId}")
    public List<VehicleResponseDto> getAllVehiclesByUserId(@PathVariable Long userId) {
        return vehicleService.getAllVehiclesByUserId(userId);
    }

    @GetMapping("/{vehicleId}")
    public VehicleResponseDto getVehicleById(@PathVariable Long vehicleId) {
        return vehicleService.getVehicleById(vehicleId);
    }

    @PostMapping("/{userId}")
    public VehicleResponseDto createVehicle(@PathVariable Long userId, @RequestBody VehicleRequestDto vehicleRequestDTO) {
        return vehicleService.createVehicle(userId, vehicleRequestDTO);
    }

    @PutMapping("/{vehicleId}")
    public VehicleResponseDto updateVehicle(@PathVariable Long vehicleId, @RequestBody VehicleRequestDto vehicleRequestDTO) {
        return vehicleService.updateVehicle(vehicleId, vehicleRequestDTO);
    }

    @DeleteMapping("/{vehicleId}")
    public void deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
    }

}
