package com.example.temperatureservice.service;

import com.example.temperatureservice.dto.TemperatureDTOs.*;
import com.example.temperatureservice.entity.TemperatureOperationEntity;
import com.example.temperatureservice.exception.TemperatureException;
import com.example.temperatureservice.repository.TemperatureOperationRepository;
import com.example.temperatureservice.util.TemperatureMathHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemperatureService {

    private final TemperatureOperationRepository repository;

    @Transactional
    public OperationResultDTO convert(QuantityDTO source, String targetUnit, Long userId) {
        requireValidUnit(source.getUnitName());
        requireValidUnit(targetUnit);
        try {
            double result = TemperatureMathHelper.convert(
                    source.getValue(), source.getUnitName(), targetUnit);

            TemperatureOperationEntity entity = TemperatureOperationEntity.builder()
                    .userId(userId)
                    .operationType("CONVERT")
                    .firstValue(source.getValue()).firstUnit(source.getUnitName())
                    .resultValue(result).resultUnit(targetUnit)
                    .status("SUCCESS")
                    .build();
            return toDTO(repository.save(entity));
        } catch (TemperatureException ex) {
            persistFailure("CONVERT", source, null, ex.getMessage(), userId);
            throw ex;
        }
    }

    @Transactional
    public OperationResultDTO compare(QuantityDTO first, QuantityDTO second, Long userId) {
        requireValidUnit(first.getUnitName());
        requireValidUnit(second.getUnitName());
        try {
            boolean result = TemperatureMathHelper.compare(
                    first.getValue(), first.getUnitName(),
                    second.getValue(), second.getUnitName());

            TemperatureOperationEntity entity = TemperatureOperationEntity.builder()
                    .userId(userId)
                    .operationType("COMPARE")
                    .firstValue(first.getValue()).firstUnit(first.getUnitName())
                    .secondValue(second.getValue()).secondUnit(second.getUnitName())
                    .comparisonResult(result)
                    .status("SUCCESS")
                    .build();
            return toDTO(repository.save(entity));
        } catch (TemperatureException ex) {
            persistFailure("COMPARE", first, second, ex.getMessage(), userId);
            throw ex;
        }
    }

    public List<OperationResultDTO> getHistory(Long userId) {
        return repository.findByUserId(userId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<OperationResultDTO> getHistoryByOperation(Long userId, String operationType) {
        return repository.findByUserIdAndOperationType(userId, operationType.toUpperCase())
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // ── helpers ───────────────────────────────────────────────────────────────

    private void requireValidUnit(String unit) {
        if (!TemperatureMathHelper.isValidUnit(unit)) {
            throw new TemperatureException(
                    "Invalid temperature unit: " + unit +
                    ". Supported: CELSIUS (C), FAHRENHEIT (F), KELVIN (K)");
        }
    }

    private void persistFailure(String op, QuantityDTO first, QuantityDTO second,
                                String errorMsg, Long userId) {
        TemperatureOperationEntity entity = TemperatureOperationEntity.builder()
                .userId(userId)
                .operationType(op)
                .firstValue(first.getValue()).firstUnit(first.getUnitName())
                .secondValue(second != null ? second.getValue() : null)
                .secondUnit(second != null ? second.getUnitName() : null)
                .status("FAILURE").errorMessage(errorMsg)
                .build();
        repository.save(entity);
    }

    private OperationResultDTO toDTO(TemperatureOperationEntity e) {
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
