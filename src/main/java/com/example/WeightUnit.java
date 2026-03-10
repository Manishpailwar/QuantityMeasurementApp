package com.example;

public enum WeightUnit implements Measurable {
    KILOGRAM(1.0, "KILOGRAM"),
    GRAM(0.001, "GRAM"),
    POUND(0.453592, "POUND");

    private final double conversionFactor;
    private final String unitName;
    

    WeightUnit(double conversionFactor, String unitName) {
        this.conversionFactor = conversionFactor;
        this.unitName = unitName;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    public double toKilogramFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return unitName;
    }
}