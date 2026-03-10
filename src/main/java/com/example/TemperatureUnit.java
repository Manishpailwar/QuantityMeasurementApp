package com.example;

import java.util.function.Function;

public enum TemperatureUnit implements Measurable {

    CELSIUS(
            c -> c,
            c -> c,
            "CELSIUS"
    ),

    FAHRENHEIT(
            f -> (f - 32) * 5 / 9,
            c -> (c * 9 / 5) + 32,
            "FAHRENHEIT"
    ),

    KELVIN(
            k -> k - 273.15,
            c -> c + 273.15,
            "KELVIN"
    );

    private final Function<Double, Double> toCelsius;
    private final Function<Double, Double> fromCelsius;
    private final String unitName;

    TemperatureUnit(Function<Double, Double> toCelsius,
                    Function<Double, Double> fromCelsius,
                    String unitName) {
        this.toCelsius = toCelsius;
        this.fromCelsius = fromCelsius;
        this.unitName = unitName;
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toCelsius.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromCelsius.apply(baseValue);
    }

    @Override
    public String getUnitName() {
        return unitName;
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException("Temperature does not support " + operation);
    }
}