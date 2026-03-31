package com.example.quantity_measurement.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BinaryOperationRequestDTO {
    @Valid
    @NotNull(message = "First quantity is required.")
    @JsonAlias({"source", "firstOperand"})
    private QuantityDTO firstQuantity;

    @Valid
    @NotNull(message = "Second quantity is required.")
    @JsonAlias({"target", "secondOperand"})
    private QuantityDTO secondQuantity;

    @JsonAlias({"targetUnit", "conversionTargetUnit"})
    private String resultUnit;
}
