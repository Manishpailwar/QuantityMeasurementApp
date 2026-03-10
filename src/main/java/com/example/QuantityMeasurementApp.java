package com.example;


public class QuantityMeasurementApp {
    private static final double EPSILON = 1e-6;

    public static <U extends Measurable> double convert(double value, U source, U target) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number.");
        }
        if (source == null) {
            throw new IllegalArgumentException("Source unit must not be null.");
        }
        if (target == null) {
            throw new IllegalArgumentException("Target unit must not be null.");
        }
        
        Quantity<U> quantity = new Quantity<>(value, source);
        return quantity.convertTo(target).getValue();
    }

    public static <U extends Measurable> double add(double value1, U unit1, double value2, U unit2, U resultUnit) {
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
        
        Quantity<U> quantity1 = new Quantity<>(value1, unit1);
        Quantity<U> quantity2 = new Quantity<>(value2, unit2);
        return quantity1.add(quantity2, resultUnit).getValue();
    }

    public static <U extends Measurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2, U resultUnit) {
        if (quantity1 == null) {
            throw new IllegalArgumentException("First quantity must not be null.");
        }
        if (quantity2 == null) {
            throw new IllegalArgumentException("Second quantity must not be null.");
        }
        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }
        
        return quantity1.add(quantity2, resultUnit);
    }

    public static <U extends Measurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2) {
        if (quantity1 == null) {
            throw new IllegalArgumentException("First quantity must not be null.");
        }
        if (quantity2 == null) {
            throw new IllegalArgumentException("Second quantity must not be null.");
        }
        
        return quantity1.add(quantity2);
    }

    public static <U extends Measurable> void demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {
        if (quantity1 == null || quantity2 == null) {
            throw new IllegalArgumentException("Both quantities must not be null.");
        }
        
        boolean isEqual = quantity1.equals(quantity2);
        System.out.printf("%s equals %s: %s%n", quantity1, quantity2, isEqual);
    }

    public static <U extends Measurable> void demonstrateEquality(double value1, U unit1, double value2, U unit2) {
        Quantity<U> quantity1 = new Quantity<>(value1, unit1);
        Quantity<U> quantity2 = new Quantity<>(value2, unit2);
        demonstrateEquality(quantity1, quantity2);
    }

    public static <U extends Measurable> void demonstrateConversion(Quantity<U> quantity, U targetUnit) {
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity must not be null.");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit must not be null.");
        }
        
        Quantity<U> converted = quantity.convertTo(targetUnit);
        System.out.printf("Converting %s to %s: %.6f%n", quantity, targetUnit.getUnitName(), converted.getValue());
    }

    public static <U extends Measurable> void demonstrateConversion(double value, U fromUnit, U toUnit) {
        double result = convert(value, fromUnit, toUnit);
        System.out.printf("Converting %.2f %s to %s: %.6f%n", value, fromUnit.getUnitName(), toUnit.getUnitName(), result);
    }

    public static <U extends Measurable> void demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2) {
        if (quantity1 == null || quantity2 == null) {
            throw new IllegalArgumentException("Both quantities must not be null.");
        }
        
        Quantity<U> result = quantity1.add(quantity2);
        System.out.printf("Adding %s + %s = %s%n", quantity1, quantity2, result);
    }

    public static <U extends Measurable> void demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U resultUnit) {
        if (quantity1 == null || quantity2 == null) {
            throw new IllegalArgumentException("Both quantities must not be null.");
        }
        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }
        
        Quantity<U> result = quantity1.add(quantity2, resultUnit);
        System.out.printf("Adding %s + %s = %s%n", quantity1, quantity2, result);
    }

    public static <U extends Measurable> void demonstrateAddition(double value1, U unit1, double value2, U unit2, U resultUnit) {
        double result = add(value1, unit1, value2, unit2, resultUnit);
        System.out.printf("Adding %.2f %s + %.2f %s = %.6f %s%n", value1, unit1.getUnitName(), value2, unit2.getUnitName(), result, resultUnit.getUnitName());
    }

    // ===== BACKWARD COMPATIBILITY METHODS FOR LENGTH =====
    // These methods maintain API compatibility with UC1-UC8

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        return QuantityMeasurementApp.convert(value, (Measurable) source, (Measurable) target);
    }

    public static double add(double value1, LengthUnit unit1, double value2, LengthUnit unit2, LengthUnit resultUnit) {
        return QuantityMeasurementApp.<LengthUnit>add(value1, unit1, value2, unit2, resultUnit);
    }

    public static void demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        demonstrateConversion(value, fromUnit, toUnit);
    }

    public static void demonstrateLengthConversion(Quantity<LengthUnit> quantity, LengthUnit targetUnit) {
        demonstrateConversion(quantity, targetUnit);
    }

    public static void demonstrateLengthEquality(Quantity<LengthUnit> length1, Quantity<LengthUnit> length2) {
        demonstrateEquality(length1, length2);
    }

    public static void demonstrateLengthComparison(double value1, LengthUnit unit1, double value2, LengthUnit unit2) {
        demonstrateEquality(value1, unit1, value2, unit2);
    }

    public static void demonstrateLengthAddition(double value1, LengthUnit unit1, double value2, LengthUnit unit2, LengthUnit resultUnit) {
        demonstrateAddition(value1, unit1, value2, unit2, resultUnit);
    }

    public static void demonstrateLengthAddition(Quantity<LengthUnit> length1, Quantity<LengthUnit> length2, LengthUnit resultUnit) {
        demonstrateAddition(length1, length2, resultUnit);
    }

    public static void demonstrateLengthAddition(Quantity<LengthUnit> length1, Quantity<LengthUnit> length2) {
        demonstrateAddition(length1, length2);
    }

    public static boolean checkFeetEquality(double value1, double value2) {
        Quantity<LengthUnit> feet1 = new Quantity<>(value1, LengthUnit.FEET);
        Quantity<LengthUnit> feet2 = new Quantity<>(value2, LengthUnit.FEET);
        return feet1.equals(feet2);
    }

    public static boolean checkInchesEquality(double value1, double value2) {
        Quantity<LengthUnit> inches1 = new Quantity<>(value1, LengthUnit.INCH);
        Quantity<LengthUnit> inches2 = new Quantity<>(value2, LengthUnit.INCH);
        return inches1.equals(inches2);
    }

    public static boolean checkYardEquality(double value1, double value2) {
        Quantity<LengthUnit> yard1 = new Quantity<>(value1, LengthUnit.YARD);
        Quantity<LengthUnit> yard2 = new Quantity<>(value2, LengthUnit.YARD);
        return yard1.equals(yard2);
    }

    public static boolean checkCentimeterEquality(double value1, double value2) {
        Quantity<LengthUnit> cm1 = new Quantity<>(value1, LengthUnit.CENTIMETER);
        Quantity<LengthUnit> cm2 = new Quantity<>(value2, LengthUnit.CENTIMETER);
        return cm1.equals(cm2);
    }

    // ===== BACKWARD COMPATIBILITY METHODS FOR WEIGHT =====
    // These methods maintain API compatibility with UC9

    public static double convertWeight(double value, WeightUnit source, WeightUnit target) {
        return QuantityMeasurementApp.convert(value, source, target);
    }

    public static double addWeight(double value1, WeightUnit unit1, double value2, WeightUnit unit2, WeightUnit resultUnit) {
        return QuantityMeasurementApp.<WeightUnit>add(value1, unit1, value2, unit2, resultUnit);
    }

    public static void demonstrateWeightConversion(double value, WeightUnit fromUnit, WeightUnit toUnit) {
        demonstrateConversion(value, fromUnit, toUnit);
    }

    public static void demonstrateWeightConversion(Quantity<WeightUnit> quantity, WeightUnit targetUnit) {
        demonstrateConversion(quantity, targetUnit);
    }

    public static void demonstrateWeightEquality(Quantity<WeightUnit> weight1, Quantity<WeightUnit> weight2) {
        demonstrateEquality(weight1, weight2);
    }

    public static void demonstrateWeightComparison(double value1, WeightUnit unit1, double value2, WeightUnit unit2) {
        demonstrateEquality(value1, unit1, value2, unit2);
    }

    public static void demonstrateWeightAddition(double value1, WeightUnit unit1, double value2, WeightUnit unit2, WeightUnit resultUnit) {
        demonstrateAddition(value1, unit1, value2, unit2, resultUnit);
    }

    public static void demonstrateWeightAddition(Quantity<WeightUnit> weight1, Quantity<WeightUnit> weight2, WeightUnit resultUnit) {
        demonstrateAddition(weight1, weight2, resultUnit);
    }

    public static void demonstrateWeightAddition(Quantity<WeightUnit> weight1, Quantity<WeightUnit> weight2) {
        demonstrateAddition(weight1, weight2);
    }

    public static boolean checkKilogramEquality(double value1, double value2) {
        Quantity<WeightUnit> kg1 = new Quantity<>(value1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> kg2 = new Quantity<>(value2, WeightUnit.KILOGRAM);
        return kg1.equals(kg2);
    }

    public static boolean checkGramEquality(double value1, double value2) {
        Quantity<WeightUnit> g1 = new Quantity<>(value1, WeightUnit.GRAM);
        Quantity<WeightUnit> g2 = new Quantity<>(value2, WeightUnit.GRAM);
        return g1.equals(g2);
    }

    public static boolean checkPoundEquality(double value1, double value2) {
        Quantity<WeightUnit> lb1 = new Quantity<>(value1, WeightUnit.POUND);
        Quantity<WeightUnit> lb2 = new Quantity<>(value2, WeightUnit.POUND);
        return lb1.equals(lb2);
    }

    /**
     * Main method demonstrating generic quantity operations.
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   QUANTITY MEASUREMENT APPLICATION");
        System.out.println("========================================\n");

        System.out.println("===== GENERIC WEIGHT MEASUREMENT SUPPORT =====\n");

        System.out.println("--- Equality ---");
        Quantity<WeightUnit> kg1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g1000 = new Quantity<>(1000.0, WeightUnit.GRAM);
        System.out.printf("1.0 KILOGRAM equals 1000.0 GRAM: %b%n", kg1.equals(g1000));

        System.out.println("\n--- Unit Conversion ---");
        System.out.printf("500.0 GRAM to base unit: %.2f KILOGRAM%n", WeightUnit.GRAM.convertToBaseUnit(500.0));
        
        System.out.println("\n--- Quantity Conversion ---");
        Quantity<WeightUnit> kg2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        System.out.printf("2.0 KILOGRAM to GRAM: %s%n", kg2.convertTo(WeightUnit.GRAM));

        System.out.println("\n--- Addition (Explicit Target) ---");
        Quantity<WeightUnit> kg3 = new Quantity<>(3.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g500 = new Quantity<>(500.0, WeightUnit.GRAM);
        System.out.printf("3.0 KILOGRAM + 500.0 GRAM = %s%n", kg3.add(g500, WeightUnit.KILOGRAM));

        System.out.println("\n--- Addition (Implicit Target) ---");
        Quantity<WeightUnit> kg1_5 = new Quantity<>(1.5, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g250 = new Quantity<>(250.0, WeightUnit.GRAM);
        System.out.printf("1.5 KILOGRAM + 250.0 GRAM = %s%n", kg1_5.add(g250));

        System.out.println("\n===== GENERIC LENGTH MEASUREMENT SUPPORT =====\n");

        System.out.println("--- Equality ---");
        Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inch12 = new Quantity<>(12.0, LengthUnit.INCH);
        System.out.printf("1.0 FEET equals 12.0 INCH: %b%n", feet1.equals(inch12));

        System.out.println("\n--- Quantity Conversion ---");
        Quantity<LengthUnit> feet2 = new Quantity<>(2.0, LengthUnit.FEET);
        System.out.printf("2.0 FEET to INCH: %s%n", feet2.convertTo(LengthUnit.INCH));

        System.out.println("\n--- Addition (Explicit Target) ---");
        Quantity<LengthUnit> feet3 = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> inch24 = new Quantity<>(24.0, LengthUnit.INCH);
        System.out.printf("3.0 FEET + 24.0 INCH = %s%n", feet3.add(inch24, LengthUnit.FEET));

        System.out.println("\n===== CROSS-CATEGORY TYPE SAFETY =====\n");
        System.out.println("--- Preventing Invalid Cross-Category Comparisons ---");
        Quantity<LengthUnit> foot = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        System.out.printf("1.0 FEET equals 1.0 KILOGRAM: %b (correctly prevents cross-category comparison)%n", foot.equals(kg));

        System.out.println("\n========================================");
        System.out.println("     Application Execution Complete");
        System.out.println("========================================");
    }
}