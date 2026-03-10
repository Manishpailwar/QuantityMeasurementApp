package com.example;

public enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER((0.393701) / 12.0);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double toFeetFactor() {
        return toFeetFactor;
    }

    /**
     * Converts a value in this unit to the base unit (feet).
     * 
     * @param value the value in this unit
     * @return the value converted to feet (base unit)
     */
    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    /**
     * Converts a value from the base unit (feet) to this unit.
     * 
     * @param baseValue the value in feet (base unit)
     * @return the value converted to this unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }
}