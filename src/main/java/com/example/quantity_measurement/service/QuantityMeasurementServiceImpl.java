package com.example.quantity_measurement.service;

import com.example.quantity_measurement.dto.OperationRequestDTO;
import com.example.quantity_measurement.dto.QuantityDTO;
import com.example.quantity_measurement.dto.QuantityOperationResultDTO;
import com.example.quantity_measurement.entity.QuantityMeasurementEntity;
import com.example.quantity_measurement.entity.User;
import com.example.quantity_measurement.enums.OperationType;
import com.example.quantity_measurement.exception.QuantityMeasurementException;
import com.example.quantity_measurement.exception.UserNotFoundException;
import com.example.quantity_measurement.model.QuantityModel;
import com.example.quantity_measurement.repository.IQuantityMeasurementRepository;
import com.example.quantity_measurement.repository.UserManagementRepository;
import com.example.quantity_measurement.util.QuantityMathHelper;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    private final IQuantityMeasurementRepository repository;
    private final UserManagementRepository userManagementRepository;

    @Override
    @Transactional
    public QuantityOperationResultDTO convert(QuantityDTO source, String targetUnit) {
        QuantityDTO sanitizedSource = sanitizeQuantity(source, "Source quantity is required.");
        String resolvedTargetUnit = normalizeRequiredUnit(targetUnit, "Target unit is required.");

        try {
            validateUnitForType(resolvedTargetUnit, sanitizedSource.getMeasurementType());

            double resultValue = QuantityMathHelper.convert(
                    sanitizedSource.getValue(),
                    sanitizedSource.getUnitName(),
                    resolvedTargetUnit,
                    sanitizedSource.getMeasurementType());

            QuantityDTO resultQuantity = QuantityDTO.builder()
                    .value(resultValue)
                    .measurementType(sanitizedSource.getMeasurementType())
                    .unitName(resolvedTargetUnit)
                    .build();

            QuantityMeasurementEntity persisted = repository.save(
                    buildSuccessfulEntity(OperationType.CONVERT, sanitizedSource, null, resultQuantity, null));

            return toResultDTO(persisted);
        } catch (RuntimeException exception) {
            throw wrapAndRecordFailure(OperationType.CONVERT, sanitizedSource, null, exception);
        }
    }

    @Override
    @Transactional
    public QuantityOperationResultDTO compare(QuantityDTO firstQuantity, QuantityDTO secondQuantity) {
        QuantityDTO sanitizedFirst = sanitizeQuantity(firstQuantity, "First quantity is required.");
        QuantityDTO sanitizedSecond = sanitizeQuantity(secondQuantity, "Second quantity is required.");

        try {
            boolean comparisonResult = QuantityMathHelper.compare(
                    sanitizedFirst.getValue(),
                    sanitizedFirst.getUnitName(),
                    sanitizedFirst.getMeasurementType(),
                    sanitizedSecond.getValue(),
                    sanitizedSecond.getUnitName(),
                    sanitizedSecond.getMeasurementType());

            QuantityMeasurementEntity persisted = repository.save(
                    buildSuccessfulEntity(OperationType.COMPARE, sanitizedFirst, sanitizedSecond, null, comparisonResult));

            return toResultDTO(persisted);
        } catch (RuntimeException exception) {
            throw wrapAndRecordFailure(OperationType.COMPARE, sanitizedFirst, sanitizedSecond, exception);
        }
    }

    @Override
    @Transactional
    public QuantityOperationResultDTO add(QuantityDTO firstQuantity, QuantityDTO secondQuantity, String resultUnit) {
        return performArithmetic(OperationType.ADD, firstQuantity, secondQuantity, resultUnit);
    }

    @Override
    @Transactional
    public QuantityOperationResultDTO subtract(QuantityDTO firstQuantity, QuantityDTO secondQuantity, String resultUnit) {
        return performArithmetic(OperationType.SUBTRACT, firstQuantity, secondQuantity, resultUnit);
    }

    @Override
    @Transactional
    public QuantityOperationResultDTO multiply(QuantityDTO firstQuantity, QuantityDTO secondQuantity, String resultUnit) {
        return performArithmetic(OperationType.MULTIPLY, firstQuantity, secondQuantity, resultUnit);
    }

    @Override
    @Transactional
    public QuantityOperationResultDTO divide(QuantityDTO firstQuantity, QuantityDTO secondQuantity, String resultUnit) {
        return performArithmetic(OperationType.DIVIDE, firstQuantity, secondQuantity, resultUnit);
    }

    @Override
    @Transactional
    public QuantityOperationResultDTO operate(OperationRequestDTO request) {
        if (request == null) {
            throw new QuantityMeasurementException("Operation request is required.");
        }

        OperationType operationType = OperationType.from(request.getOperationType());
        return switch (operationType) {
            case CONVERT -> convert(request.getFirstQuantity(), request.getTargetUnit());
            case COMPARE -> compare(request.getFirstQuantity(), request.getSecondQuantity());
            case ADD -> add(request.getFirstQuantity(), request.getSecondQuantity(), request.getTargetUnit());
            case SUBTRACT -> subtract(request.getFirstQuantity(), request.getSecondQuantity(), request.getTargetUnit());
            case MULTIPLY -> multiply(request.getFirstQuantity(), request.getSecondQuantity(), request.getTargetUnit());
            case DIVIDE -> divide(request.getFirstQuantity(), request.getSecondQuantity(), request.getTargetUnit());
        };
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuantityMeasurementEntity> getMeasurementHistoryForCurrentUser() {
        User currentUser = getCurrentUser();
        return repository.findByUserOrderByCreatedAtAsc(currentUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuantityMeasurementEntity> getMeasurementHistoryByOperationForCurrentUser(String operationType) {
        User currentUser = getCurrentUser();
        String normalizedOperationType = normalizeRequiredValue(operationType, "Operation type is required.")
                .toUpperCase(Locale.ROOT);
        return repository.findByUserAndOperationTypeOrderByCreatedAtAsc(currentUser, normalizedOperationType);
    }

    private QuantityOperationResultDTO performArithmetic(
            OperationType operationType,
            QuantityDTO firstQuantity,
            QuantityDTO secondQuantity,
            String resultUnit) {

        QuantityDTO sanitizedFirst = sanitizeQuantity(firstQuantity, "First quantity is required.");
        QuantityDTO sanitizedSecond = sanitizeQuantity(secondQuantity, "Second quantity is required.");
        String resolvedResultUnit = resultUnit == null || resultUnit.trim().isEmpty()
                ? sanitizedFirst.getUnitName()
                : normalizeRequiredUnit(resultUnit, "Result unit is required.");

        try {
            validateUnitForType(resolvedResultUnit, sanitizedFirst.getMeasurementType());

            QuantityModel firstModel = toModel(sanitizedFirst);
            QuantityModel secondModel = toModel(sanitizedSecond);

            double resultValue = switch (operationType) {
                case ADD -> QuantityMathHelper.add(
                        firstModel.getValue(),
                        firstModel.getUnitName(),
                        firstModel.getMeasurementType(),
                        secondModel.getValue(),
                        secondModel.getUnitName(),
                        secondModel.getMeasurementType());
                case SUBTRACT -> QuantityMathHelper.subtract(
                        firstModel.getValue(),
                        firstModel.getUnitName(),
                        firstModel.getMeasurementType(),
                        secondModel.getValue(),
                        secondModel.getUnitName(),
                        secondModel.getMeasurementType());
                case MULTIPLY -> QuantityMathHelper.multiply(
                        firstModel.getValue(),
                        firstModel.getUnitName(),
                        firstModel.getMeasurementType(),
                        secondModel.getValue(),
                        secondModel.getUnitName(),
                        secondModel.getMeasurementType());
                case DIVIDE -> QuantityMathHelper.divide(
                        firstModel.getValue(),
                        firstModel.getUnitName(),
                        firstModel.getMeasurementType(),
                        secondModel.getValue(),
                        secondModel.getUnitName(),
                        secondModel.getMeasurementType());
                default -> throw new QuantityMeasurementException("Unsupported arithmetic operation: " + operationType);
            };

            double normalizedResultValue = QuantityMathHelper.convert(
                    resultValue,
                    firstModel.getUnitName(),
                    resolvedResultUnit,
                    firstModel.getMeasurementType());

            QuantityDTO resultQuantity = QuantityDTO.builder()
                    .value(normalizedResultValue)
                    .measurementType(firstModel.getMeasurementType())
                    .unitName(resolvedResultUnit)
                    .build();

            QuantityMeasurementEntity persisted = repository.save(
                    buildSuccessfulEntity(operationType, sanitizedFirst, sanitizedSecond, resultQuantity, null));

            return toResultDTO(persisted);
        } catch (RuntimeException exception) {
            throw wrapAndRecordFailure(operationType, sanitizedFirst, sanitizedSecond, exception);
        }
    }

    private QuantityDTO sanitizeQuantity(QuantityDTO quantity, String nullMessage) {
        if (quantity == null) {
            throw new QuantityMeasurementException(nullMessage);
        }

        if (quantity.getValue() == null) {
            throw new QuantityMeasurementException("Quantity value is required.");
        }

        String normalizedMeasurementType = normalizeMeasurementType(quantity.getMeasurementType());
        String normalizedUnit = normalizeRequiredUnit(quantity.getUnitName(), "Unit name is required.");
        validateUnitForType(normalizedUnit, normalizedMeasurementType);

        return QuantityDTO.builder()
                .value(quantity.getValue())
                .measurementType(normalizedMeasurementType)
                .unitName(normalizedUnit)
                .build();
    }

    private void validateUnitForType(String unit, String measurementType) {
        if (!QuantityMathHelper.isValidUnitForMeasurementType(unit, measurementType)) {
            throw new QuantityMeasurementException(
                    "Unit '" + unit + "' is not valid for measurement type '" + measurementType + "'.");
        }
    }

    private String normalizeMeasurementType(String measurementType) {
        String normalized = QuantityMathHelper.normalizeMeasurementType(
                normalizeRequiredValue(measurementType, "Measurement type is required."));

        return switch (normalized) {
            case "LengthUnit" -> "LENGTH";
            case "WeightUnit" -> "WEIGHT";
            case "VolumeUnit" -> "VOLUME";
            case "TemperatureUnit" -> "TEMPERATURE";
            default -> normalized.toUpperCase(Locale.ROOT);
        };
    }

    private String normalizeRequiredUnit(String unit, String message) {
        return normalizeRequiredValue(unit, message).toUpperCase(Locale.ROOT);
    }

    private String normalizeRequiredValue(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new QuantityMeasurementException(message);
        }
        return value.trim();
    }

    private QuantityModel toModel(QuantityDTO quantity) {
        return QuantityModel.builder()
                .value(quantity.getValue())
                .measurementType(quantity.getMeasurementType())
                .unitName(quantity.getUnitName())
                .build();
    }

    private QuantityMeasurementEntity buildSuccessfulEntity(
            OperationType operationType,
            QuantityDTO first,
            QuantityDTO second,
            QuantityDTO result,
            Boolean comparisonResult) {

        User currentUser = getCurrentUser();
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setUser(currentUser);
        entity.setOperationType(operationType.name());
        setQuantity(entity, first, true);
        setQuantity(entity, second, false);
        setResultQuantity(entity, result);
        entity.setComparisonResult(comparisonResult);
        entity.setErrorMessage(null);
        entity.setSuccessful(Boolean.TRUE);
        return entity;
    }

    private QuantityMeasurementException wrapAndRecordFailure(
            OperationType operationType,
            QuantityDTO first,
            QuantityDTO second,
            RuntimeException exception) {

        String message = exception.getMessage() == null
                ? "Operation failed."
                : exception.getMessage();

        try {
            User currentUser = getCurrentUser();
            QuantityMeasurementEntity failedEntity = new QuantityMeasurementEntity();
            failedEntity.setUser(currentUser);
            failedEntity.setOperationType(operationType.name());
            setQuantity(failedEntity, first, true);
            setQuantity(failedEntity, second, false);
            failedEntity.setErrorMessage(message);
            failedEntity.setSuccessful(Boolean.FALSE);
            repository.save(failedEntity);
        } catch (RuntimeException suppressed) {
            exception.addSuppressed(suppressed);
        }

        if (exception instanceof QuantityMeasurementException quantityMeasurementException) {
            return quantityMeasurementException;
        }

        return new QuantityMeasurementException(message, exception);
    }

    private void setQuantity(QuantityMeasurementEntity entity, QuantityDTO quantity, boolean first) {
        if (quantity == null) {
            return;
        }

        if (first) {
            entity.setFirstOperandValue(quantity.getValue());
            entity.setFirstMeasurementType(quantity.getMeasurementType());
            entity.setFirstUnit(quantity.getUnitName());
            return;
        }

        entity.setSecondOperandValue(quantity.getValue());
        entity.setSecondMeasurementType(quantity.getMeasurementType());
        entity.setSecondUnit(quantity.getUnitName());
    }

    private void setResultQuantity(QuantityMeasurementEntity entity, QuantityDTO result) {
        if (result == null) {
            return;
        }

        entity.setResultOperandValue(result.getValue());
        entity.setResultMeasurementType(result.getMeasurementType());
        entity.setResultUnit(result.getUnitName());
    }

    private QuantityOperationResultDTO toResultDTO(QuantityMeasurementEntity entity) {
        return QuantityOperationResultDTO.builder()
                .historyId(entity.getId())
                .operationType(entity.getOperationType())
                .successful(entity.getSuccessful())
                .firstQuantity(toQuantityDTO(entity.getFirstOperandValue(), entity.getFirstMeasurementType(), entity.getFirstUnit()))
                .secondQuantity(toQuantityDTO(entity.getSecondOperandValue(), entity.getSecondMeasurementType(), entity.getSecondUnit()))
                .resultQuantity(toQuantityDTO(entity.getResultOperandValue(), entity.getResultMeasurementType(), entity.getResultUnit()))
                .comparisonResult(entity.getComparisonResult())
                .errorMessage(entity.getErrorMessage())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private QuantityDTO toQuantityDTO(Double value, String measurementType, String unit) {
        if (value == null || measurementType == null || unit == null) {
            return null;
        }

        return QuantityDTO.builder()
                .value(value)
                .measurementType(measurementType)
                .unitName(unit)
                .build();
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new QuantityMeasurementException("User is not authenticated.");
        }
        String email = authentication.getName();
        return userManagementRepository.findByEmail(email);
    }
}
