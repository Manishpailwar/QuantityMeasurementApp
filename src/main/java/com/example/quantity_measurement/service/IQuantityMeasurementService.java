package com.example.quantity_measurement.service;

import com.example.quantity_measurement.dto.OperationRequestDTO;
import com.example.quantity_measurement.dto.QuantityDTO;
import com.example.quantity_measurement.dto.QuantityOperationResultDTO;
import com.example.quantity_measurement.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementService {
    QuantityOperationResultDTO convert(QuantityDTO source, String targetUnit);

    QuantityOperationResultDTO compare(QuantityDTO firstQuantity, QuantityDTO secondQuantity);

    QuantityOperationResultDTO add(QuantityDTO firstQuantity, QuantityDTO secondQuantity, String resultUnit);

    QuantityOperationResultDTO subtract(QuantityDTO firstQuantity, QuantityDTO secondQuantity, String resultUnit);

    QuantityOperationResultDTO multiply(QuantityDTO firstQuantity, QuantityDTO secondQuantity, String resultUnit);

    QuantityOperationResultDTO divide(QuantityDTO firstQuantity, QuantityDTO secondQuantity, String resultUnit);

    QuantityOperationResultDTO operate(OperationRequestDTO request);

    List<QuantityMeasurementEntity> getMeasurementHistoryForCurrentUser();

    List<QuantityMeasurementEntity> getMeasurementHistoryByOperationForCurrentUser(String operationType);
}
