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

        System.out.println("===== UC8: REFACTORED DESIGN - UNIT RESPONSIBILITY =====\n");

        System.out.println("--- LengthUnit Conversion Methods ---");
        System.out.printf("LengthUnit.FEET.convertToBaseUnit(12.0) = %.2f feet%n", 
            LengthUnit.FEET.convertToBaseUnit(12.0));
        System.out.printf("LengthUnit.INCH.convertToBaseUnit(12.0) = %.2f feet%n", 
            LengthUnit.INCH.convertToBaseUnit(12.0));
        System.out.printf("LengthUnit.YARD.convertToBaseUnit(1.0) = %.2f feet%n", 
            LengthUnit.YARD.convertToBaseUnit(1.0));
        System.out.printf("LengthUnit.CENTIMETER.convertToBaseUnit(30.48) = %.2f feet%n", 
            LengthUnit.CENTIMETER.convertToBaseUnit(30.48));

        System.out.println("\n--- From Base Unit Conversions ---");
        System.out.printf("LengthUnit.FEET.convertFromBaseUnit(2.0) = %.2f feet%n", 
            LengthUnit.FEET.convertFromBaseUnit(2.0));
        System.out.printf("LengthUnit.INCH.convertFromBaseUnit(1.0) = %.2f inches%n", 
            LengthUnit.INCH.convertFromBaseUnit(1.0));
        System.out.printf("LengthUnit.YARD.convertFromBaseUnit(3.0) = %.2f yards%n", 
            LengthUnit.YARD.convertFromBaseUnit(3.0));
        System.out.printf("LengthUnit.CENTIMETER.convertFromBaseUnit(1.0) = %.2f centimeters%n", 
            LengthUnit.CENTIMETER.convertFromBaseUnit(1.0));

        System.out.println("\n--- Refactored QuantityLength Operations ---");
        QuantityLength uc8_feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength uc8_inches = new QuantityLength(12.0, LengthUnit.INCH);
        System.out.printf("Quantity(1.0, FEET).equals(Quantity(12.0, INCHES)) = %b%n", 
            uc8_feet.equals(uc8_inches));
        
        QuantityLength uc8_converted = uc8_feet.convertTo(LengthUnit.INCH);
        System.out.printf("Quantity(1.0, FEET).convertTo(INCHES) = %s%n", uc8_converted);
        
        QuantityLength uc8_sum = uc8_feet.add(uc8_inches, LengthUnit.FEET);
        System.out.printf("Quantity(1.0, FEET).add(Quantity(12.0, INCHES), FEET) = %s%n", uc8_sum);
        
        QuantityLength uc8_sumYards = uc8_feet.add(uc8_inches, LengthUnit.YARD);
        System.out.printf("Quantity(1.0, FEET).add(Quantity(12.0, INCHES), YARDS) = %s%n", uc8_sumYards);

        System.out.println("\n--- Delegation Pattern Demonstration ---");
        System.out.println("QuantityLength now delegates all conversion logic to LengthUnit");
        System.out.println("LengthUnit is responsible for: convertToBaseUnit() and convertFromBaseUnit()");
        System.out.println("QuantityLength is responsible for: equality, arithmetic, and value management");
        System.out.println("Design follows Single Responsibility Principle (SRP)");

        System.out.println("\n========================================");
        System.out.println("     Application Execution Complete");
        System.out.println("========================================");
    }
}