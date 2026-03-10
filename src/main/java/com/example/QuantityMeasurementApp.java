package com.example;

public class QuantityMeasurementApp {
    private static final double EPSILON = 1e-6;

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number.");
        }
        if (source == null) {
            throw new IllegalArgumentException("Source unit must not be null.");
        }
        if (target == null) {
            throw new IllegalArgumentException("Target unit must not be null.");
        }
        
        QuantityLength quantity = new QuantityLength(value, source);
        return quantity.convertTo(target).getValue();
    }

    public static double add(double value1, LengthUnit unit1, double value2, LengthUnit unit2, LengthUnit resultUnit) {
        if (Double.isNaN(value1) || Double.isInfinite(value1)) {
            throw new IllegalArgumentException("Value 1 must be a finite number.");
        }
        if (Double.isNaN(value2) || Double.isInfinite(value2)) {
            throw new IllegalArgumentException("Value 2 must be a finite number.");
        }
        if (unit1 == null) {
            throw new IllegalArgumentException("Unit 1 must not be null.");
        }
        if (unit2 == null) {
            throw new IllegalArgumentException("Unit 2 must not be null.");
        }
        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }
        
        QuantityLength quantity1 = new QuantityLength(value1, unit1);
        QuantityLength quantity2 = new QuantityLength(value2, unit2);
        return quantity1.add(quantity2, resultUnit).getValue();
    }

    public static QuantityLength add(QuantityLength length1, QuantityLength length2, LengthUnit resultUnit) {
        if (length1 == null) {
            throw new IllegalArgumentException("Length 1 must not be null.");
        }
        if (length2 == null) {
            throw new IllegalArgumentException("Length 2 must not be null.");
        }
        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }
        
        return length1.add(length2, resultUnit);
    }

    public static QuantityLength add(QuantityLength length1, QuantityLength length2) {
        if (length1 == null) {
            throw new IllegalArgumentException("Length 1 must not be null.");
        }
        if (length2 == null) {
            throw new IllegalArgumentException("Length 2 must not be null.");
        }
        
        return length1.add(length2);
    }

    public static void demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        double result = convert(value, fromUnit, toUnit);
        System.out.printf("Converting %.2f %s to %s: %.6f%n", value, fromUnit, toUnit, result);
    }

    public static void demonstrateLengthConversion(QuantityLength quantity, LengthUnit targetUnit) {
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity must not be null.");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit must not be null.");
        }
        
        QuantityLength converted = quantity.convertTo(targetUnit);
        System.out.printf("Converting %s to %s: %.6f%n", quantity, targetUnit, converted.getValue());
    }

    public static void demonstrateLengthEquality(QuantityLength length1, QuantityLength length2) {
        if (length1 == null || length2 == null) {
            throw new IllegalArgumentException("Both lengths must not be null.");
        }
        
        boolean isEqual = length1.equals(length2);
        System.out.printf("%s equals %s: %s%n", length1, length2, isEqual);
    }

    public static void demonstrateLengthComparison(double value1, LengthUnit unit1, 
                                                   double value2, LengthUnit unit2) {
        QuantityLength length1 = new QuantityLength(value1, unit1);
        QuantityLength length2 = new QuantityLength(value2, unit2);
        demonstrateLengthEquality(length1, length2);
    }

    public static void demonstrateLengthAddition(double value1, LengthUnit unit1, 
                                                 double value2, LengthUnit unit2, LengthUnit resultUnit) {
        double result = add(value1, unit1, value2, unit2, resultUnit);
        System.out.printf("Adding %.2f %s + %.2f %s = %.6f %s%n", value1, unit1, value2, unit2, result, resultUnit);
    }

    public static void demonstrateLengthAddition(QuantityLength length1, QuantityLength length2, LengthUnit resultUnit) {
        if (length1 == null || length2 == null) {
            throw new IllegalArgumentException("Both lengths must not be null.");
        }
        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }
        
        QuantityLength result = length1.add(length2, resultUnit);
        System.out.printf("Adding %s + %s = %s%n", length1, length2, result);
    }

    public static void demonstrateLengthAddition(QuantityLength length1, QuantityLength length2) {
        if (length1 == null || length2 == null) {
            throw new IllegalArgumentException("Both lengths must not be null.");
        }
        
        QuantityLength result = length1.add(length2);
        System.out.printf("Adding %s + %s = %s%n", length1, length2, result);
    }

    public static boolean checkFeetEquality(double value1, double value2) {
        QuantityLength feet1 = new QuantityLength(value1, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(value2, LengthUnit.FEET);
        return feet1.equals(feet2);
    }

    public static boolean checkInchesEquality(double value1, double value2) {
        QuantityLength inches1 = new QuantityLength(value1, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(value2, LengthUnit.INCH);
        return inches1.equals(inches2);
    }

    public static boolean checkYardEquality(double value1, double value2) {
        QuantityLength yard1 = new QuantityLength(value1, LengthUnit.YARD);
        QuantityLength yard2 = new QuantityLength(value2, LengthUnit.YARD);
        return yard1.equals(yard2);
    }

    public static boolean checkCentimeterEquality(double value1, double value2) {
        QuantityLength cm1 = new QuantityLength(value1, LengthUnit.CENTIMETER);
        QuantityLength cm2 = new QuantityLength(value2, LengthUnit.CENTIMETER);
        return cm1.equals(cm2);
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   QUANTITY MEASUREMENT APPLICATION");
        System.out.println("========================================\n");

        System.out.println("===== UC7: EXPLICIT TARGET UNIT ADDITION =====\n");

        System.out.println("--- Explicit Target Unit: Same as First Operand ---");
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        demonstrateLengthAddition(feet1, inches1, LengthUnit.FEET);

        System.out.println("\n--- Explicit Target Unit: Same as Second Operand ---");
        demonstrateLengthAddition(feet1, inches1, LengthUnit.INCH);

        System.out.println("\n--- Explicit Target Unit: Different from Both Operands ---");
        demonstrateLengthAddition(feet1, inches1, LengthUnit.YARD);

        System.out.println("\n--- Explicit Target Unit: Yards ---");
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet2 = new QuantityLength(3.0, LengthUnit.FEET);
        demonstrateLengthAddition(yard1, feet2, LengthUnit.YARD);

        System.out.println("\n--- Explicit Target Unit: Feet from Inches and Yards ---");
        QuantityLength inches2 = new QuantityLength(36.0, LengthUnit.INCH);
        QuantityLength yard2 = new QuantityLength(1.0, LengthUnit.YARD);
        demonstrateLengthAddition(inches2, yard2, LengthUnit.FEET);

        System.out.println("\n--- Explicit Target Unit: Centimeters ---");
        QuantityLength cm1 = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        QuantityLength inches3 = new QuantityLength(1.0, LengthUnit.INCH);
        demonstrateLengthAddition(cm1, inches3, LengthUnit.CENTIMETER);

        System.out.println("\n--- Explicit Target Unit: With Zero Values ---");
        QuantityLength feet3 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength inches4 = new QuantityLength(0.0, LengthUnit.INCH);
        demonstrateLengthAddition(feet3, inches4, LengthUnit.YARD);

        System.out.println("\n--- Explicit Target Unit: With Negative Values ---");
        QuantityLength feet4 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength feet5 = new QuantityLength(-2.0, LengthUnit.FEET);
        demonstrateLengthAddition(feet4, feet5, LengthUnit.INCH);

        System.out.println("\n--- Commutativity with Explicit Target Unit ---");
        QuantityLength result1 = feet1.add(inches1, LengthUnit.YARD);
        QuantityLength result2 = inches1.add(feet1, LengthUnit.YARD);
        System.out.printf("add(1.0 FEET, 12.0 INCH, YARD) = %s%n", result1);
        System.out.printf("add(12.0 INCH, 1.0 FEET, YARD) = %s%n", result2);
        System.out.printf("Commutativity verified: %b%n", result1.equals(result2));

        System.out.println("\n========================================");
        System.out.println("     Application Execution Complete");
        System.out.println("========================================");
    }
}