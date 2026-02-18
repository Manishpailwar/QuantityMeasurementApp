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
}