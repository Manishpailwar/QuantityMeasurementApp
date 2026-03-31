package com.example.quantity_measurement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationRequestDTO {
    @NotBlank(message = "Operation type is required.")
    private String operationType;

    @Valid
    @NotNull(message = "First quantity is required.")
    private QuantityDTO firstQuantity;

    @Valid
    private QuantityDTO secondQuantity;

    private String targetUnit;
}
