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

    public static double convertWeight(double value, WeightUnit source, WeightUnit target) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number.");
        }
        if (source == null) {
            throw new IllegalArgumentException("Source unit must not be null.");
        }
        if (target == null) {
            throw new IllegalArgumentException("Target unit must not be null.");
        }
        
        QuantityWeight quantity = new QuantityWeight(value, source);
        return quantity.convertTo(target).getValue();
    }

    public static double addWeight(double value1, WeightUnit unit1, double value2, WeightUnit unit2, WeightUnit resultUnit) {
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
        
        QuantityWeight quantity1 = new QuantityWeight(value1, unit1);
        QuantityWeight quantity2 = new QuantityWeight(value2, unit2);
        return quantity1.add(quantity2, resultUnit).getValue();
    }

    public static QuantityWeight add(QuantityWeight weight1, QuantityWeight weight2, WeightUnit resultUnit) {
        if (weight1 == null) {
            throw new IllegalArgumentException("Weight 1 must not be null.");
        }
        if (weight2 == null) {
            throw new IllegalArgumentException("Weight 2 must not be null.");
        }
        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }
        
        return weight1.add(weight2, resultUnit);
    }

    public static QuantityWeight add(QuantityWeight weight1, QuantityWeight weight2) {
        if (weight1 == null) {
            throw new IllegalArgumentException("Weight 1 must not be null.");
        }
        if (weight2 == null) {
            throw new IllegalArgumentException("Weight 2 must not be null.");
        }
        
        return weight1.add(weight2);
    }

    public static void demonstrateWeightConversion(double value, WeightUnit fromUnit, WeightUnit toUnit) {
        double result = convertWeight(value, fromUnit, toUnit);
        System.out.printf("Converting %.2f %s to %s: %.6f%n", value, fromUnit, toUnit, result);
    }

    public static void demonstrateWeightConversion(QuantityWeight quantity, WeightUnit targetUnit) {
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity must not be null.");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit must not be null.");
        }
        
        QuantityWeight converted = quantity.convertTo(targetUnit);
        System.out.printf("Converting %s to %s: %.6f%n", quantity, targetUnit, converted.getValue());
    }

    public static void demonstrateWeightEquality(QuantityWeight weight1, QuantityWeight weight2) {
        if (weight1 == null || weight2 == null) {
            throw new IllegalArgumentException("Both weights must not be null.");
        }
        
        boolean isEqual = weight1.equals(weight2);
        System.out.printf("%s equals %s: %s%n", weight1, weight2, isEqual);
    }

    public static void demonstrateWeightComparison(double value1, WeightUnit unit1, 
                                                   double value2, WeightUnit unit2) {
        QuantityWeight weight1 = new QuantityWeight(value1, unit1);
        QuantityWeight weight2 = new QuantityWeight(value2, unit2);
        demonstrateWeightEquality(weight1, weight2);
    }

    public static void demonstrateWeightAddition(double value1, WeightUnit unit1, 
                                                 double value2, WeightUnit unit2, WeightUnit resultUnit) {
        double result = addWeight(value1, unit1, value2, unit2, resultUnit);
        System.out.printf("Adding %.2f %s + %.2f %s = %.6f %s%n", value1, unit1, value2, unit2, result, resultUnit);
    }

    public static void demonstrateWeightAddition(QuantityWeight weight1, QuantityWeight weight2, WeightUnit resultUnit) {
        if (weight1 == null || weight2 == null) {
            throw new IllegalArgumentException("Both weights must not be null.");
        }
        if (resultUnit == null) {
            throw new IllegalArgumentException("Result unit must not be null.");
        }
        
        QuantityWeight result = weight1.add(weight2, resultUnit);
        System.out.printf("Adding %s + %s = %s%n", weight1, weight2, result);
    }

    public static void demonstrateWeightAddition(QuantityWeight weight1, QuantityWeight weight2) {
        if (weight1 == null || weight2 == null) {
            throw new IllegalArgumentException("Both weights must not be null.");
        }
        
        QuantityWeight result = weight1.add(weight2);
        System.out.printf("Adding %s + %s = %s%n", weight1, weight2, result);
    }

    public static boolean checkKilogramEquality(double value1, double value2) {
        QuantityWeight kg1 = new QuantityWeight(value1, WeightUnit.KILOGRAM);
        QuantityWeight kg2 = new QuantityWeight(value2, WeightUnit.KILOGRAM);
        return kg1.equals(kg2);
    }

    public static boolean checkGramEquality(double value1, double value2) {
        QuantityWeight g1 = new QuantityWeight(value1, WeightUnit.GRAM);
        QuantityWeight g2 = new QuantityWeight(value2, WeightUnit.GRAM);
        return g1.equals(g2);
    }

    public static boolean checkPoundEquality(double value1, double value2) {
        QuantityWeight lb1 = new QuantityWeight(value1, WeightUnit.POUND);
        QuantityWeight lb2 = new QuantityWeight(value2, WeightUnit.POUND);
        return lb1.equals(lb2);
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   QUANTITY MEASUREMENT APPLICATION");
        System.out.println("========================================\n");

        System.out.println("===== WEIGHT MEASUREMENT SUPPORT =====\n");

        System.out.println("--- Equality ---");
        QuantityWeight kg1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g1000 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        System.out.printf("1.0 kg equals 1000.0 g: %b%n", kg1.equals(g1000));

        System.out.println("\n--- Unit Conversion ---");
        System.out.printf("500.0 g to base unit: %.2f kg%n", WeightUnit.GRAM.convertToBaseUnit(500.0));
        
        System.out.println("\n--- Quantity Conversion ---");
        QuantityWeight kg2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);
        System.out.printf("2.0 kg to grams: %s%n", kg2.convertTo(WeightUnit.GRAM));

        System.out.println("\n--- Addition (Explicit Target) ---");
        QuantityWeight kg3 = new QuantityWeight(3.0, WeightUnit.KILOGRAM);
        QuantityWeight g500 = new QuantityWeight(500.0, WeightUnit.GRAM);
        System.out.printf("3.0 kg + 500.0 g = %s%n", kg3.add(g500, WeightUnit.KILOGRAM));

        System.out.println("\n--- Addition (Implicit Target) ---");
        QuantityWeight kg1_5 = new QuantityWeight(1.5, WeightUnit.KILOGRAM);
        QuantityWeight g250 = new QuantityWeight(250.0, WeightUnit.GRAM);
        System.out.printf("1.5 kg + 250.0 g = %s%n", kg1_5.add(g250));

        System.out.println("\n========================================");
        System.out.println("     Application Execution Complete");
        System.out.println("========================================");
    }
}