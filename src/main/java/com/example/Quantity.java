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
        this.unit = Objects.requireNonNull(unit);
        this.value = value;
    }

    private double performArithmeticOperation(Quantity<U> other, ArithmeticOperation operation) {

        if (other == null) {
            throw new IllegalArgumentException("Other quantity must not be null.");
        }

        unit.validateOperationSupport(operation.name());
        other.unit.validateOperationSupport(operation.name());

        double thisBaseValue = this.toBaseUnit();
        double otherBaseValue = other.toBaseUnit();

        if (operation == ArithmeticOperation.DIVIDE && Math.abs(otherBaseValue) < EPSILON) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        return operation.apply(thisBaseValue, otherBaseValue);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U resultUnit) {

        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }

        double resultBaseValue = performArithmeticOperation(other, ArithmeticOperation.ADD);
        double resultValue = resultUnit.convertFromBaseUnit(resultBaseValue);

        resultValue = Math.round(resultValue * 100.0) / 100.0;

        return new Quantity<>(resultValue, resultUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U resultUnit) {

        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }

        double resultBaseValue = performArithmeticOperation(other, ArithmeticOperation.SUBTRACT);
        double resultValue = resultUnit.convertFromBaseUnit(resultBaseValue);

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

        double ratio = performArithmeticOperation(other, ArithmeticOperation.DIVIDE);

        ratio = Math.round(ratio * 100.0) / 100.0;

        return new Quantity<>(ratio, resultUnit);
    }

    public Quantity<U> convertTo(U targetUnit) {

        Objects.requireNonNull(targetUnit);

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

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

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity)) return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (this.unit.getClass() != other.unit.getClass()) return false;

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