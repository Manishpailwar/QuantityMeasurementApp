package com.example;

import java.util.Objects;

public class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number.");
        }
        this.unit = Objects.requireNonNull(unit, "Unit must not be null.");
        this.value = value;
    }

    public double toFeet() {
        return value * unit.toFeetFactor();
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
        return Double.compare(this.toFeet(), other.toFeet()) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toFeet());
    }
}