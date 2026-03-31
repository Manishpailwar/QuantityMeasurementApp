package com.example.quantity_measurement.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public class ConversionRequestDTO {
    @Valid
    @NotNull(message = "Source quantity is required.")
    @JsonAlias({"source", "firstQuantity", "firstOperand"})
    private QuantityDTO sourceQuantity;

    @NotBlank(message = "Target unit is required.")
    @JsonAlias({"resultUnit", "conversionTargetUnit"})
    private String targetUnit;
}
