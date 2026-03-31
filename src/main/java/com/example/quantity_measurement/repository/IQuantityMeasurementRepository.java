package com.example.quantity_measurement.repository;

import com.example.quantity_measurement.entity.QuantityMeasurementEntity;
import com.example.quantity_measurement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, String> {
    List<QuantityMeasurementEntity> findAllByOrderByCreatedAtAsc();

    List<QuantityMeasurementEntity> findByOperationTypeOrderByCreatedAtAsc(String operationType);

    List<QuantityMeasurementEntity> findByUserOrderByCreatedAtAsc(User user);

    List<QuantityMeasurementEntity> findByUserAndOperationTypeOrderByCreatedAtAsc(User user, String operationType);
}
