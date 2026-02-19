package com.example;

public class QuantityMeasurementApp 
{
    // Static method for Feet equality check
    public static boolean checkFeetEquality(double value1, double value2) {
        QuantityLength feet1 = new QuantityLength(value1, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(value2, LengthUnit.FEET);
        return feet1.equals(feet2);
    }

    // Static method for Inches equality check
    public static boolean checkInchesEquality(double value1, double value2) {
        QuantityLength inches1 = new QuantityLength(value1, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(value2, LengthUnit.INCH);
        return inches1.equals(inches2);
    }

    // Static method for Yard equality check
    public static boolean checkYardEquality(double value1, double value2) {
        QuantityLength yard1 = new QuantityLength(value1, LengthUnit.YARD);
        QuantityLength yard2 = new QuantityLength(value2, LengthUnit.YARD);
        return yard1.equals(yard2);
    }

    // Static method for Centimeter equality check
    public static boolean checkCentimeterEquality(double value1, double value2) {
        QuantityLength cm1 = new QuantityLength(value1, LengthUnit.CENTIMETER);
        QuantityLength cm2 = new QuantityLength(value2, LengthUnit.CENTIMETER);
        return cm1.equals(cm2);
    }

    public static void main(String[] args) {
        // Using static methods for Feet equality checks
        System.out.println("=== Feet Comparisons ===");
        System.out.println("Comparing 1.0 ft and 1.0 ft: " + checkFeetEquality(1.0, 1.0));
        System.out.println("Comparing 1.0 ft and 2.0 ft: " + checkFeetEquality(1.0, 2.0));
        
        // Direct object comparison for additional cases
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        System.out.println("Comparing 1.0 ft with null: " + feet1.equals(null));
        System.out.println("Comparing 1.0 ft with itself: " + feet1.equals(feet1));
        
        // Using static methods for Inches equality checks
        System.out.println("\n=== Inches Comparisons ===");
        System.out.println("Comparing 1.0 inch and 1.0 inch: " + checkInchesEquality(1.0, 1.0));
        System.out.println("Comparing 1.0 inch and 2.0 inch: " + checkInchesEquality(1.0, 2.0));
        
        // Direct object comparison for additional cases
        QuantityLength inches1 = new QuantityLength(1.0, LengthUnit.INCH);
        System.out.println("Comparing 1.0 inch with null: " + inches1.equals(null));
        System.out.println("Comparing 1.0 inch with itself: " + inches1.equals(inches1));

        // Yard comparisons
        System.out.println("\n=== Yard Comparisons ===");
        System.out.println("Comparing 1.0 yard and 1.0 yard: " + checkYardEquality(1.0, 1.0));
        System.out.println("Comparing 1.0 yard and 2.0 yard: " + checkYardEquality(1.0, 2.0));
        System.out.println("Comparing 1.0 yard and 3.0 ft: "
            + new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(3.0, LengthUnit.FEET)));
        System.out.println("Comparing 1.0 yard and 36.0 inches: "
            + new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(36.0, LengthUnit.INCH)));

        // Centimeter comparisons
        System.out.println("\n=== Centimeter Comparisons ===");
        System.out.println("Comparing 2.0 cm and 2.0 cm: " + checkCentimeterEquality(2.0, 2.0));
        System.out.println("Comparing 1.0 cm and 0.393701 inch: "
            + new QuantityLength(1.0, LengthUnit.CENTIMETER)
                .equals(new QuantityLength(0.393701, LengthUnit.INCH)));
    }
}