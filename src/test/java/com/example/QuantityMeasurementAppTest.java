package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class QuantityMeasurementAppTest 
{
    @Test
    @DisplayName("Given two feet measurements of 1.0, when compared, then should be equal")
    public void testEquality_SameValue()
    {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(1.0);
        assertEquals(feet1, feet2, "The measurements should be identical");
    }

    @Test
    @DisplayName("Given two feet measurements of 1.0 and 2.0, when compared, then should not be equal")
    public void testEquality_DifferentValue()
    {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(2.0);
        assertNotEquals(feet1, feet2, "1.0 ft should not be equal to 2.0 ft");
    }

    @Test
    @DisplayName("Given a feet measurement, when compared to null, then should not be equal")
    public void testEquality_NullComparison()
    {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        assertNotEquals(feet1, null, "Feet object should not be equal to null");
    }

    @Test
    @DisplayName("Given a feet measurement, when compared to non-Feet object, then should not be equal")
    public void testEquality_NonNumericInput()
    {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        String nonNumeric = "1.0";
        assertNotEquals(feet1, nonNumeric, "Feet object should not be equal to non-Feet object");
    }

    @Test
    @DisplayName("Given a feet measurement, when compared to itself, then should be equal")
    public void testEquality_SameReference()
    {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        assertEquals(feet1, feet1, "Feet object should be equal to itself (reflexive)");
    }

    @Test
    @DisplayName("Given two equal feet measurements, when compared in any order, then should be symmetric")
    public void testEquality_SymmetricProperty()
    {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(3.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(3.0);
        assertEquals(feet1, feet2, "Equality should be symmetric");
        assertEquals(feet2, feet1, "Equality should be symmetric");
    }

    @Test
    @DisplayName("Given three equal feet measurements, when compared transitively, then should be equal")
    public void testEquality_TransitiveProperty()
    {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(5.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(5.0);
        QuantityMeasurementApp.Feet feet3 = new QuantityMeasurementApp.Feet(5.0);
        assertEquals(feet1, feet2, "Equality should be transitive");
        assertEquals(feet2, feet3, "Equality should be transitive");
        assertEquals(feet1, feet3, "Equality should be transitive");
    }

    @Test
    @DisplayName("Given two feet measurements of 0.0, when compared, then should be equal")
    public void testEquality_ZeroValue()
    {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(0.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(0.0);
        assertEquals(feet1, feet2, "0.0 ft should be equal to 0.0 ft");
    }

    @Test
    @DisplayName("Given two feet measurements of -1.0, when compared, then should be equal")
    public void testEquality_NegativeValue()
    {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(-1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(-1.0);
        assertEquals(feet1, feet2, "-1.0 ft should be equal to -1.0 ft");
    }

    @Test
    @DisplayName("Given two feet measurements with decimal values, when compared, then should handle precision")
    public void testEquality_DecimalPrecision()
    {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.5);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(1.5);
        assertEquals(feet1, feet2, "1.5 ft should be equal to 1.5 ft");
    }

    // === INCHES EQUALITY TEST CASES ===

    @Test
    @DisplayName("Given two inches measurements of 1.0, when compared, then should be equal")
    public void testInchesEquality_SameValue()
    {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches inches2 = new QuantityMeasurementApp.Inches(1.0);
        assertEquals(inches1, inches2, "The measurements should be identical");
    }

    @Test
    @DisplayName("Given two inches measurements of 1.0 and 2.0, when compared, then should not be equal")
    public void testInchesEquality_DifferentValue()
    {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches inches2 = new QuantityMeasurementApp.Inches(2.0);
        assertNotEquals(inches1, inches2, "1.0 inch should not be equal to 2.0 inch");
    }

    @Test
    @DisplayName("Given an inches measurement, when compared to null, then should not be equal")
    public void testInchesEquality_NullComparison()
    {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(1.0);
        assertNotEquals(inches1, null, "Inches object should not be equal to null");
    }

    @Test
    @DisplayName("Given an inches measurement, when compared to non-Inches object, then should not be equal")
    public void testInchesEquality_NonNumericInput()
    {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(1.0);
        String nonNumeric = "1.0";
        assertNotEquals(inches1, nonNumeric, "Inches object should not be equal to non-Inches object");
    }

    @Test
    @DisplayName("Given an inches measurement, when compared to itself, then should be equal")
    public void testInchesEquality_SameReference()
    {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(1.0);
        assertEquals(inches1, inches1, "Inches object should be equal to itself (reflexive)");
    }

    @Test
    @DisplayName("Given two equal inches measurements, when compared in any order, then should be symmetric")
    public void testInchesEquality_SymmetricProperty()
    {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(3.0);
        QuantityMeasurementApp.Inches inches2 = new QuantityMeasurementApp.Inches(3.0);
        assertEquals(inches1, inches2, "Equality should be symmetric");
        assertEquals(inches2, inches1, "Equality should be symmetric");
    }

    @Test
    @DisplayName("Given three equal inches measurements, when compared transitively, then should be equal")
    public void testInchesEquality_TransitiveProperty()
    {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(5.0);
        QuantityMeasurementApp.Inches inches2 = new QuantityMeasurementApp.Inches(5.0);
        QuantityMeasurementApp.Inches inches3 = new QuantityMeasurementApp.Inches(5.0);
        assertEquals(inches1, inches2, "Equality should be transitive");
        assertEquals(inches2, inches3, "Equality should be transitive");
        assertEquals(inches1, inches3, "Equality should be transitive");
    }

    @Test
    @DisplayName("Given two inches measurements of 0.0, when compared, then should be equal")
    public void testInchesEquality_ZeroValue()
    {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(0.0);
        QuantityMeasurementApp.Inches inches2 = new QuantityMeasurementApp.Inches(0.0);
        assertEquals(inches1, inches2, "0.0 inch should be equal to 0.0 inch");
    }

    @Test
    @DisplayName("Given two inches measurements of -1.0, when compared, then should be equal")
    public void testInchesEquality_NegativeValue()
    {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(-1.0);
        QuantityMeasurementApp.Inches inches2 = new QuantityMeasurementApp.Inches(-1.0);
        assertEquals(inches1, inches2, "-1.0 inch should be equal to -1.0 inch");
    }

    @Test
    @DisplayName("Given two inches measurements with decimal values, when compared, then should handle precision")
    public void testInchesEquality_DecimalPrecision()
    {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(1.5);
        QuantityMeasurementApp.Inches inches2 = new QuantityMeasurementApp.Inches(1.5);
        assertEquals(inches1, inches2, "1.5 inch should be equal to 1.5 inch");
    }

    @Test
    @DisplayName("Given a Feet object and an Inches object, when compared, then should not be equal")
    public void testInchesEquality_DifferentTypes()
    {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Inches inches = new QuantityMeasurementApp.Inches(1.0);
        assertNotEquals(feet, inches, "Feet and Inches objects should not be equal (different types)");
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