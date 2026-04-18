package com.example.lengthservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "length_operations")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class LengthOperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String operationType;

    private Double firstValue;
    private String firstUnit;

    private Double secondValue;
    private String secondUnit;

    private Double resultValue;
    private String resultUnit;
    private Boolean comparisonResult;

    private String status;       // SUCCESS | FAILURE
    private String errorMessage;
}
