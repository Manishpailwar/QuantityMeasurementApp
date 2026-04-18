package com.example.weightvolumeservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "weight_volume_operations")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class WeightVolumeOperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String domain;          // WEIGHT | VOLUME
    private String operationType;   // CONVERT | COMPARE | ADD | SUBTRACT | MULTIPLY | DIVIDE

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
