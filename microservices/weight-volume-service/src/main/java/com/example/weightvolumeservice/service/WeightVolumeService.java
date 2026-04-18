package com.example.weightvolumeservice.service;

import com.example.weightvolumeservice.dto.MeasurementDTOs.*;
import com.example.weightvolumeservice.entity.WeightVolumeOperationEntity;
import com.example.weightvolumeservice.exception.MeasurementException;
import com.example.weightvolumeservice.repository.WeightVolumeOperationRepository;
import com.example.weightvolumeservice.util.WeightVolumeMathHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeightVolumeService {

    private final WeightVolumeOperationRepository repository;

    // ═══════════════════════════════ WEIGHT ══════════════════════════════════

    @Transactional
    public OperationResultDTO convertWeight(QuantityDTO source, String targetUnit, Long userId) {
        requireWeightUnit(source.getUnitName());
        requireWeightUnit(targetUnit);
        double result = WeightVolumeMathHelper.convertWeight(source.getValue(), source.getUnitName(), targetUnit);
        return persist("WEIGHT", "CONVERT", source, null, result, targetUnit, null, userId);
    }

    @Transactional
    public OperationResultDTO compareWeight(QuantityDTO first, QuantityDTO second, Long userId) {
        requireWeightUnit(first.getUnitName());
        requireWeightUnit(second.getUnitName());
        boolean eq = WeightVolumeMathHelper.compareWeight(first.getValue(), first.getUnitName(),
                second.getValue(), second.getUnitName());
        return persistComparison("WEIGHT", first, second, eq, userId);
    }

    @Transactional
    public OperationResultDTO addWeight(QuantityDTO first, QuantityDTO second, String resultUnit, Long userId) {
        requireWeightUnit(first.getUnitName()); requireWeightUnit(second.getUnitName());
        double raw = WeightVolumeMathHelper.addWeight(first.getValue(), first.getUnitName(), second.getValue(), second.getUnitName());
        return arithmeticResult("WEIGHT", "ADD", first, second, raw, resultUnit, userId);
    }

    @Transactional
    public OperationResultDTO subtractWeight(QuantityDTO first, QuantityDTO second, String resultUnit, Long userId) {
        requireWeightUnit(first.getUnitName()); requireWeightUnit(second.getUnitName());
        double raw = WeightVolumeMathHelper.subtractWeight(first.getValue(), first.getUnitName(), second.getValue(), second.getUnitName());
        return arithmeticResult("WEIGHT", "SUBTRACT", first, second, raw, resultUnit, userId);
    }

    @Transactional
    public OperationResultDTO multiplyWeight(QuantityDTO first, QuantityDTO second, String resultUnit, Long userId) {
        requireWeightUnit(first.getUnitName()); requireWeightUnit(second.getUnitName());
        double raw = WeightVolumeMathHelper.multiplyWeight(first.getValue(), first.getUnitName(), second.getValue(), second.getUnitName());
        return arithmeticResult("WEIGHT", "MULTIPLY", first, second, raw, resultUnit, userId);
    }

    @Transactional
    public OperationResultDTO divideWeight(QuantityDTO first, QuantityDTO second, String resultUnit, Long userId) {
        requireWeightUnit(first.getUnitName()); requireWeightUnit(second.getUnitName());
        double raw = WeightVolumeMathHelper.divideWeight(first.getValue(), first.getUnitName(), second.getValue(), second.getUnitName());
        return arithmeticResult("WEIGHT", "DIVIDE", first, second, raw, resultUnit, userId);
    }

    // ═══════════════════════════════ VOLUME ══════════════════════════════════

    @Transactional
    public OperationResultDTO convertVolume(QuantityDTO source, String targetUnit, Long userId) {
        requireVolumeUnit(source.getUnitName());
        requireVolumeUnit(targetUnit);
        double result = WeightVolumeMathHelper.convertVolume(source.getValue(), source.getUnitName(), targetUnit);
        return persist("VOLUME", "CONVERT", source, null, result, targetUnit, null, userId);
    }

    @Transactional
    public OperationResultDTO compareVolume(QuantityDTO first, QuantityDTO second, Long userId) {
        requireVolumeUnit(first.getUnitName());
        requireVolumeUnit(second.getUnitName());
        boolean eq = WeightVolumeMathHelper.compareVolume(first.getValue(), first.getUnitName(),
                second.getValue(), second.getUnitName());
        return persistComparison("VOLUME", first, second, eq, userId);
    }

    @Transactional
    public OperationResultDTO addVolume(QuantityDTO first, QuantityDTO second, String resultUnit, Long userId) {
        requireVolumeUnit(first.getUnitName()); requireVolumeUnit(second.getUnitName());
        double raw = WeightVolumeMathHelper.addVolume(first.getValue(), first.getUnitName(), second.getValue(), second.getUnitName());
        return arithmeticResult("VOLUME", "ADD", first, second, raw, resultUnit, userId);
    }

    @Transactional
    public OperationResultDTO subtractVolume(QuantityDTO first, QuantityDTO second, String resultUnit, Long userId) {
        requireVolumeUnit(first.getUnitName()); requireVolumeUnit(second.getUnitName());
        double raw = WeightVolumeMathHelper.subtractVolume(first.getValue(), first.getUnitName(), second.getValue(), second.getUnitName());
        return arithmeticResult("VOLUME", "SUBTRACT", first, second, raw, resultUnit, userId);
    }

    @Transactional
    public OperationResultDTO multiplyVolume(QuantityDTO first, QuantityDTO second, String resultUnit, Long userId) {
        requireVolumeUnit(first.getUnitName()); requireVolumeUnit(second.getUnitName());
        double raw = WeightVolumeMathHelper.multiplyVolume(first.getValue(), first.getUnitName(), second.getValue(), second.getUnitName());
        return arithmeticResult("VOLUME", "MULTIPLY", first, second, raw, resultUnit, userId);
    }

    @Transactional
    public OperationResultDTO divideVolume(QuantityDTO first, QuantityDTO second, String resultUnit, Long userId) {
        requireVolumeUnit(first.getUnitName()); requireVolumeUnit(second.getUnitName());
        double raw = WeightVolumeMathHelper.divideVolume(first.getValue(), first.getUnitName(), second.getValue(), second.getUnitName());
        return arithmeticResult("VOLUME", "DIVIDE", first, second, raw, resultUnit, userId);
    }

    // ═══════════════════════════════ HISTORY ══════════════════════════════════

    public List<OperationResultDTO> getWeightHistory(Long userId) {
        return repository.findByUserIdAndDomain(userId, "WEIGHT").stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<OperationResultDTO> getVolumeHistory(Long userId) {
        return repository.findByUserIdAndDomain(userId, "VOLUME").stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<OperationResultDTO> getWeightHistoryByOp(Long userId, String op) {
        return repository.findByUserIdAndDomainAndOperationType(userId, "WEIGHT", op.toUpperCase())
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<OperationResultDTO> getVolumeHistoryByOp(Long userId, String op) {
        return repository.findByUserIdAndDomainAndOperationType(userId, "VOLUME", op.toUpperCase())
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // ═══════════════════════════════ HELPERS ══════════════════════════════════

    private void requireWeightUnit(String u) {
        if (!WeightVolumeMathHelper.isWeightUnit(u))
            throw new MeasurementException("Invalid weight unit: " + u + ". Supported: GRAM, KILOGRAM, POUND");
    }

    private void requireVolumeUnit(String u) {
        if (!WeightVolumeMathHelper.isVolumeUnit(u))
            throw new MeasurementException("Invalid volume unit: " + u + ". Supported: MILLILITER, LITER, GALLON");
    }

    private OperationResultDTO arithmeticResult(String domain, String op, QuantityDTO first,
                                                QuantityDTO second, double raw, String resultUnit, Long userId) {
        String ru = (resultUnit != null && !resultUnit.isBlank()) ? resultUnit : first.getUnitName();
        double finalVal = domain.equals("WEIGHT")
                ? WeightVolumeMathHelper.convertWeight(raw, first.getUnitName(), ru)
                : WeightVolumeMathHelper.convertVolume(raw, first.getUnitName(), ru);
        return persist(domain, op, first, second, finalVal, ru, null, userId);
    }

    private OperationResultDTO persistComparison(String domain, QuantityDTO first,
                                                 QuantityDTO second, boolean eq, Long userId) {
        WeightVolumeOperationEntity e = WeightVolumeOperationEntity.builder()
                .userId(userId).domain(domain).operationType("COMPARE")
                .firstValue(first.getValue()).firstUnit(first.getUnitName())
                .secondValue(second.getValue()).secondUnit(second.getUnitName())
                .comparisonResult(eq).status("SUCCESS").build();
        return toDTO(repository.save(e));
    }

    private OperationResultDTO persist(String domain, String op, QuantityDTO first, QuantityDTO second,
                                       double resultValue, String resultUnit, Boolean cmp, Long userId) {
        WeightVolumeOperationEntity e = WeightVolumeOperationEntity.builder()
                .userId(userId).domain(domain).operationType(op)
                .firstValue(first.getValue()).firstUnit(first.getUnitName())
                .secondValue(second != null ? second.getValue() : null)
                .secondUnit(second != null ? second.getUnitName() : null)
                .resultValue(resultValue).resultUnit(resultUnit)
                .comparisonResult(cmp).status("SUCCESS").build();
        return toDTO(repository.save(e));
    }

    private OperationResultDTO toDTO(WeightVolumeOperationEntity e) {
        return OperationResultDTO.builder()
                .id(e.getId()).domain(e.getDomain()).operationType(e.getOperationType())
                .firstValue(e.getFirstValue()).firstUnit(e.getFirstUnit())
                .secondValue(e.getSecondValue()).secondUnit(e.getSecondUnit())
                .resultValue(e.getResultValue()).resultUnit(e.getResultUnit())
                .comparisonResult(e.getComparisonResult())
                .status(e.getStatus()).errorMessage(e.getErrorMessage()).build();
    }
}
