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
        System.out.println("     Application Execution Complete");
        System.out.println("========================================");
    }
}