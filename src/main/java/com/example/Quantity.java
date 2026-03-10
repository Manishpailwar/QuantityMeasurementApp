package com.example;

import java.util.Objects;

public class Quantity<U extends Measurable> {
    private static final double EPSILON = 1e-6;
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number.");
        }
        this.unit = Objects.requireNonNull(unit, "Unit must not be null.");
        this.value = value;
    }


    public Quantity<U> convertTo(U targetUnit) {
        Objects.requireNonNull(targetUnit, "Target unit must not be null.");
        double valueInBaseUnit = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(valueInBaseUnit);
        // Round to two decimal places for precision
        convertedValue = Math.round(convertedValue * 100.0) / 100.0;
        return new Quantity<>(convertedValue, targetUnit);
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }


    public Quantity<U> add(Quantity<U> other, U resultUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Other quantity must not be null.");
        }
        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }
        
        double thisBaseValue = this.toBaseUnit();
        double otherBaseValue = other.toBaseUnit();
        double sumBaseValue = thisBaseValue + otherBaseValue;
        
        double resultValue = resultUnit.convertFromBaseUnit(sumBaseValue);
        // Round to two decimal places for precision
        resultValue = Math.round(resultValue * 100.0) / 100.0;
        return new Quantity<>(resultValue, resultUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Quantity)) {
            return false;
        }
        
        @SuppressWarnings("unchecked")
        Quantity<U> other = (Quantity<U>) obj;
        
        // Check that both use the same unit type (prevents cross-category comparison)
        if (this.unit.getClass() != other.unit.getClass()) {
            return false;
        }
        
        // Compare values in the base unit
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit.getUnitName());
    }
}