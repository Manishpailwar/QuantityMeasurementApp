package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class QuantityMeasurementAppTest 
{
    @Test
    @DisplayName("Given two feet measurements of 1.0, when compared, then should be equal")
    public void testEquality_SameValue()
    {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(feet1, feet2, "The measurements should be identical");
    }

    @Test
    @DisplayName("Given two feet measurements of 1.0 and 2.0, when compared, then should not be equal")
    public void testEquality_DifferentValue()
    {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(2.0, LengthUnit.FEET);
        assertNotEquals(feet1, feet2, "1.0 ft should not be equal to 2.0 ft");
    }

    @Test
    @DisplayName("Given a feet measurement, when compared to null, then should not be equal")
    public void testEquality_NullComparison()
    {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertNotEquals(feet1, null, "Feet object should not be equal to null");
    }

    @Test
    @DisplayName("Given a feet measurement, when compared to non-Feet object, then should not be equal")
    public void testEquality_NonNumericInput()
    {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        String nonNumeric = "1.0";
        assertNotEquals(feet1, nonNumeric, "Feet object should not be equal to non-Feet object");
    }

    @Test
    @DisplayName("Given a feet measurement, when compared to itself, then should be equal")
    public void testEquality_SameReference()
    {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(feet1, feet1, "Feet object should be equal to itself (reflexive)");
    }

    @Test
    @DisplayName("Given two equal feet measurements, when compared in any order, then should be symmetric")
    public void testEquality_SymmetricProperty()
    {
        QuantityLength feet1 = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(3.0, LengthUnit.FEET);
        assertEquals(feet1, feet2, "Equality should be symmetric");
        assertEquals(feet2, feet1, "Equality should be symmetric");
    }

    @Test
    @DisplayName("Given three equal feet measurements, when compared transitively, then should be equal")
    public void testEquality_TransitiveProperty()
    {
        QuantityLength feet1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength feet3 = new QuantityLength(5.0, LengthUnit.FEET);
        assertEquals(feet1, feet2, "Equality should be transitive");
        assertEquals(feet2, feet3, "Equality should be transitive");
        assertEquals(feet1, feet3, "Equality should be transitive");
    }

    @Test
    @DisplayName("Given two feet measurements of 0.0, when compared, then should be equal")
    public void testEquality_ZeroValue()
    {
        QuantityLength feet1 = new QuantityLength(0.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(0.0, LengthUnit.FEET);
        assertEquals(feet1, feet2, "0.0 ft should be equal to 0.0 ft");
    }

    @Test
    @DisplayName("Given two feet measurements of -1.0, when compared, then should be equal")
    public void testEquality_NegativeValue()
    {
        QuantityLength feet1 = new QuantityLength(-1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(-1.0, LengthUnit.FEET);
        assertEquals(feet1, feet2, "-1.0 ft should be equal to -1.0 ft");
    }

    @Test
    @DisplayName("Given two feet measurements with decimal values, when compared, then should handle precision")
    public void testEquality_DecimalPrecision()
    {
        QuantityLength feet1 = new QuantityLength(1.5, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.5, LengthUnit.FEET);
        assertEquals(feet1, feet2, "1.5 ft should be equal to 1.5 ft");
    }

    // === INCHES EQUALITY TEST CASES ===

    @Test
    @DisplayName("Given two inches measurements of 1.0, when compared, then should be equal")
    public void testInchesEquality_SameValue()
    {
        QuantityLength inches1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(1.0, LengthUnit.INCH);
        assertEquals(inches1, inches2, "The measurements should be identical");
    }

    @Test
    @DisplayName("Given two inches measurements of 1.0 and 2.0, when compared, then should not be equal")
    public void testInchesEquality_DifferentValue()
    {
        QuantityLength inches1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(2.0, LengthUnit.INCH);
        assertNotEquals(inches1, inches2, "1.0 inch should not be equal to 2.0 inch");
    }

    @Test
    @DisplayName("Given an inches measurement, when compared to null, then should not be equal")
    public void testInchesEquality_NullComparison()
    {
        QuantityLength inches1 = new QuantityLength(1.0, LengthUnit.INCH);
        assertNotEquals(inches1, null, "Inches object should not be equal to null");
    }

    @Test
    @DisplayName("Given an inches measurement, when compared to non-Inches object, then should not be equal")
    public void testInchesEquality_NonNumericInput()
    {
        QuantityLength inches1 = new QuantityLength(1.0, LengthUnit.INCH);
        String nonNumeric = "1.0";
        assertNotEquals(inches1, nonNumeric, "Inches object should not be equal to non-Inches object");
    }

    @Test
    @DisplayName("Given an inches measurement, when compared to itself, then should be equal")
    public void testInchesEquality_SameReference()
    {
        QuantityLength inches1 = new QuantityLength(1.0, LengthUnit.INCH);
        assertEquals(inches1, inches1, "Inches object should be equal to itself (reflexive)");
    }

    @Test
    @DisplayName("Given two equal inches measurements, when compared in any order, then should be symmetric")
    public void testInchesEquality_SymmetricProperty()
    {
        QuantityLength inches1 = new QuantityLength(3.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(3.0, LengthUnit.INCH);
        assertEquals(inches1, inches2, "Equality should be symmetric");
        assertEquals(inches2, inches1, "Equality should be symmetric");
    }

    @Test
    @DisplayName("Given three equal inches measurements, when compared transitively, then should be equal")
    public void testInchesEquality_TransitiveProperty()
    {
        QuantityLength inches1 = new QuantityLength(5.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(5.0, LengthUnit.INCH);
        QuantityLength inches3 = new QuantityLength(5.0, LengthUnit.INCH);
        assertEquals(inches1, inches2, "Equality should be transitive");
        assertEquals(inches2, inches3, "Equality should be transitive");
        assertEquals(inches1, inches3, "Equality should be transitive");
    }

    @Test
    @DisplayName("Given two inches measurements of 0.0, when compared, then should be equal")
    public void testInchesEquality_ZeroValue()
    {
        QuantityLength inches1 = new QuantityLength(0.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(0.0, LengthUnit.INCH);
        assertEquals(inches1, inches2, "0.0 inch should be equal to 0.0 inch");
    }

    @Test
    @DisplayName("Given two inches measurements of -1.0, when compared, then should be equal")
    public void testInchesEquality_NegativeValue()
    {
        QuantityLength inches1 = new QuantityLength(-1.0, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(-1.0, LengthUnit.INCH);
        assertEquals(inches1, inches2, "-1.0 inch should be equal to -1.0 inch");
    }

    @Test
    @DisplayName("Given two inches measurements with decimal values, when compared, then should handle precision")
    public void testInchesEquality_DecimalPrecision()
    {
        QuantityLength inches1 = new QuantityLength(1.5, LengthUnit.INCH);
        QuantityLength inches2 = new QuantityLength(1.5, LengthUnit.INCH);
        assertEquals(inches1, inches2, "1.5 inch should be equal to 1.5 inch");
    }

    @Test
    @DisplayName("Given a Feet object and an Inches object, when compared, then should not be equal")
    public void testInchesEquality_DifferentTypes()
    {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(1.0, LengthUnit.INCH);
        assertNotEquals(feet, inches, "Feet and Inches objects should not be equal (different units)");
    }

    @Test
    @DisplayName("Given equivalent feet and inches, when compared, then should be equal")
    public void testEquality_InchToFeet_EquivalentValue()
    {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCH);
        assertEquals(feet, inches, "1.0 ft should be equal to 12.0 inch");
    }

    @Test
    @DisplayName("Given a null unit, when constructing, then should throw")
    public void testEquality_NullUnit()
    {
        assertThrows(NullPointerException.class, () -> new QuantityLength(1.0, null));
    }

    @Test
    @DisplayName("Given a NaN value, when constructing, then should throw")
    public void testEquality_InvalidValue()
    {
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    // === STATIC METHOD TEST CASES ===

    @Test
    @DisplayName("Given two feet values using static method, when compared, then should return correct equality")
    public void testStaticMethod_FeetEquality()
    {
        boolean result1 = QuantityMeasurementApp.checkFeetEquality(1.0, 1.0);
        boolean result2 = QuantityMeasurementApp.checkFeetEquality(1.0, 2.0);
        
        assertEquals(true, result1, "1.0 ft should be equal to 1.0 ft");
        assertEquals(false, result2, "1.0 ft should not be equal to 2.0 ft");
    }

    @Test
    @DisplayName("Given two inches values using static method, when compared, then should return correct equality")
    public void testStaticMethod_InchesEquality()
    {
        boolean result1 = QuantityMeasurementApp.checkInchesEquality(1.0, 1.0);
        boolean result2 = QuantityMeasurementApp.checkInchesEquality(1.0, 2.0);
        
        assertEquals(true, result1, "1.0 inch should be equal to 1.0 inch");
        assertEquals(false, result2, "1.0 inch should not be equal to 2.0 inch");
    }
}