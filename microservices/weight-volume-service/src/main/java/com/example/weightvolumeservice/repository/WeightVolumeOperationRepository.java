package com.example.weightvolumeservice.repository;

import com.example.weightvolumeservice.entity.WeightVolumeOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeightVolumeOperationRepository extends JpaRepository<WeightVolumeOperationEntity, Long> {
    List<WeightVolumeOperationEntity> findByUserIdAndDomain(Long userId, String domain);
    List<WeightVolumeOperationEntity> findByUserIdAndDomainAndOperationType(Long userId, String domain, String operationType);
}
