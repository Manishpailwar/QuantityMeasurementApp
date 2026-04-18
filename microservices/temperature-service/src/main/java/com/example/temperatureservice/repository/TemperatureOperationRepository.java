package com.example.temperatureservice.repository;

import com.example.temperatureservice.entity.TemperatureOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemperatureOperationRepository extends JpaRepository<TemperatureOperationEntity, Long> {
    List<TemperatureOperationEntity> findByUserId(Long userId);
    List<TemperatureOperationEntity> findByUserIdAndOperationType(Long userId, String operationType);
}
