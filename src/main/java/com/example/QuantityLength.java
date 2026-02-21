package com.example;

import java.util.Objects;

public class QuantityLength {
    private static final double EPSILON = 1e-6;
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number.");
        }
        this.unit = Objects.requireNonNull(unit, "Unit must not be null.");
        this.value = value;
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        Objects.requireNonNull(targetUnit, "Target unit must not be null.");
        double convertedValue = convertValue(this.value, this.unit, targetUnit);
        return new QuantityLength(convertedValue, targetUnit);
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public double toFeet() {
        return value * unit.toFeetFactor();
    }

    private static double convertValue(double sourceValue, LengthUnit sourceUnit, LengthUnit targetUnit) {
        double valueInFeet = sourceValue * sourceUnit.toFeetFactor();
        return valueInFeet / targetUnit.toFeetFactor();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QuantityLength)) {
            return false;
        }
        QuantityLength other = (QuantityLength) obj;
        return Math.abs(this.toFeet() - other.toFeet()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toFeet());
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}