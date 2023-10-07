package com.vehicleregistrationsystem.finalcase.controller;

import com.vehicleregistrationsystem.finalcase.requests.VehicleRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.VehicleResponseDto;
import com.vehicleregistrationsystem.finalcase.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
@Valid
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<VehicleResponseDto>> getAllVehiclesByUserId(@PathVariable Long userId) {
        final List<VehicleResponseDto> vehicleResponseDtoList = vehicleService.getAllVehiclesByUserId(userId);
        return ResponseEntity.ok(vehicleResponseDtoList);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseDto> getVehicleById(@PathVariable Long vehicleId) {
        final VehicleResponseDto vehicleResponseDto = vehicleService.getVehicleById(vehicleId);
        return ResponseEntity.ok(vehicleResponseDto);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<VehicleResponseDto> createVehicle(@PathVariable Long userId, @Valid @RequestBody VehicleRequestDto vehicleRequestDTO) {
        final VehicleResponseDto vehicleResponseDto = vehicleService.createVehicle(userId, vehicleRequestDTO);
        return ResponseEntity.ok(vehicleResponseDto);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseDto> updateVehicle(@PathVariable Long vehicleId, @Valid @RequestBody VehicleRequestDto vehicleRequestDTO) {
        final VehicleResponseDto vehicleResponseDto = vehicleService.updateVehicle(vehicleId, vehicleRequestDTO);
        return ResponseEntity.ok(vehicleResponseDto);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }

}
