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


        System.out.println("===== UC4: LENGTH EQUALITY & COMPARISON =====\n");
        
        System.out.println("=== Feet Comparisons ===");
        System.out.println("Comparing 1.0 ft and 1.0 ft: " + checkFeetEquality(1.0, 1.0));
        System.out.println("Comparing 1.0 ft and 2.0 ft: " + checkFeetEquality(1.0, 2.0));
        
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        System.out.println("Comparing 1.0 ft with itself: " + feet1.equals(feet1));
        System.out.println("Comparing 1.0 ft with null: " + feet1.equals(null));
        
        System.out.println("\n=== Inches Comparisons ===");
        System.out.println("Comparing 1.0 inch and 1.0 inch: " + checkInchesEquality(1.0, 1.0));
        System.out.println("Comparing 1.0 inch and 2.0 inch: " + checkInchesEquality(1.0, 2.0));
        
        QuantityLength inches1 = new QuantityLength(1.0, LengthUnit.INCH);
        System.out.println("Comparing 1.0 inch with itself: " + inches1.equals(inches1));

        System.out.println("\n=== Yard Comparisons ===");
        System.out.println("Comparing 1.0 yard and 1.0 yard: " + checkYardEquality(1.0, 1.0));
        System.out.println("Comparing 1.0 yard and 2.0 yard: " + checkYardEquality(1.0, 2.0));
        demonstrateLengthComparison(1.0, LengthUnit.YARD, 3.0, LengthUnit.FEET);
        demonstrateLengthComparison(1.0, LengthUnit.YARD, 36.0, LengthUnit.INCH);

        System.out.println("\n=== Centimeter Comparisons ===");
        System.out.println("Comparing 2.0 cm and 2.0 cm: " + checkCentimeterEquality(2.0, 2.0));
        demonstrateLengthComparison(1.0, LengthUnit.CENTIMETER, 0.393701, LengthUnit.INCH);

        System.out.println("\n========================================");
        System.out.println("===== UC5: UNIT CONVERSION =====\n");

        System.out.println("--- Basic Unit Conversion ---");
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH);
        demonstrateLengthConversion(3.0, LengthUnit.YARD, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARD);

        System.out.println("\n--- Cross-Unit Conversion ---");
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.CENTIMETER);

        System.out.println("\n--- Zero Value Conversion ---");
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCH);

        System.out.println("\n--- Negative Value Conversion ---");
        demonstrateLengthConversion(-1.0, LengthUnit.FEET, LengthUnit.INCH);

        System.out.println("\n--- Same-Unit Conversion ---");
        demonstrateLengthConversion(5.0, LengthUnit.FEET, LengthUnit.FEET);

        System.out.println("\n--- Using Instance Method (Method Overloading) ---");
        QuantityLength lengthInYards = new QuantityLength(2.0, LengthUnit.YARD);
        demonstrateLengthConversion(lengthInYards, LengthUnit.INCH);
        
        QuantityLength lengthInCm = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        demonstrateLengthConversion(lengthInCm, LengthUnit.INCH);

        System.out.println("\n--- Bidirectional Conversion (Round-Trip) ---");
        double original = 1.0;
        double feet2Inches = convert(original, LengthUnit.FEET, LengthUnit.INCH);
        double inches2Feet = convert(feet2Inches, LengthUnit.INCH, LengthUnit.FEET);
        System.out.printf("Original: %.6f FEET -> %.6f INCH -> %.6f FEET (Round-trip preserved: %b)%n",
                original, feet2Inches, inches2Feet, 
                Math.abs(original - inches2Feet) < EPSILON);

        System.out.println("\n========================================");
        System.out.println("===== UC6: ADDITION OPERATIONS =====\n");

        System.out.println("--- Same-Unit Addition ---");
        demonstrateLengthAddition(1.0, LengthUnit.FEET, 2.0, LengthUnit.FEET, LengthUnit.FEET);
        demonstrateLengthAddition(6.0, LengthUnit.INCH, 6.0, LengthUnit.INCH, LengthUnit.INCH);
        demonstrateLengthAddition(1.0, LengthUnit.YARD, 3.0, LengthUnit.FEET, LengthUnit.YARD);

        System.out.println("\n--- Cross-Unit Addition ---");
        demonstrateLengthAddition(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH, LengthUnit.FEET);
        demonstrateLengthAddition(12.0, LengthUnit.INCH, 1.0, LengthUnit.FEET, LengthUnit.INCH);
        demonstrateLengthAddition(1.0, LengthUnit.YARD, 3.0, LengthUnit.FEET, LengthUnit.YARD);
        demonstrateLengthAddition(36.0, LengthUnit.INCH, 1.0, LengthUnit.YARD, LengthUnit.INCH);

        System.out.println("\n--- Cross-Unit Addition (Centimeters) ---");
        demonstrateLengthAddition(2.54, LengthUnit.CENTIMETER, 1.0, LengthUnit.INCH, LengthUnit.CENTIMETER);

        System.out.println("\n--- Addition with Zero Values ---");
        demonstrateLengthAddition(5.0, LengthUnit.FEET, 0.0, LengthUnit.INCH, LengthUnit.FEET);

        System.out.println("\n--- Addition with Negative Values ---");
        demonstrateLengthAddition(5.0, LengthUnit.FEET, -2.0, LengthUnit.FEET, LengthUnit.FEET);

        System.out.println("\n--- Using Instance Method (Default Unit) ---");
        QuantityLength addFeet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength addInches1 = new QuantityLength(12.0, LengthUnit.INCH);
        demonstrateLengthAddition(addFeet1, addInches1);

        QuantityLength addInches2 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength addFeet2 = new QuantityLength(1.0, LengthUnit.FEET);
        demonstrateLengthAddition(addInches2, addFeet2);

        System.out.println("\n--- Using Instance Method (Specified Unit) ---");
        QuantityLength addYard1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength addFeet3 = new QuantityLength(3.0, LengthUnit.FEET);
        demonstrateLengthAddition(addYard1, addFeet3, LengthUnit.YARD);

        System.out.println("\n--- Commutativity Check ---");
        double result1 = add(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH, LengthUnit.FEET);
        double result2 = add(12.0, LengthUnit.INCH, 1.0, LengthUnit.FEET, LengthUnit.FEET);
        System.out.printf("add(1.0 FEET, 12.0 INCH, FEET) = %.6f%n", result1);
        System.out.printf("add(12.0 INCH, 1.0 FEET, FEET) = %.6f%n", result2);
        System.out.printf("Commutativity verified: %b%n", Math.abs(result1 - result2) < EPSILON);

        System.out.println("\n========================================");
        System.out.println("     Application Execution Complete");
        System.out.println("========================================");
    }
}