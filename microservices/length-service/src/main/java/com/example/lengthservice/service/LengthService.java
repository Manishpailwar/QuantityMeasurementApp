package com.example.lengthservice.service;

import com.example.lengthservice.dto.LengthDTOs.*;
import com.example.lengthservice.entity.LengthOperationEntity;
import com.example.lengthservice.exception.LengthMeasurementException;
import com.example.lengthservice.repository.LengthOperationRepository;
import com.example.lengthservice.util.LengthMathHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LengthService {

    private final LengthOperationRepository repository;

    @Transactional
    public OperationResultDTO convert(QuantityDTO source, String targetUnit, Long userId) {
        validateUnit(source.getUnitName());
        validateUnit(targetUnit);
        try {
            double result = LengthMathHelper.convert(source.getValue(), source.getUnitName(), targetUnit);
            return save("CONVERT", source, null, result, targetUnit, null, userId);
        } catch (RuntimeException ex) {
            return saveFailure("CONVERT", source, null, ex.getMessage(), userId);
        }
    }

    @Transactional
    public OperationResultDTO compare(QuantityDTO first, QuantityDTO second, Long userId) {
        validateUnit(first.getUnitName());
        validateUnit(second.getUnitName());
        try {
            boolean result = LengthMathHelper.compare(first.getValue(), first.getUnitName(),
                    second.getValue(), second.getUnitName());
            LengthOperationEntity entity = LengthOperationEntity.builder()
                    .userId(userId)
                    .operationType("COMPARE")
                    .firstValue(first.getValue()).firstUnit(first.getUnitName())
                    .secondValue(second.getValue()).secondUnit(second.getUnitName())
                    .comparisonResult(result)
                    .status("SUCCESS")
                    .build();
            entity = repository.save(entity);
            return toDTO(entity);
        } catch (RuntimeException ex) {
            return saveFailure("COMPARE", first, second, ex.getMessage(), userId);
        }
    }

    @Transactional
    public OperationResultDTO add(QuantityDTO first, QuantityDTO second, String resultUnit, Long userId) {
        return arithmetic("ADD", first, second, resultUnit, userId,
                LengthMathHelper.add(first.getValue(), first.getUnitName(), second.getValue(), second.getUnitName()));
    }

    @Transactional
    public OperationResultDTO subtract(QuantityDTO first, QuantityDTO second, String resultUnit, Long userId) {
        return arithmetic("SUBTRACT", first, second, resultUnit, userId,
                LengthMathHelper.subtract(first.getValue(), first.getUnitName(), second.getValue(), second.getUnitName()));
    }

    @Transactional
    public OperationResultDTO multiply(QuantityDTO first, QuantityDTO second, String resultUnit, Long userId) {
        return arithmetic("MULTIPLY", first, second, resultUnit, userId,
                LengthMathHelper.multiply(first.getValue(), first.getUnitName(), second.getValue(), second.getUnitName()));
    }

    @Transactional
    public OperationResultDTO divide(QuantityDTO first, QuantityDTO second, String resultUnit, Long userId) {
        try {
            double result = LengthMathHelper.divide(first.getValue(), first.getUnitName(),
                    second.getValue(), second.getUnitName());
            return arithmetic("DIVIDE", first, second, resultUnit, userId, result);
        } catch (ArithmeticException ex) {
            return saveFailure("DIVIDE", first, second, ex.getMessage(), userId);
        }
    }

    public List<OperationResultDTO> getHistory(Long userId) {
        return repository.findByUserId(userId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<OperationResultDTO> getHistoryByOperation(Long userId, String operationType) {
        return repository.findByUserIdAndOperationType(userId, operationType.toUpperCase())
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // ---- helpers ----

    private void validateUnit(String unit) {
        if (!LengthMathHelper.isValidUnit(unit)) {
            throw new LengthMeasurementException("Invalid length unit: " + unit +
                    ". Supported: INCH, FOOT, YARD, CENTIMETER, METER");
        }
    }

    private OperationResultDTO arithmetic(String op, QuantityDTO first, QuantityDTO second,
                                          String resultUnit, Long userId, double result) {
        validateUnit(first.getUnitName());
        validateUnit(second.getUnitName());
        String ru = (resultUnit != null && !resultUnit.isBlank()) ? resultUnit : first.getUnitName();
        double finalResult = LengthMathHelper.convert(result, first.getUnitName(), ru);
        return save(op, first, second, finalResult, ru, null, userId);
    }

    private OperationResultDTO save(String op, QuantityDTO first, QuantityDTO second,
                                    double resultValue, String resultUnit,
                                    Boolean comparison, Long userId) {
        LengthOperationEntity entity = LengthOperationEntity.builder()
                .userId(userId)
                .operationType(op)
                .firstValue(first.getValue()).firstUnit(first.getUnitName())
                .secondValue(second != null ? second.getValue() : null)
                .secondUnit(second != null ? second.getUnitName() : null)
                .resultValue(resultValue).resultUnit(resultUnit)
                .comparisonResult(comparison)
                .status("SUCCESS")
                .build();
        return toDTO(repository.save(entity));
    }

    private OperationResultDTO saveFailure(String op, QuantityDTO first, QuantityDTO second,
                                           String errorMsg, Long userId) {
        LengthOperationEntity entity = LengthOperationEntity.builder()
                .userId(userId)
                .operationType(op)
                .firstValue(first.getValue()).firstUnit(first.getUnitName())
                .secondValue(second != null ? second.getValue() : null)
                .secondUnit(second != null ? second.getUnitName() : null)
                .status("FAILURE").errorMessage(errorMsg)
                .build();
        repository.save(entity);
        throw new LengthMeasurementException(errorMsg);
    }

    private OperationResultDTO toDTO(LengthOperationEntity e) {
        return OperationResultDTO.builder()
                .id(e.getId())
                .operationType(e.getOperationType())
                .firstValue(e.getFirstValue()).firstUnit(e.getFirstUnit())
                .secondValue(e.getSecondValue()).secondUnit(e.getSecondUnit())
                .resultValue(e.getResultValue()).resultUnit(e.getResultUnit())
                .comparisonResult(e.getComparisonResult())
                .status(e.getStatus()).errorMessage(e.getErrorMessage())
                .build();
    }
}
