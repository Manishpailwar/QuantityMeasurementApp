package com.example.temperatureservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "temperature_operations")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class TemperatureOperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String operationType;   // CONVERT | COMPARE

    private Double firstValue;
    private String firstUnit;

    private Double secondValue;
    private String secondUnit;

    private Double resultValue;
    private String resultUnit;
    private Boolean comparisonResult;

    private String status;          // SUCCESS | FAILURE
    private String errorMessage;
}
