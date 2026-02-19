package com.example;

public enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double toFeetFactor() {
        return toFeetFactor;
    }
}