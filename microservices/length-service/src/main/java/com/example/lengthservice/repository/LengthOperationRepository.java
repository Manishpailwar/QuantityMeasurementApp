package com.example.lengthservice.repository;

import com.example.lengthservice.entity.LengthOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LengthOperationRepository extends JpaRepository<LengthOperationEntity, Long> {
    List<LengthOperationEntity> findByUserId(Long userId);
    List<LengthOperationEntity> findByUserIdAndOperationType(Long userId, String operationType);
}
