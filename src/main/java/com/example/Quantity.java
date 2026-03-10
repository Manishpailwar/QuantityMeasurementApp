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

    private double performArithmeticOperation(Quantity<U> other, AirthmaticOperation operation) {
        if (other == null) {
            throw new IllegalArgumentException("Other quantity must not be null.");
        }
        
        double thisBaseValue = this.toBaseUnit();
        double otherBaseValue = other.toBaseUnit();
        
        // Special validation for division
        if (operation == AirthmaticOperation.DIVIDE && Math.abs(otherBaseValue) < EPSILON) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        
        return operation.apply(thisBaseValue, otherBaseValue);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U resultUnit) {
        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }
        
        double resultBaseValue = performArithmeticOperation(other, AirthmaticOperation.SUBTRACT);
        double resultValue = resultUnit.convertFromBaseUnit(resultBaseValue);
        // Round to two decimal places for precision
        resultValue = Math.round(resultValue * 100.0) / 100.0;
        return new Quantity<>(resultValue, resultUnit);
    }

    public Quantity<U> divide(Quantity<U> other) {
        return divide(other, this.unit);
    }

    public Quantity<U> divide(Quantity<U> other, U resultUnit) {
        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }
        
        double ratio = performArithmeticOperation(other, AirthmaticOperation.DIVIDE);
        // Round to two decimal places for precision
        ratio = Math.round(ratio * 100.0) / 100.0;
        return new Quantity<>(ratio, resultUnit);
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
        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }
        
        double resultBaseValue = performArithmeticOperation(other, AirthmaticOperation.ADD);
        double resultValue = resultUnit.convertFromBaseUnit(resultBaseValue);
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