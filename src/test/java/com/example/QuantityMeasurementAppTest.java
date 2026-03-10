package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
    @DisplayName("Given a Feet object and an Inches object with different values, when compared, then should not be equal")
    public void testEquality_DifferentUnits() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(1.0, LengthUnit.INCH);
        assertNotEquals(feet, inches, "1.0 ft should not equal 1.0 inch (different values)");
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
    @DisplayName("Given NaN value, when converting, then should throw IllegalArgumentException")
    public void testConversion_InvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH),
                "NaN value should throw IllegalArgumentException");
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



    // UC6: ADDITION TEST CASES
    @Test
    @DisplayName("Given 1.0 feet and 2.0 feet, when added, then should return 3.0 feet")
    public void testAddition_SameUnit() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(2.0, LengthUnit.FEET);
        QuantityLength result = feet1.add(feet2);
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Given 1.0 feet and 12.0 inches, when added in feet, then should return 2.0 feet")
    public void testAddition_CrossUnit() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength result = feet1.add(inches1, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Given 1.0 feet and 12.0 inches, when added using default unit, then should result in feet")
    public void testAddition_DefaultUnit() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength result = feet1.add(inches1);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }



    @Test
    @DisplayName("Given null operand or unit, when adding, then should throw exception")
    public void testAddition_Validation() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(2.0, LengthUnit.FEET);
        
        assertThrows(IllegalArgumentException.class, () -> feet1.add(null));
        assertThrows(IllegalArgumentException.class, () -> feet1.add(feet2, null));
        assertThrows(IllegalArgumentException.class, () -> 
            QuantityMeasurementApp.add(Double.NaN, LengthUnit.FEET, 1.0, LengthUnit.FEET, LengthUnit.FEET));
    }

    @Test
    @DisplayName("Given 2.0 yards and 3.0 feet with explicit target unit YARDS (same as first operand), when added, then should return 3.0 yards")
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        QuantityLength yard1 = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength feet1 = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength result = yard1.add(feet1, LengthUnit.YARD);
        
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    @DisplayName("Given 2.0 yards and 3.0 feet with explicit target unit FEET (same as second operand), when added, then should return 9.0 feet")
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        QuantityLength yard1 = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength feet1 = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength result = yard1.add(feet1, LengthUnit.FEET);
        
        assertEquals(9.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Given 1.0 feet and 12.0 inches with explicit target unit YARDS, when added in any order, then should be commutative")
    public void testAddition_ExplicitTargetUnit_Commutativity() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        
        QuantityLength result1 = feet1.add(inches1, LengthUnit.YARD);
        QuantityLength result2 = inches1.add(feet1, LengthUnit.YARD);
        
        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
        assertEquals(result1.getUnit(), result2.getUnit());
        assertEquals(result1, result2);
    }

    @Test
    @DisplayName("Given 5.0 feet and 0.0 inches with explicit target unit YARDS, when added, then should return ~1.667 yards")
    public void testAddition_ExplicitTargetUnit_WithZero() {
        QuantityLength feet1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(0.0, LengthUnit.INCH);
        QuantityLength result = feet1.add(inches1, LengthUnit.YARD);
        
        assertEquals(5.0 / 3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    @DisplayName("Given 5.0 feet and -2.0 feet with explicit target unit INCHES, when added, then should return 36.0 inches")
    public void testAddition_ExplicitTargetUnit_NegativeValues() {
        QuantityLength feet1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(-2.0, LengthUnit.FEET);
        QuantityLength result = feet1.add(feet2, LengthUnit.INCH);
        
        assertEquals(36.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    @DisplayName("Given null target unit, when adding, then should throw IllegalArgumentException")
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        
        assertThrows(IllegalArgumentException.class, () -> feet1.add(inches1, null));
    }

    @Test
    @DisplayName("Given 1000.0 feet and 500.0 feet with explicit target unit INCHES, when added, then should return 18000.0 inches")
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        QuantityLength feet1 = new QuantityLength(1000.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(500.0, LengthUnit.FEET);
        QuantityLength result = feet1.add(feet2, LengthUnit.INCH);
        
        assertEquals(18000.0, result.getValue(), EPSILON * 18000.0);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    @DisplayName("Given 12.0 inches and 12.0 inches with explicit target unit YARDS, when added, then should return ~0.667 yards")
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength result = inches1.add(inches2, LengthUnit.YARD);
        
        assertEquals(2.0 / 3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    @DisplayName("Given various unit combinations with multiple target units, when added, then should verify mathematical correctness across all combinations")
    public void testAddition_ExplicitTargetUnit_AllUnitCombinations() {
        // Test FEET + INCHES with multiple target units
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        
        QuantityLength resultFeet = feet1.add(inches1, LengthUnit.FEET);
        QuantityLength resultInches = feet1.add(inches1, LengthUnit.INCH);
        QuantityLength resultYards = feet1.add(inches1, LengthUnit.YARD);
        
        assertEquals(resultFeet, resultInches);
        assertEquals(resultFeet, resultYards);
        
        // Test YARDS + FEET with multiple target units
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet2 = new QuantityLength(3.0, LengthUnit.FEET);
        
        QuantityLength resultYards2 = yard1.add(feet2, LengthUnit.YARD);
        QuantityLength resultFeet2 = yard1.add(feet2, LengthUnit.FEET);
        
        assertEquals(resultYards2, resultFeet2);
    }

    @Test
    @DisplayName("Given multiple additions with explicit target units, when verified using epsilon-based comparison, then should maintain floating-point precision")
    public void testAddition_ExplicitTargetUnit_PrecisionTolerance() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches1 = new QuantityLength(12.0, LengthUnit.INCH);
        
        QuantityLength resultYards = feet1.add(inches1, LengthUnit.YARD);
        assertEquals(2.0 / 3.0, resultYards.getValue(), EPSILON);
        
        QuantityLength cm1 = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        QuantityLength inches2 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength resultCm = cm1.add(inches2, LengthUnit.CENTIMETER);
        // Using larger tolerance for centimeter conversions due to conversion factor precision
        assertEquals(5.08, resultCm.getValue(), 0.01);
        
        // Test precision across scale conversions
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet2 = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength resultInches2 = yard1.add(feet2, LengthUnit.INCH);
        assertEquals(72.0, resultInches2.getValue(), EPSILON);
    }

    // UC8: REFACTORING - STANDALONE UNIT WITH CONVERSION RESPONSIBILITY
    @Test
    @DisplayName("UC8: Given LengthUnit.FEET, when checking conversion factor, then should return 1.0")
    public void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.toFeetFactor(), EPSILON);
    }

    @Test
    @DisplayName("UC8: Given LengthUnit.INCH, when checking conversion factor, then should return ~0.0833")
    public void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCH.toFeetFactor(), EPSILON);
    }

    @Test
    @DisplayName("UC8: Given LengthUnit.YARD, when checking conversion factor, then should return 3.0")
    public void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARD.toFeetFactor(), EPSILON);
    }

    @Test
    @DisplayName("UC8: Given LengthUnit.CENTIMETER, when checking conversion factor, then should return ~0.0328")
    public void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(0.393701 / 12.0, LengthUnit.CENTIMETER.toFeetFactor(), EPSILON);
    }

    @Test
    @DisplayName("UC8: Given 5.0 feet, when converting to base unit, then should return 5.0")
    public void testConvertToBaseUnit_FeetToFeet() {
        double result = LengthUnit.FEET.convertToBaseUnit(5.0);
        assertEquals(5.0, result, EPSILON);
    }

    @Test
    @DisplayName("UC8: Given 12.0 inches, when converting to base unit, then should return 1.0 feet")
    public void testConvertToBaseUnit_InchesToFeet() {
        double result = LengthUnit.INCH.convertToBaseUnit(12.0);
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    @DisplayName("UC8: Given 1.0 yard, when converting to base unit, then should return 3.0 feet")
    public void testConvertToBaseUnit_YardsToFeet() {
        double result = LengthUnit.YARD.convertToBaseUnit(1.0);
        assertEquals(3.0, result, EPSILON);
    }

    @Test
    @DisplayName("UC8: Given 30.48 centimeters, when converting to base unit, then should return ~1.0 feet")
    public void testConvertToBaseUnit_CentimetersToFeet() {
        double result = LengthUnit.CENTIMETER.convertToBaseUnit(30.48);
        assertEquals(1.0, result, 0.01);
    }

    @Test
    @DisplayName("UC8: Given 2.0 feet in base unit, when converting to feet, then should return 2.0")
    public void testConvertFromBaseUnit_FeetToFeet() {
        double result = LengthUnit.FEET.convertFromBaseUnit(2.0);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    @DisplayName("UC8: Given 1.0 feet in base unit, when converting to inches, then should return 12.0")
    public void testConvertFromBaseUnit_FeetToInches() {
        double result = LengthUnit.INCH.convertFromBaseUnit(1.0);
        assertEquals(12.0, result, EPSILON);
    }

    @Test
    @DisplayName("UC8: Given 3.0 feet in base unit, when converting to yards, then should return 1.0")
    public void testConvertFromBaseUnit_FeetToYards() {
        double result = LengthUnit.YARD.convertFromBaseUnit(3.0);
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    @DisplayName("UC8: Given 1.0 feet in base unit, when converting to centimeters, then should return ~30.48")
    public void testConvertFromBaseUnit_FeetToCentimeters() {
        double result = LengthUnit.CENTIMETER.convertFromBaseUnit(1.0);
        assertEquals(30.48, result, 0.01);
    }

    @Test
    @DisplayName("UC8: Given refactored design, when comparing 1.0 feet and 12.0 inches, then should be equal")
    public void testQuantityLengthRefactored_Equality() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCH);
        assertEquals(feet, inches);
    }

    @Test
    @DisplayName("UC8: Given refactored design, when converting 1.0 feet to inches, then should return 12.0 inches")
    public void testQuantityLengthRefactored_ConvertTo() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength result = feet.convertTo(LengthUnit.INCH);
        
        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    @DisplayName("UC8: Given refactored design, when adding 1.0 feet and 12.0 inches in feet, then should return 2.0 feet")
    public void testQuantityLengthRefactored_Add() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength result = feet.add(inches, LengthUnit.FEET);
        
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("UC8: Given refactored design, when adding 1.0 feet and 12.0 inches in yards, then should return ~0.667 yards")
    public void testQuantityLengthRefactored_AddWithTargetUnit() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength result = feet.add(inches, LengthUnit.YARD);
        
        assertEquals(2.0 / 3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    @DisplayName("UC8: Given null unit in refactored design, when creating QuantityLength, then should throw exception")
    public void testQuantityLengthRefactored_NullUnit() {
        assertThrows(NullPointerException.class, () -> new QuantityLength(1.0, null));
    }

    @Test
    @DisplayName("UC8: Given NaN value in refactored design, when creating QuantityLength, then should throw exception")
    public void testQuantityLengthRefactored_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    @Test
    @DisplayName("UC8: Given refactored design, when performing round-trip conversion, then should preserve value")
    public void testRoundTripConversion_RefactoredDesign() {
        QuantityLength original = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength toInches = original.convertTo(LengthUnit.INCH);
        QuantityLength backToFeet = toInches.convertTo(LengthUnit.FEET);
        
        assertEquals(original.getValue(), backToFeet.getValue(), EPSILON);
        assertEquals(original, backToFeet);
    }

    @Test
    @DisplayName("UC8: Given refactored design, when testing unit delegation, then conversion should work correctly")
    public void testUnitDelegation_ConversionLogic() {
        // Test that QuantityLength delegates to unit conversion methods
        QuantityLength yards = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength feet = yards.convertTo(LengthUnit.FEET);
        
        assertEquals(6.0, feet.getValue(), EPSILON);
        
        QuantityLength inches = feet.convertTo(LengthUnit.INCH);
        assertEquals(72.0, inches.getValue(), EPSILON);
    }

    @Test
    @DisplayName("UC8: Given refactored design, when testing multiple conversions, then should maintain precision")
    public void testPrecisionMaintenance_RefactoredDesign() {
        QuantityLength cm = new QuantityLength(100.0, LengthUnit.CENTIMETER);
        QuantityLength feet = cm.convertTo(LengthUnit.FEET);
        QuantityLength inches = feet.convertTo(LengthUnit.INCH);
        QuantityLength backToCm = inches.convertTo(LengthUnit.CENTIMETER);
        
        // Within acceptable tolerance for multiple conversions
        assertEquals(100.0, backToCm.getValue(), 0.1);
    }

    @Test
    @DisplayName("UC8: Given refactored design, when testing base unit conversions, then should be accurate")
    public void testBaseUnitConversions_Accuracy() {
        // Test FEET (base unit)
        assertEquals(10.0, LengthUnit.FEET.convertToBaseUnit(10.0), EPSILON);
        assertEquals(10.0, LengthUnit.FEET.convertFromBaseUnit(10.0), EPSILON);
        
        // Test INCH
        assertEquals(2.0, LengthUnit.INCH.convertToBaseUnit(24.0), EPSILON);
        assertEquals(24.0, LengthUnit.INCH.convertFromBaseUnit(2.0), EPSILON);
        
        // Test YARD
        assertEquals(9.0, LengthUnit.YARD.convertToBaseUnit(3.0), EPSILON);
        assertEquals(3.0, LengthUnit.YARD.convertFromBaseUnit(9.0), EPSILON);
    }

    @Test
    @DisplayName("UC8: Given refactored design, when testing architectural scalability, then pattern should be reusable")
    public void testArchitecturalScalability_Pattern() {
        // Verify that LengthUnit is a standalone enum
        assertTrue(LengthUnit.class.isEnum());
        
        // Verify conversion methods exist
        try {
            LengthUnit.FEET.getClass().getMethod("convertToBaseUnit", double.class);
            LengthUnit.FEET.getClass().getMethod("convertFromBaseUnit", double.class);
        } catch (NoSuchMethodException e) {
            fail("Conversion methods should exist in LengthUnit");
        }
    }

    // UC9: WEIGHT MEASUREMENT TEST CASES

    @Test
    @DisplayName("Given 1.0 kg and 1000.0 g, when compared, then should be equal")
    public void testWeightEquality_KilogramToGram() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);
        assertEquals(kg, g, "1.0 kg should equal 1000.0 g");
    }

    @Test
    @DisplayName("Given 1.0 lb and 0.453592 kg, when compared, then should be equal")
    public void testWeightEquality_PoundToKilogram() {
        QuantityWeight lb = new QuantityWeight(1.0, WeightUnit.POUND);
        QuantityWeight kg = new QuantityWeight(0.453592, WeightUnit.KILOGRAM);
        assertEquals(lb, kg, "1.0 lb should equal 0.453592 kg");
    }

    @Test
    @DisplayName("Given 2.0 kg, when converted to grams, then should return 2000.0 g")
    public void testWeightConversion_KilogramToGram() {
        QuantityWeight kg = new QuantityWeight(2.0, WeightUnit.KILOGRAM);
        QuantityWeight g = kg.convertTo(WeightUnit.GRAM);
        assertEquals(new QuantityWeight(2000.0, WeightUnit.GRAM), g, "2.0 kg should convert to 2000.0 g");
    }

    @Test
    @DisplayName("Given 5.0 lb, when converted to kilograms, then should return approximately 2.268 kg")
    public void testWeightConversion_PoundToKilogram() {
        QuantityWeight lb = new QuantityWeight(5.0, WeightUnit.POUND);
        QuantityWeight kg = lb.convertTo(WeightUnit.KILOGRAM);
        assertEquals(2.26796, kg.getValue(), EPSILON, "5.0 lb should convert to approximately 2.26796 kg");
    }

    @Test
    @DisplayName("Given 3.0 kg + 500.0 g, when added with explicit target, then should return 3.5 kg")
    public void testWeightAddition_ExplicitTarget() {
        QuantityWeight kg = new QuantityWeight(3.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(500.0, WeightUnit.GRAM);
        QuantityWeight result = kg.add(g, WeightUnit.KILOGRAM);
        assertEquals(new QuantityWeight(3.5, WeightUnit.KILOGRAM), result, "3.0 kg + 500.0 g should equal 3.5 kg");
    }

    @Test
    @DisplayName("Given 1.5 kg + 250.0 g, when added without explicit target, then should return 1.75 kg")
    public void testWeightAddition_ImplicitTarget() {
        QuantityWeight kg = new QuantityWeight(1.5, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(250.0, WeightUnit.GRAM);
        QuantityWeight result = kg.add(g);
        assertEquals(new QuantityWeight(1.75, WeightUnit.KILOGRAM), result, "1.5 kg + 250.0 g should equal 1.75 kg (implicit target)");
    }

    @Test
    @DisplayName("Given WeightUnit enum, when testing conversion methods, then should convert correctly")
    public void testWeightUnit_ConversionMethods() {
        assertEquals(0.5, WeightUnit.GRAM.convertToBaseUnit(500.0), EPSILON);
        assertEquals(500.0, WeightUnit.GRAM.convertFromBaseUnit(0.5), EPSILON);
        assertEquals(4.53592, WeightUnit.POUND.convertToBaseUnit(10.0), EPSILON);
    }

    @Test
    @DisplayName("Given null weight unit, when creating QuantityWeight, then should throw exception")
    public void testWeightValidation_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityWeight(1.0, null);
        }, "Creating QuantityWeight with null unit should throw exception");
    }

    @Test
    @DisplayName("Given NaN value, when creating QuantityWeight, then should throw exception")
    public void testWeightValidation_NaNValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM);
        }, "Creating QuantityWeight with NaN should throw exception");
    }

    @Test
    @DisplayName("Given infinite value, when creating QuantityWeight, then should throw exception")
    public void testWeightValidation_InfiniteValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityWeight(Double.POSITIVE_INFINITY, WeightUnit.KILOGRAM);
        }, "Creating QuantityWeight with infinite value should throw exception");
    }
}