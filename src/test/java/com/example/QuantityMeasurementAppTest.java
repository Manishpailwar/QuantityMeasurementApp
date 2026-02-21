package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;

    // UC4: EQUALITY TEST CASES
    @Test
    @DisplayName("Given two feet measurements of 1.0, when compared, then should be equal")
    public void testEquality_SameValue() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(feet1, feet2, "The measurements should be identical");
    }

    @Test
    @DisplayName("Given two feet measurements of 1.0 and 2.0, when compared, then should not be equal")
    public void testEquality_DifferentValue() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(2.0, LengthUnit.FEET);
        assertNotEquals(feet1, feet2, "1.0 ft should not be equal to 2.0 ft");
    }

    @Test
    @DisplayName("Given a feet measurement, when compared to null, then should not be equal")
    public void testEquality_NullComparison() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertNotEquals(feet1, null, "Feet object should not be equal to null");
    }

    @Test
    @DisplayName("Given a feet measurement, when compared to non-Feet object, then should not be equal")
    public void testEquality_NonNumericInput() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        String nonNumeric = "1.0";
        assertNotEquals(feet1, nonNumeric, "Feet object should not be equal to non-Feet object");
    }

    @Test
    @DisplayName("Given a feet measurement, when compared to itself, then should be equal")
    public void testEquality_SameReference() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(feet1, feet1, "Feet object should be equal to itself (reflexive)");
    }

    @Test
    @DisplayName("Given two equal feet measurements, when compared in any order, then should be symmetric")
    public void testEquality_SymmetricProperty() {
        QuantityLength feet1 = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(3.0, LengthUnit.FEET);
        assertEquals(feet1, feet2, "Equality should be symmetric");
        assertEquals(feet2, feet1, "Equality should be symmetric");
    }

    @Test
    @DisplayName("Given three equal feet measurements, when compared transitively, then should be equal")
    public void testEquality_TransitiveProperty() {
        QuantityLength feet1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength feet3 = new QuantityLength(5.0, LengthUnit.FEET);
        assertEquals(feet1, feet2, "Equality should be transitive");
        assertEquals(feet2, feet3, "Equality should be transitive");
        assertEquals(feet1, feet3, "Equality should be transitive");
    }

    @Test
    @DisplayName("Given two feet measurements of 0.0, when compared, then should be equal")
    public void testEquality_ZeroValue() {
        QuantityLength feet1 = new QuantityLength(0.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(0.0, LengthUnit.FEET);
        assertEquals(feet1, feet2, "0.0 ft should be equal to 0.0 ft");
    }

    @Test
    @DisplayName("Given two feet measurements of -1.0, when compared, then should be equal")
    public void testEquality_NegativeValue() {
        QuantityLength feet1 = new QuantityLength(-1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(-1.0, LengthUnit.FEET);
        assertEquals(feet1, feet2, "-1.0 ft should be equal to -1.0 ft");
    }

    @Test
    @DisplayName("Given two feet measurements with decimal values, when compared, then should handle precision")
    public void testEquality_DecimalPrecision() {
        QuantityLength feet1 = new QuantityLength(1.5, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.5, LengthUnit.FEET);
        assertEquals(feet1, feet2, "1.5 ft should be equal to 1.5 ft");
    }

    @Test
    @DisplayName("Given two inches measurements of 1.0, when compared, then should be equal")
    public void testInchesEquality_SameValue() {
        QuantityLength inches1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(1.0, LengthUnit.INCH);
        assertEquals(inches1, inches2, "The measurements should be identical");
    }

    @Test
    @DisplayName("Given two inches measurements of 1.0 and 2.0, when compared, then should not be equal")
    public void testInchesEquality_DifferentValue() {
        QuantityLength inches1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(2.0, LengthUnit.INCH);
        assertNotEquals(inches1, inches2, "1.0 inch should not be equal to 2.0 inch");
    }

    @Test
    @DisplayName("Given an inches measurement, when compared to null, then should not be equal")
    public void testInchesEquality_NullComparison() {
        QuantityLength inches1 = new QuantityLength(1.0, LengthUnit.INCH);
        assertNotEquals(inches1, null, "Inches object should not be equal to null");
    }

    @Test
    @DisplayName("Given an inches measurement, when compared to non-Inches object, then should not be equal")
    public void testInchesEquality_NonNumericInput() {
        QuantityLength inches1 = new QuantityLength(1.0, LengthUnit.INCH);
        String nonNumeric = "1.0";
        assertNotEquals(inches1, nonNumeric, "Inches object should not be equal to non-Inches object");
    }

    @Test
    @DisplayName("Given an inches measurement, when compared to itself, then should be equal")
    public void testInchesEquality_SameReference() {
        QuantityLength inches1 = new QuantityLength(1.0, LengthUnit.INCH);
        assertEquals(inches1, inches1, "Inches object should be equal to itself (reflexive)");
    }

    @Test
    @DisplayName("Given two equal inches measurements, when compared in any order, then should be symmetric")
    public void testInchesEquality_SymmetricProperty() {
        QuantityLength inches1 = new QuantityLength(3.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(3.0, LengthUnit.INCH);
        assertEquals(inches1, inches2, "Equality should be symmetric");
        assertEquals(inches2, inches1, "Equality should be symmetric");
    }

    @Test
    @DisplayName("Given three equal inches measurements, when compared transitively, then should be equal")
    public void testInchesEquality_TransitiveProperty() {
        QuantityLength inches1 = new QuantityLength(5.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(5.0, LengthUnit.INCH);
        QuantityLength inches3 = new QuantityLength(5.0, LengthUnit.INCH);
        assertEquals(inches1, inches2, "Equality should be transitive");
        assertEquals(inches2, inches3, "Equality should be transitive");
        assertEquals(inches1, inches3, "Equality should be transitive");
    }

    @Test
    @DisplayName("Given two inches measurements of 0.0, when compared, then should be equal")
    public void testInchesEquality_ZeroValue() {
        QuantityLength inches1 = new QuantityLength(0.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(0.0, LengthUnit.INCH);
        assertEquals(inches1, inches2, "0.0 inch should be equal to 0.0 inch");
    }

    @Test
    @DisplayName("Given two inches measurements of -1.0, when compared, then should be equal")
    public void testInchesEquality_NegativeValue() {
        QuantityLength inches1 = new QuantityLength(-1.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(-1.0, LengthUnit.INCH);
        assertEquals(inches1, inches2, "-1.0 inch should be equal to -1.0 inch");
    }

    @Test
    @DisplayName("Given two inches measurements with decimal values, when compared, then should handle precision")
    public void testInchesEquality_DecimalPrecision() {
        QuantityLength inches1 = new QuantityLength(1.5, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(1.5, LengthUnit.INCH);
        assertEquals(inches1, inches2, "1.5 inch should be equal to 1.5 inch");
    }

    @Test
    @DisplayName("Given a Feet object and an Inches object, when compared, then should not be equal")
    public void testInchesEquality_DifferentTypes() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(1.0, LengthUnit.INCH);
        assertNotEquals(feet, inches, "Feet and Inches objects should not be equal (different units)");
    }

    @Test
    @DisplayName("Given equivalent feet and inches, when compared, then should be equal")
    public void testEquality_InchToFeet_EquivalentValue() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCH);
        assertEquals(feet, inches, "1.0 ft should be equal to 12.0 inch");
    }

    @Test
    @DisplayName("Given two yard measurements of 1.0, when compared, then should be equal")
    public void testEquality_YardToYard_SameValue() {
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength yard2 = new QuantityLength(1.0, LengthUnit.YARD);
        assertEquals(yard1, yard2, "1.0 yard should be equal to 1.0 yard");
    }

    @Test
    @DisplayName("Given two yard measurements of 1.0 and 2.0, when compared, then should not be equal")
    public void testEquality_YardToYard_DifferentValue() {
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength yard2 = new QuantityLength(2.0, LengthUnit.YARD);
        assertNotEquals(yard1, yard2, "1.0 yard should not be equal to 2.0 yard");
    }

    @Test
    @DisplayName("Given equivalent yard and feet, when compared, then should be equal")
    public void testEquality_YardToFeet_EquivalentValue() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        assertEquals(yard, feet, "1.0 yard should be equal to 3.0 feet");
    }

    @Test
    @DisplayName("Given equivalent yard and inches, when compared, then should be equal")
    public void testEquality_YardToInches_EquivalentValue() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength inches = new QuantityLength(36.0, LengthUnit.INCH);
        assertEquals(yard, inches, "1.0 yard should be equal to 36.0 inches");
    }

    @Test
    @DisplayName("Given non-equivalent yard and feet, when compared, then should not be equal")
    public void testEquality_YardToFeet_NonEquivalentValue() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet = new QuantityLength(2.0, LengthUnit.FEET);
        assertNotEquals(yard, feet, "1.0 yard should not be equal to 2.0 feet");
    }

    @Test
    @DisplayName("Given two centimeter measurements of 2.0, when compared, then should be equal")
    public void testEquality_CentimeterToCentimeter_SameValue() {
        QuantityLength cm1 = new QuantityLength(2.0, LengthUnit.CENTIMETER);
        QuantityLength cm2 = new QuantityLength(2.0, LengthUnit.CENTIMETER);
        assertEquals(cm1, cm2, "2.0 cm should be equal to 2.0 cm");
    }

    @Test
    @DisplayName("Given equivalent centimeter and inches, when compared, then should be equal")
    public void testEquality_CentimeterToInches_EquivalentValue() {
        QuantityLength cm = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength inches = new QuantityLength(0.393701, LengthUnit.INCH);
        assertEquals(cm, inches, "1.0 cm should be equal to 0.393701 inch");
    }

    @Test
    @DisplayName("Given non-equivalent centimeter and feet, when compared, then should not be equal")
    public void testEquality_CentimeterToFeet_NonEquivalentValue() {
        QuantityLength cm = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        assertNotEquals(cm, feet, "1.0 cm should not be equal to 1.0 feet");
    }

    @Test
    @DisplayName("Given yard, feet, and inches equivalence, then should be transitive")
    public void testEquality_MultiUnit_TransitiveProperty() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(36.0, LengthUnit.INCH);
        assertEquals(yard, feet, "1.0 yard should equal 3.0 feet");
        assertEquals(feet, inches, "3.0 feet should equal 36.0 inches");
        assertEquals(yard, inches, "1.0 yard should equal 36.0 inches");
    }

    @Test
    @DisplayName("Given a null unit, when constructing, then should throw")
    public void testEquality_NullUnit() {
        assertThrows(NullPointerException.class, () -> new QuantityLength(1.0, null));
    }

    @Test
    @DisplayName("Given a NaN value, when constructing, then should throw")
    public void testEquality_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    // UC5: CONVERSION TEST CASES
    @Test
    @DisplayName("Given 1.0 feet, when converted to inches, then should return 12.0")
    public void testConversion_FeetToInches() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(12.0, result, EPSILON, "1.0 ft should convert to 12.0 inches");
    }

    @Test
    @DisplayName("Given 24.0 inches, when converted to feet, then should return 2.0")
    public void testConversion_InchesToFeet() {
        double result = QuantityMeasurementApp.convert(24.0, LengthUnit.INCH, LengthUnit.FEET);
        assertEquals(2.0, result, EPSILON, "24.0 inches should convert to 2.0 ft");
    }

    @Test
    @DisplayName("Given 3.0 yards, when converted to feet, then should return 9.0")
    public void testConversion_YardsToFeet() {
        double result = QuantityMeasurementApp.convert(3.0, LengthUnit.YARD, LengthUnit.FEET);
        assertEquals(9.0, result, EPSILON, "3.0 yards should convert to 9.0 ft");
    }

    @Test
    @DisplayName("Given 6.0 feet, when converted to yards, then should return 2.0")
    public void testConversion_FeetToYards() {
        double result = QuantityMeasurementApp.convert(6.0, LengthUnit.FEET, LengthUnit.YARD);
        assertEquals(2.0, result, EPSILON, "6.0 ft should convert to 2.0 yards");
    }

    @Test
    @DisplayName("Given 1.0 yard, when converted to inches, then should return 36.0")
    public void testConversion_YardsToInches() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.YARD, LengthUnit.INCH);
        assertEquals(36.0, result, EPSILON, "1.0 yard should convert to 36.0 inches");
    }

    @Test
    @DisplayName("Given 72.0 inches, when converted to yards, then should return 2.0")
    public void testConversion_InchesToYards() {
        double result = QuantityMeasurementApp.convert(72.0, LengthUnit.INCH, LengthUnit.YARD);
        assertEquals(2.0, result, EPSILON, "72.0 inches should convert to 2.0 yards");
    }

    @Test
    @DisplayName("Given 2.54 centimeters, when converted to inches, then should return ~1.0")
    public void testConversion_CentimetersToInches() {
        double result = QuantityMeasurementApp.convert(2.54, LengthUnit.CENTIMETER, LengthUnit.INCH);
        assertEquals(1.0, result, EPSILON, "2.54 cm should convert to ~1.0 inches");
    }

    @Test
    @DisplayName("Given 1.0 centimeter, when converted to inches, then should return ~0.393701")
    public void testConversion_CentimeterToInch() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);
        assertEquals(0.393701, result, EPSILON, "1.0 cm should convert to ~0.393701 inches");
    }

    @Test
    @DisplayName("Given 1.0 feet, when converted to centimeters, then should return ~30.48")
    public void testConversion_FeetToCentimeters() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.CENTIMETER);
        // Using a slightly larger epsilon for centimeter conversions due to floating-point precision
        assertEquals(30.48, result, 1e-4, "1.0 ft should convert to ~30.48 cm");
    }

    @Test
    @DisplayName("Given 0.0 feet, when converted to inches, then should return 0.0")
    public void testConversion_ZeroValue() {
        double result = QuantityMeasurementApp.convert(0.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(0.0, result, EPSILON, "0.0 ft should convert to 0.0 inches");
    }

    @Test
    @DisplayName("Given 0.0 yards, when converted to feet, then should return 0.0")
    public void testConversion_ZeroYards() {
        double result = QuantityMeasurementApp.convert(0.0, LengthUnit.YARD, LengthUnit.FEET);
        assertEquals(0.0, result, EPSILON, "0.0 yards should convert to 0.0 feet");
    }

    @Test
    @DisplayName("Given -1.0 feet, when converted to inches, then should return -12.0")
    public void testConversion_NegativeValue() {
        double result = QuantityMeasurementApp.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(-12.0, result, EPSILON, "-1.0 ft should convert to -12.0 inches");
    }

    @Test
    @DisplayName("Given -3.0 yards, when converted to feet, then should return -9.0")
    public void testConversion_NegativeYards() {
        double result = QuantityMeasurementApp.convert(-3.0, LengthUnit.YARD, LengthUnit.FEET);
        assertEquals(-9.0, result, EPSILON, "-3.0 yards should convert to -9.0 feet");
    }

    @Test
    @DisplayName("Given 5.0 feet, when converted to feet, then should return 5.0")
    public void testConversion_SameUnit() {
        double result = QuantityMeasurementApp.convert(5.0, LengthUnit.FEET, LengthUnit.FEET);
        assertEquals(5.0, result, EPSILON, "5.0 ft should remain 5.0 ft");
    }

    @Test
    @DisplayName("Given 1.0 inch, when converted to inch, then should return 1.0")
    public void testConversion_SameUnitInch() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.INCH, LengthUnit.INCH);
        assertEquals(1.0, result, EPSILON, "1.0 inch should remain 1.0 inch");
    }

    @Test
    @DisplayName("Given value in feet, when converted to inches and back, then should preserve value")
    public void testConversion_RoundTrip_FeetInches() {
        double original = 1.0;
        double feet2Inches = QuantityMeasurementApp.convert(original, LengthUnit.FEET, LengthUnit.INCH);
        double back2Feet = QuantityMeasurementApp.convert(feet2Inches, LengthUnit.INCH, LengthUnit.FEET);
        assertEquals(original, back2Feet, EPSILON, "Round-trip conversion should preserve value");
    }

    @Test
    @DisplayName("Given value in yards, when converted to feet and back, then should preserve value")
    public void testConversion_RoundTrip_YardsFeet() {
        double original = 3.0;
        double yards2Feet = QuantityMeasurementApp.convert(original, LengthUnit.YARD, LengthUnit.FEET);
        double back2Yards = QuantityMeasurementApp.convert(yards2Feet, LengthUnit.FEET, LengthUnit.YARD);
        assertEquals(original, back2Yards, EPSILON, "Round-trip conversion should preserve value");
    }

    @Test
    @DisplayName("Given value in centimeters, when converted to inches and back, then should preserve value")
    public void testConversion_RoundTrip_CentimetersInches() {
        double original = 10.0;
        double cm2Inches = QuantityMeasurementApp.convert(original, LengthUnit.CENTIMETER, LengthUnit.INCH);
        double back2Cm = QuantityMeasurementApp.convert(cm2Inches, LengthUnit.INCH, LengthUnit.CENTIMETER);
        assertEquals(original, back2Cm, EPSILON, "Round-trip conversion should preserve value");
    }

    @Test
    @DisplayName("Given value in feet, when converted through multiple units, then should preserve value")
    public void testConversion_MultipleSequential() {
        double original = 1.0;
        double feet2Inches = QuantityMeasurementApp.convert(original, LengthUnit.FEET, LengthUnit.INCH);
        double inches2Ft = QuantityMeasurementApp.convert(feet2Inches, LengthUnit.INCH, LengthUnit.FEET);
        double feet2Yard = QuantityMeasurementApp.convert(inches2Ft, LengthUnit.FEET, LengthUnit.YARD);
        double yard2Inch = QuantityMeasurementApp.convert(feet2Yard, LengthUnit.YARD, LengthUnit.INCH);
        double inch2Feet = QuantityMeasurementApp.convert(yard2Inch, LengthUnit.INCH, LengthUnit.FEET);
        assertEquals(original, inch2Feet, EPSILON, "Multiple conversion should preserve value");
    }

    @Test
    @DisplayName("Given large value in feet, when converted to inches, then should handle precision")
    public void testConversion_LargeValue() {
        double result = QuantityMeasurementApp.convert(1000000.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(12000000.0, result, EPSILON, "Large value conversion should maintain precision");
    }

    @Test
    @DisplayName("Given small value in feet, when converted to inches, then should handle precision")
    public void testConversion_SmallValue() {
        double result = QuantityMeasurementApp.convert(0.001, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(0.012, result, EPSILON, "Small value conversion should maintain precision");
    }

    @Test
    @DisplayName("Given decimal value in feet, when converted to inches, then should maintain precision")
    public void testConversion_DecimalValue() {
        double result = QuantityMeasurementApp.convert(1.5, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(18.0, result, EPSILON, "1.5 ft should convert to 18.0 inches");
    }

    @Test
    @DisplayName("Given NaN value, when converting, then should throw IllegalArgumentException")
    public void testConversion_InvalidValue_NaN() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH),
                "NaN value should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Given Infinity value, when converting, then should throw IllegalArgumentException")
    public void testConversion_InvalidValue_Infinity() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH),
                "Positive Infinity should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Given negative Infinity value, when converting, then should throw IllegalArgumentException")
    public void testConversion_InvalidValue_NegativeInfinity() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(Double.NEGATIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH),
                "Negative Infinity should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Given null source unit, when converting, then should throw IllegalArgumentException")
    public void testConversion_InvalidUnit_Null_Source() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(1.0, null, LengthUnit.INCH),
                "Null source unit should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Given null target unit, when converting, then should throw IllegalArgumentException")
    public void testConversion_InvalidUnit_Null_Target() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, null),
                "Null target unit should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Given QuantityLength in feet, when using convertTo method, then should return correct value")
    public void testConversion_InstanceMethod_FeetToInches() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength result = feet.convertTo(LengthUnit.INCH);
        assertEquals(12.0, result.getValue(), EPSILON, "1.0 ft should convert to 12.0 inches");
        assertEquals(LengthUnit.INCH, result.getUnit(), "Converted unit should be INCH");
    }

    @Test
    @DisplayName("Given QuantityLength in yards, when using convertTo method, then should return correct value")
    public void testConversion_InstanceMethod_YardsToFeet() {
        QuantityLength yards = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength result = yards.convertTo(LengthUnit.FEET);
        assertEquals(6.0, result.getValue(), EPSILON, "2.0 yards should convert to 6.0 feet");
    }

    @Test
    @DisplayName("Given QuantityLength with null target unit, when using convertTo, then should throw")
    public void testConversion_InstanceMethod_NullUnit() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        assertThrows(NullPointerException.class, () -> feet.convertTo(null),
                "Null target unit should throw NullPointerException");
    }

    @Test
    @DisplayName("Given QuantityLength instance, when toString called, then should return formatted string")
    public void testToString() {
        QuantityLength length = new QuantityLength(1.5, LengthUnit.FEET);
        String result = length.toString();
        assertEquals("1.50 FEET", result, "toString should return formatted string");
    }

    @Test
    @DisplayName("Given QuantityLength instances, when converted and compared, then should be equal")
    public void testValueObjectSemantics_ConvertedEquality() {
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength yards = new QuantityLength(1.0, LengthUnit.YARD);
        assertEquals(feet, yards, "3.0 feet should equal 1.0 yard after conversion");
    }

    @Test
    @DisplayName("Given QuantityLength instance, when convertTo is called, then should return new instance")
    public void testImmutability_ConvertTo() {
        QuantityLength original = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength converted = original.convertTo(LengthUnit.INCH);
        // Note: converted equals original semantically (1 foot = 12 inches)
        // but they are different instances with different units
        assertNotEquals(original.getUnit(), converted.getUnit(), "Converted unit should differ from original");
        assertEquals(1.0, original.getValue(), EPSILON, "Original value should not change");
        assertEquals(LengthUnit.FEET, original.getUnit(), "Original unit should not change");
    }

    @Test
    @DisplayName("Given two feet values using static method, when compared, then should return correct equality")
    public void testStaticMethod_FeetEquality() {
        boolean result1 = QuantityMeasurementApp.checkFeetEquality(1.0, 1.0);
        boolean result2 = QuantityMeasurementApp.checkFeetEquality(1.0, 2.0);
        
        assertEquals(true, result1, "1.0 ft should be equal to 1.0 ft");
        assertEquals(false, result2, "1.0 ft should not be equal to 2.0 ft");
    }

    @Test
    @DisplayName("Given two inches values using static method, when compared, then should return correct equality")
    public void testStaticMethod_InchesEquality() {
        boolean result1 = QuantityMeasurementApp.checkInchesEquality(1.0, 1.0);
        boolean result2 = QuantityMeasurementApp.checkInchesEquality(1.0, 2.0);
        
        assertEquals(true, result1, "1.0 inch should be equal to 1.0 inch");
        assertEquals(false, result2, "1.0 inch should not be equal to 2.0 inch");
    }

    // UC6: ADDITION TEST CASES
    @Test
    @DisplayName("Given 1.0 feet and 2.0 feet, when added, then should return 3.0 feet")
    public void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(2.0, LengthUnit.FEET);
        QuantityLength result = feet1.add(feet2);
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Given 6.0 inches and 6.0 inches, when added, then should return 12.0 inches")
    public void testAddition_SameUnit_InchesPlusInches() {
        QuantityLength inches1 = new QuantityLength(6.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(6.0, LengthUnit.INCH);
        QuantityLength result = inches1.add(inches2);
        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    @DisplayName("Given 1.0 yards and 1.0 yards, when added, then should return 2.0 yards")
    public void testAddition_SameUnit_YardsPlusYards() {
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength yard2 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength result = yard1.add(yard2);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    @DisplayName("Given 1.0 feet and 12.0 inches, when added in feet, then should return 2.0 feet")
    public void testAddition_CrossUnit_FeetPlusInches() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength result = feet1.add(inches1, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Given 12.0 inches and 1.0 feet, when added in inches, then should return 24.0 inches")
    public void testAddition_CrossUnit_InchesPlusFeet() {
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength result = inches1.add(feet1, LengthUnit.INCH);
        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    @DisplayName("Given 1.0 yards and 3.0 feet, when added in yards, then should return 2.0 yards")
    public void testAddition_CrossUnit_YardsPlusFeet() {
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet1 = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength result = yard1.add(feet1, LengthUnit.YARD);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    @DisplayName("Given 36.0 inches and 1.0 yards, when added in inches, then should return 72.0 inches")
    public void testAddition_CrossUnit_InchesPlusYards() {
        QuantityLength inches1 = new QuantityLength(36.0, LengthUnit.INCH);
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength result = inches1.add(yard1, LengthUnit.INCH);
        assertEquals(72.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    @DisplayName("Given 2.54 centimeters and 1.0 inches, when added in centimeters, then should return ~5.08 centimeters")
    public void testAddition_CrossUnit_CentimetersPlusInches() {
        QuantityLength cm1 = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        QuantityLength inches1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength result = cm1.add(inches1, LengthUnit.CENTIMETER);
        assertEquals(5.08, result.getValue(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, result.getUnit());
    }

    @Test
    @DisplayName("Given 1.0 feet and 12.0 inches, when added using default unit, then should result in feet")
    public void testAddition_DefaultUnit_FeetAsFirstOperand() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength result = feet1.add(inches1);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Given 12.0 inches and 1.0 feet, when added using default unit, then should result in inches")
    public void testAddition_DefaultUnit_InchesAsFirstOperand() {
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength result = inches1.add(feet1);
        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    @DisplayName("Given 5.0 feet and 0.0 inches, when added, then should return 5.0 feet")
    public void testAddition_WithZero_ZeroInches() {
        QuantityLength feet1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(0.0, LengthUnit.INCH);
        QuantityLength result = feet1.add(inches1, LengthUnit.FEET);
        assertEquals(5.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Given 0.0 feet and 5.0 feet, when added, then should return 5.0 feet")
    public void testAddition_WithZero_ZeroFeet() {
        QuantityLength feet1 = new QuantityLength(0.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength result = feet1.add(feet2);
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("Given 5.0 feet and -2.0 feet, when added, then should return 3.0 feet")
    public void testAddition_NegativeValues() {
        QuantityLength feet1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(-2.0, LengthUnit.FEET);
        QuantityLength result = feet1.add(feet2);
        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("Given -1.0 feet and -2.0 feet, when added, then should return -3.0 feet")
    public void testAddition_BothNegativeValues() {
        QuantityLength feet1 = new QuantityLength(-1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(-2.0, LengthUnit.FEET);
        QuantityLength result = feet1.add(feet2);
        assertEquals(-3.0, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("Given 1.0 feet and 12.0 inches, when added, then should be commutative")
    public void testAddition_Commutativity() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        
        double result1 = QuantityMeasurementApp.add(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH, LengthUnit.FEET);
        double result2 = QuantityMeasurementApp.add(12.0, LengthUnit.INCH, 1.0, LengthUnit.FEET, LengthUnit.FEET);
        
        assertEquals(result1, result2, EPSILON);
    }

    @Test
    @DisplayName("Given 0.5 feet and 1.5 feet, when added, then should return 2.0 feet")
    public void testAddition_DecimalValues() {
        QuantityLength feet1 = new QuantityLength(0.5, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.5, LengthUnit.FEET);
        QuantityLength result = feet1.add(feet2);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("Given 1e6 feet and 1e6 feet, when added, then should handle large values")
    public void testAddition_LargeValues() {
        QuantityLength feet1 = new QuantityLength(1e6, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1e6, LengthUnit.FEET);
        QuantityLength result = feet1.add(feet2);
        assertEquals(2e6, result.getValue(), EPSILON * 1e6);
    }

    @Test
    @DisplayName("Given 0.001 feet and 0.002 feet, when added, then should handle small values")
    public void testAddition_SmallValues() {
        QuantityLength feet1 = new QuantityLength(0.001, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(0.002, LengthUnit.FEET);
        QuantityLength result = feet1.add(feet2);
        assertEquals(0.003, result.getValue(), EPSILON);
    }

    @Test
    @DisplayName("Given null as second operand, when adding, then should throw exception")
    public void testAddition_NullSecondOperand() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> feet1.add(null));
    }

    @Test
    @DisplayName("Given null target unit, when adding with specified unit, then should throw exception")
    public void testAddition_NullTargetUnit() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(2.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> feet1.add(feet2, null));
    }

    @Test
    @DisplayName("Given NaN value in static add method, when adding, then should throw exception")
    public void testAddition_NaNValue() {
        assertThrows(IllegalArgumentException.class, () -> 
            QuantityMeasurementApp.add(Double.NaN, LengthUnit.FEET, 1.0, LengthUnit.FEET, LengthUnit.FEET));
    }

    @Test
    @DisplayName("Given Infinity value in static add method, when adding, then should throw exception")
    public void testAddition_InfinityValue() {
        assertThrows(IllegalArgumentException.class, () -> 
            QuantityMeasurementApp.add(Double.POSITIVE_INFINITY, LengthUnit.FEET, 1.0, LengthUnit.FEET, LengthUnit.FEET));
    }

    @Test
    @DisplayName("Given null unit in static add method, when adding, then should throw exception")
    public void testAddition_NullSourceUnit() {
        assertThrows(IllegalArgumentException.class, () -> 
            QuantityMeasurementApp.add(1.0, null, 1.0, LengthUnit.FEET, LengthUnit.FEET));
    }

    @Test
    @DisplayName("Given null resultUnit in static add method, when adding, then should throw exception")
    public void testAddition_NullResultUnit() {
        assertThrows(IllegalArgumentException.class, () -> 
            QuantityMeasurementApp.add(1.0, LengthUnit.FEET, 1.0, LengthUnit.FEET, null));
    }

    @Test
    @DisplayName("Given 1.0 foot and 12.0 inches addition result converted to yards, then should preserve mathematical accuracy")
    public void testAddition_RoundTripConversion() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength result = feet1.add(inches1, LengthUnit.FEET);
        
        QuantityLength convertedToYards = result.convertTo(LengthUnit.YARD);
        assertEquals(2.0/3.0, convertedToYards.getValue(), EPSILON);
    }

    @Test
    @DisplayName("Given measurements added then converted, when reconverted, then should match original sum")
    public void testAddition_ConversionConsistency() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(24.0, LengthUnit.INCH);
        
        QuantityLength resultInFeet = feet1.add(inches1, LengthUnit.FEET);
        QuantityLength resultInInches = feet1.add(inches1, LengthUnit.INCH);
        
        assertEquals(resultInFeet.getValue(), resultInInches.convertTo(LengthUnit.FEET).getValue(), EPSILON);
    }

    @Test
    @DisplayName("Given 1.0 yards and 12.0 inches, when added using static method, then should return 4.0 yards")
    public void testAddition_StaticMethod_WithResultUnit() {
        double result = QuantityMeasurementApp.add(1.0, LengthUnit.YARD, 12.0, LengthUnit.INCH, LengthUnit.YARD);
        assertEquals(4.0/3.0, result, EPSILON);
    }

    @Test
    @DisplayName("Given QuantityLength objects, when added using static method, then should return new QuantityLength")
    public void testAddition_StaticMethod_WithQuantityLengthObjects() {
        QuantityLength feet1 = new QuantityLength(2.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength result = QuantityMeasurementApp.add(feet1, feet2);
        
        assertEquals(5.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Given QuantityLength objects with different units, when added using static method with result unit, then should return result in specified unit")
    public void testAddition_StaticMethod_WithDifferentUnits() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength result = QuantityMeasurementApp.add(feet1, inches1, LengthUnit.FEET);
        
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Given null Length1 in static method, when adding, then should throw exception")
    public void testAddition_StaticMethod_NullLength1() {
        QuantityLength feet2 = new QuantityLength(2.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> 
            QuantityMeasurementApp.add(null, feet2, LengthUnit.FEET));
    }

    @Test
    @DisplayName("Given null Length2 in static method, when adding, then should throw exception")
    public void testAddition_StaticMethod_NullLength2() {
        QuantityLength feet1 = new QuantityLength(2.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> 
            QuantityMeasurementApp.add(feet1, null, LengthUnit.FEET));
    }

    @Test
    @DisplayName("Given addition result, when converted to different units, then should maintain equality")
    public void testAddition_EqualityAfterConversion() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        
        QuantityLength resultInFeet = feet1.add(inches1, LengthUnit.FEET);
        QuantityLength resultInInches = feet1.add(inches1, LengthUnit.INCH);
        
        assertEquals(resultInFeet, resultInInches);
    }

    @Test
    @DisplayName("Given addition of yards and feet, when result expressed as inches, then should equal direct conversion")
    public void testAddition_UnitsConsistency() {
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet1 = new QuantityLength(3.0, LengthUnit.FEET);
        
        QuantityLength resultInYards = yard1.add(feet1, LengthUnit.YARD);
        QuantityLength convertedToInches = resultInYards.convertTo(LengthUnit.INCH);
        
        assertEquals(72.0, convertedToInches.getValue(), EPSILON);
    }

    @Test
    @DisplayName("Given addition of centimeters and inches, when result converted to feet, then should be accurate")
    public void testAddition_CentimeterAndInch() {
        QuantityLength cm1 = new QuantityLength(30.48, LengthUnit.CENTIMETER);
        QuantityLength inches1 = new QuantityLength(0.0, LengthUnit.INCH);
        
        QuantityLength result = cm1.add(inches1, LengthUnit.CENTIMETER);
        QuantityLength convertedToFeet = result.convertTo(LengthUnit.FEET);
        
        assertEquals(1.0, convertedToFeet.getValue(), EPSILON);
    }
}