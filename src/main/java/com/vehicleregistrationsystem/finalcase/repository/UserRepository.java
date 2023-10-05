package com.vehicleregistrationsystem.finalcase.repository;

import com.vehicleregistrationsystem.finalcase.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
