package com.example.quantity_measurement_app.service;

import com.example.quantity_measurement_app.dto.*;
import com.example.quantity_measurement_app.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementService {
    QuantityOperationResultDTO convert(QuantityDTO source, String targetUnit);

    QuantityOperationResultDTO compare(QuantityDTO firstQuantity, QuantityDTO secondQuantity);

    QuantityOperationResultDTO add(QuantityDTO firstQuantity, QuantityDTO secondQuantity, String resultUnit);

    QuantityOperationResultDTO subtract(QuantityDTO firstQuantity, QuantityDTO secondQuantity, String resultUnit);

    QuantityOperationResultDTO multiply(QuantityDTO firstQuantity, QuantityDTO secondQuantity, String resultUnit);

    QuantityOperationResultDTO divide(QuantityDTO firstQuantity, QuantityDTO secondQuantity, String resultUnit);

    QuantityOperationResultDTO operate(OperationRequestDTO request);

    List<QuantityMeasurementEntity> getMeasurementHistory();

    List<QuantityMeasurementEntity> getMeasurementHistoryByOperation(String operationType);
}