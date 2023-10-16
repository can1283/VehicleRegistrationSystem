package com.vehicleregistrationsystem.finalcase.repository;

import com.vehicleregistrationsystem.finalcase.entity.User;
import com.vehicleregistrationsystem.finalcase.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByUser(User user);

    List<Vehicle> findByUser(User user, Sort sort);

    Page<Vehicle> findByUserId(Long userId, Pageable pageable);

    boolean existsUserByPlateCode(String plateCode);

    List<Vehicle> findByBrand(String brand);

    List<Vehicle> findByModel(String model);
}
