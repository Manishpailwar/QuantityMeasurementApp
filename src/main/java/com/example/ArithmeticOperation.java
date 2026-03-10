package com.example;
import java.util.function.DoubleBinaryOperator;
public enum ArithmeticOperation {
    ADD((a, b) -> a + b),
    SUBTRACT((a, b) -> a - b),
    MULTIPLY((a, b) -> a * b),
    DIVIDE((a, b) -> a / b);
    private final DoubleBinaryOperator operator;
    ArithmeticOperation(DoubleBinaryOperator operator) {
        this.operator = operator;
    }
    public double apply(double a, double b) {
        return operator.applyAsDouble(a, b);
    }
}