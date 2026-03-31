package com.example.quantity_measurement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quantity_measurement_entity")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY) //
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore // Prevents infinite loops when serializing
    private User user;

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
}
