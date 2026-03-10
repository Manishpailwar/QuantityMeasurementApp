package com.example;
public interface Measurable {
    double getConversionFactor();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();
    default boolean supportsArithmetic() {
        return true;
    }
    default void validateOperationSupport(String operation) {
    }
}