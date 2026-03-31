package com.example.quantity_measurement_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "quantity_measurement_entity")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "operation_type", nullable = false, length = 32)
    private String operationType;

    @Column(name = "first_operand_value")
    private Double firstOperandValue;

    @Column(name = "first_measurement_type", length = 32)
    private String firstMeasurementType;

    @Column(name = "first_unit", length = 32)
    private String firstUnit;

    @Column(name = "second_operand_value")
    private Double secondOperandValue;

    @Column(name = "second_measurement_type", length = 32)
    private String secondMeasurementType;

    @Column(name = "second_unit", length = 32)
    private String secondUnit;

    @Column(name = "result_operand_value")
    private Double resultOperandValue;

    @Column(name = "result_measurement_type", length = 32)
    private String resultMeasurementType;

    @Column(name = "result_unit", length = 32)
    private String resultUnit;

    @Column(name = "comparison_result")
    private Boolean comparisonResult;

    @Column(name = "error_message", length = 1000)
    private String errorMessage;

    @Column(name = "successful", nullable = false)
    private Boolean successful;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Double getFirstOperandValue() {
        return firstOperandValue;
    }

    public void setFirstOperandValue(Double firstOperandValue) {
        this.firstOperandValue = firstOperandValue;
    }

    public String getFirstMeasurementType() {
        return firstMeasurementType;
    }

    public void setFirstMeasurementType(String firstMeasurementType) {
        this.firstMeasurementType = firstMeasurementType;
    }

    public String getFirstUnit() {
        return firstUnit;
    }

    public void setFirstUnit(String firstUnit) {
        this.firstUnit = firstUnit;
    }

    public Double getSecondOperandValue() {
        return secondOperandValue;
    }

    public void setSecondOperandValue(Double secondOperandValue) {
        this.secondOperandValue = secondOperandValue;
    }

    public String getSecondMeasurementType() {
        return secondMeasurementType;
    }

    public void setSecondMeasurementType(String secondMeasurementType) {
        this.secondMeasurementType = secondMeasurementType;
    }

    public String getSecondUnit() {
        return secondUnit;
    }

    public void setSecondUnit(String secondUnit) {
        this.secondUnit = secondUnit;
    }

    public Double getResultOperandValue() {
        return resultOperandValue;
    }

    public void setResultOperandValue(Double resultOperandValue) {
        this.resultOperandValue = resultOperandValue;
    }

    public String getResultMeasurementType() {
        return resultMeasurementType;
    }

    public void setResultMeasurementType(String resultMeasurementType) {
        this.resultMeasurementType = resultMeasurementType;
    }

    public String getResultUnit() {
        return resultUnit;
    }

    public void setResultUnit(String resultUnit) {
        this.resultUnit = resultUnit;
    }

    public Boolean getComparisonResult() {
        return comparisonResult;
    }

    public void setComparisonResult(Boolean comparisonResult) {
        this.comparisonResult = comparisonResult;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}