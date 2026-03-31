package com.example.quantity_measurement_app.controller;

import com.example.quantity_measurement_app.dto.BinaryOperationRequestDTO;
import com.example.quantity_measurement_app.dto.ConversionRequestDTO;
import com.example.quantity_measurement_app.dto.OperationRequestDTO;
import com.example.quantity_measurement_app.dto.QuantityOperationResultDTO;
import com.example.quantity_measurement_app.entity.QuantityMeasurementEntity;
import com.example.quantity_measurement_app.service.IQuantityMeasurementService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quantity")
@RequiredArgsConstructor
public class QuantityMeasurementController {
    private final IQuantityMeasurementService quantityMeasurementService;

    @PostMapping("/convert")
    public ResponseEntity<QuantityOperationResultDTO> convert(@Valid @RequestBody ConversionRequestDTO request) {
        QuantityOperationResultDTO response = quantityMeasurementService.convert(
                request.getSourceQuantity(),
                request.getTargetUnit());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/compare")
    public ResponseEntity<QuantityOperationResultDTO> compare(@Valid @RequestBody BinaryOperationRequestDTO request) {
        QuantityOperationResultDTO response = quantityMeasurementService.compare(
                request.getFirstQuantity(),
                request.getSecondQuantity());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<QuantityOperationResultDTO> add(@Valid @RequestBody BinaryOperationRequestDTO request) {
        QuantityOperationResultDTO response = quantityMeasurementService.add(
                request.getFirstQuantity(),
                request.getSecondQuantity(),
                request.getResultUnit());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/subtract")
    public ResponseEntity<QuantityOperationResultDTO> subtract(@Valid @RequestBody BinaryOperationRequestDTO request) {
        QuantityOperationResultDTO response = quantityMeasurementService.subtract(
                request.getFirstQuantity(),
                request.getSecondQuantity(),
                request.getResultUnit());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/multiply")
    public ResponseEntity<QuantityOperationResultDTO> multiply(@Valid @RequestBody BinaryOperationRequestDTO request) {
        QuantityOperationResultDTO response = quantityMeasurementService.multiply(
                request.getFirstQuantity(),
                request.getSecondQuantity(),
                request.getResultUnit());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/divide")
    public ResponseEntity<QuantityOperationResultDTO> divide(@Valid @RequestBody BinaryOperationRequestDTO request) {
        QuantityOperationResultDTO response = quantityMeasurementService.divide(
                request.getFirstQuantity(),
                request.getSecondQuantity(),
                request.getResultUnit());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/operate")
    public ResponseEntity<QuantityOperationResultDTO> operate(@Valid @RequestBody OperationRequestDTO request) {
        return ResponseEntity.ok(quantityMeasurementService.operate(request));
    }

    @GetMapping("/history")
    public ResponseEntity<List<QuantityMeasurementEntity>> getHistory() {
        return ResponseEntity.ok(quantityMeasurementService.getMeasurementHistory());
    }

    @GetMapping("/history/operation/{operationType}")
    public ResponseEntity<List<QuantityMeasurementEntity>> getHistoryByOperation(
            @PathVariable String operationType) {
        return ResponseEntity.ok(quantityMeasurementService.getMeasurementHistoryByOperation(operationType));
    }
}