package com.example.quantity_measurement_app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quantity_measurement_app.entity.QuantityMeasurementEntity;

@Repository
public interface IQuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, String> {
    List<QuantityMeasurementEntity> findAllByOrderByCreatedAtAsc();

    List<QuantityMeasurementEntity> findByOperationTypeOrderByCreatedAtAsc(String operationType);


}