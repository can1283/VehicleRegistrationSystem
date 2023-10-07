package com.vehicleregistrationsystem.finalcase.service;

import com.vehicleregistrationsystem.finalcase.entity.User;
import com.vehicleregistrationsystem.finalcase.entity.Vehicle;
import com.vehicleregistrationsystem.finalcase.repository.UserRepository;
import com.vehicleregistrationsystem.finalcase.repository.VehicleRepository;
import com.vehicleregistrationsystem.finalcase.requests.VehicleRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.VehicleResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService{
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public VehicleResponseDto createVehicle(Long userId, VehicleRequestDto vehicleRequestDTO) {
        // Kullanıcıyı ID'ye göre bulun
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı"));

        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleRequestDTO, vehicle);
        vehicle.setUser(user);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = simpleDateFormat.format(new Date());
        vehicle.setVehiclesCreationDate(formattedDate);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        VehicleResponseDto responseDTO = new VehicleResponseDto();
        BeanUtils.copyProperties(savedVehicle, responseDTO);
        return responseDTO;
    }

    @Override
    public VehicleResponseDto updateVehicle(Long vehicleId, VehicleRequestDto vehicleRequestDTO) {
        Vehicle existingVehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Araç bulunamadı"));

        BeanUtils.copyProperties(vehicleRequestDTO, existingVehicle);

        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        VehicleResponseDto responseDTO = new VehicleResponseDto();
        BeanUtils.copyProperties(updatedVehicle, responseDTO);
        return responseDTO;
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        Vehicle existingVehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new IllegalArgumentException("Araç bulunamadı"));
        vehicleRepository.delete(existingVehicle);
    }

    @Override
    public VehicleResponseDto getVehicleById(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Araç bulunamadı"));

        VehicleResponseDto responseDTO = new VehicleResponseDto();
        BeanUtils.copyProperties(vehicle, responseDTO);
        return responseDTO;
    }

    @Override
    public List<VehicleResponseDto> getAllVehiclesByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı"));

        List<Vehicle> userVehicles = vehicleRepository.findByUser(user);
        List<VehicleResponseDto> responseDTOList = new ArrayList<>();

        for (Vehicle vehicle : userVehicles) {
            VehicleResponseDto responseDTO = new VehicleResponseDto();
            BeanUtils.copyProperties(vehicle, responseDTO);
            responseDTOList.add(responseDTO);
        }

        return responseDTOList;
    }
}
