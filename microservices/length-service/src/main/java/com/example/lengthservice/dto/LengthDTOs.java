package com.example.lengthservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class LengthDTOs {

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class QuantityDTO {
        @NotNull(message = "Value is required")
        private Double value;
        @NotBlank(message = "Unit name is required")
        private String unitName;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ConversionRequestDTO {
        @NotNull private QuantityDTO sourceQuantity;
        @NotBlank private String targetUnit;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BinaryOperationRequestDTO {
        @NotNull private QuantityDTO firstQuantity;
        @NotNull private QuantityDTO secondQuantity;
        private String resultUnit;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class OperationResultDTO {
        private Long id;
        private String operationType;
        private Double firstValue;
        private String firstUnit;
        private Double secondValue;
        private String secondUnit;
        private Double resultValue;
        private String resultUnit;
        private Boolean comparisonResult;
        private String status;
        private String errorMessage;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ErrorDTO {
        private int status;
        private String message;
        private String timestamp;
    }
}
