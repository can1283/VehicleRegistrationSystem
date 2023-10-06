package com.vehicleregistrationsystem.finalcase.repository;

import com.vehicleregistrationsystem.finalcase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
