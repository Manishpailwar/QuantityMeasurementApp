package com.example.quantity_measurement.repository;

import com.example.quantity_measurement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
