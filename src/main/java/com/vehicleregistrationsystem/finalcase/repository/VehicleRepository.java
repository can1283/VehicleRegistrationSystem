package com.vehicleregistrationsystem.finalcase.repository;

import com.vehicleregistrationsystem.finalcase.entity.User;
import com.vehicleregistrationsystem.finalcase.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByUser(User user);
}
