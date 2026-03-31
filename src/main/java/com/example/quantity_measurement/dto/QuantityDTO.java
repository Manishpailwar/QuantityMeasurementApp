package com.example.quantity_measurement.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public class QuantityDTO {
    @NotNull(message = "Value is required.")
    private Double value;

    @NotBlank(message = "Measurement type is required.")
    @JsonAlias({"measurement", "measurementTypeName"})
    private String measurementType;

    @NotBlank(message = "Unit name is required.")
    @JsonAlias({"unit", "unitName"})
    private String unitName;
}
