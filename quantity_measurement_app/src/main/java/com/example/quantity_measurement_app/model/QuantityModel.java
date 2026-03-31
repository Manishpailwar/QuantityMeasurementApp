package com.example.quantity_measurement_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuantityModel {
    private double value;
    private String measurementType;
    private String unitName;
}