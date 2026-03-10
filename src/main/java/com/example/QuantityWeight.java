package com.example;

public class QuantityWeight {
    private static final double EPSILON = 1e-6;
    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number.");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit must not be null.");
        }
        this.unit = unit;
        this.value = value;
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit must not be null.");
        }
        double valueInBaseUnit = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(valueInBaseUnit);
        return new QuantityWeight(convertedValue, targetUnit);
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public double toKilogram() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit resultUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Other weight must not be null.");
        }
        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }
        
        double thisKilogram = this.toKilogram();
        double otherKilogram = other.toKilogram();
        double sumKilogram = thisKilogram + otherKilogram;
        
        double resultValue = resultUnit.convertFromBaseUnit(sumKilogram);
        return new QuantityWeight(resultValue, resultUnit);
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QuantityWeight)) {
            return false;
        }
        QuantityWeight other = (QuantityWeight) obj;
        return Math.abs(this.toKilogram() - other.toKilogram()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toKilogram());
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}