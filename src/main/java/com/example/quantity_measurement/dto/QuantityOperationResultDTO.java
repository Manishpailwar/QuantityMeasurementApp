package com.example.quantity_measurement.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuantityOperationResultDTO {
    private String historyId;
    private String operationType;
    private Boolean successful;
    private QuantityDTO firstQuantity;
    private QuantityDTO secondQuantity;
    private QuantityDTO resultQuantity;
    private Boolean comparisonResult;
    private String errorMessage;
    private LocalDateTime createdAt;
}
