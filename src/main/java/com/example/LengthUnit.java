package com.example;

public enum LengthUnit implements Measurable {
    FEET(1.0, "FEET"),
    INCH(1.0 / 12.0, "INCH"),
    YARD(3.0, "YARD"),
    CENTIMETER((0.393701) / 12.0, "CENTIMETER");

    private final double conversionFactor;
    private final String unitName;

    LengthUnit(double conversionFactor, String unitName) {
        this.conversionFactor = conversionFactor;
        this.unitName = unitName;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    public double toFeetFactor() {
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