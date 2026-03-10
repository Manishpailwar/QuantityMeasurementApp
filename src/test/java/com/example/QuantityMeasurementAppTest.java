package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;

    // UC4: EQUALITY TEST CASES - GENERIC QUANTITY
    @Test
    @DisplayName("Given two feet measurements of 1.0, when compared, then should be equal")
    public void testEquality_SameValue() {
        Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> feet2 = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(feet1, feet2, "The measurements should be identical");
    }

    @Test
    @DisplayName("Given equivalent feet and inches, when compared, then should be equal")
    public void testEquality_InchToFeet_EquivalentValue() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCH);
        assertEquals(feet, inches, "1.0 ft should be equal to 12.0 inch");
    }

    @Test
    @DisplayName("Given a feet measurement, when compared to null, then should not be equal")
    public void testEquality_NullComparison() {
        Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotEquals(feet1, null, "Feet object should not be equal to null");
    }

    // UC5: CONVERSION TEST CASES - GENERIC QUANTITY
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
    @DisplayName("Given Quantity in feet, when using convertTo method, then should return correct value")
    public void testConversion_InstanceMethod_FeetToInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = feet.convertTo(LengthUnit.INCH);
        assertEquals(12.0, result.getValue(), EPSILON, "1.0 ft should convert to 12.0 inches");
        assertEquals(LengthUnit.INCH, result.getUnit(), "Converted unit should be INCH");
    }

    @Test
    @DisplayName("Given Quantity instance, when toString called, then should return formatted string")
    public void testToString() {
        Quantity<LengthUnit> length = new Quantity<>(1.5, LengthUnit.FEET);
        String result = length.toString();
        assertEquals("1.50 FEET", result, "toString should return formatted string");
    }

    // UC6: ADDITION TEST CASES - GENERIC QUANTITY
    @Test
    @DisplayName("Given 1.0 feet and 2.0 feet, when added, then should return 3.0 feet")
    public void testAddition_SameUnit() {
        Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> feet2 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = feet1.add(feet2);
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Given 1.0 feet and 12.0 inches, when added in feet, then should return 2.0 feet")
    public void testAddition_CrossUnit() {
        Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches1 = new Quantity<>(12.0, LengthUnit.INCH);
        Quantity<LengthUnit> result = feet1.add(inches1, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    @DisplayName("Given null operand, when adding, then should throw exception")
    public void testAddition_Validation() {
        Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> feet1.add(null));
    }

    // UC8: UNIT ENUM WITH CONVERSION RESPONSIBILITY
    @Test
    @DisplayName("UC8: Given 1.0 feet, when checking conversion factor, then should return 1.0")
    public void testLengthUnitEnum_Conversion() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
        assertEquals(12.0, LengthUnit.INCH.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    @DisplayName("UC8: Given refactored design, when comparing 1.0 feet and 12.0 inches, then should be equal")
    public void testQuantityRefactored_Equality() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCH);
        assertEquals(feet, inches);
    }

    // UC9: WEIGHT MEASUREMENT TEST CASES - GENERIC QUANTITY
    @Test
    @DisplayName("Given 1.0 kg and 1000.0 g, when compared, then should be equal")
    public void testWeightEquality_KilogramToGram() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertEquals(kg, g, "1.0 kg should equal 1000.0 g");
    }

    @Test
    @DisplayName("Given 2.0 kg, when converted to grams, then should return 2000.0 g")
    public void testWeightConversion_KilogramToGram() {
        Quantity<WeightUnit> kg = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = kg.convertTo(WeightUnit.GRAM);
        assertEquals(new Quantity<>(2000.0, WeightUnit.GRAM), g, "2.0 kg should convert to 2000.0 g");
    }

    @Test
    @DisplayName("Given 3.0 kg + 500.0 g, when added with explicit target, then should return 3.5 kg")
    public void testWeightAddition_ExplicitTarget() {
        Quantity<WeightUnit> kg = new Quantity<>(3.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(500.0, WeightUnit.GRAM);
        Quantity<WeightUnit> result = kg.add(g, WeightUnit.KILOGRAM);
        assertEquals(new Quantity<>(3.5, WeightUnit.KILOGRAM), result, "3.0 kg + 500.0 g should equal 3.5 kg");
    }

    @Test
    @DisplayName("Given null weight unit, when creating Quantity, then should throw exception")
    public void testWeightValidation_NullUnit() {
        assertThrows(NullPointerException.class, () -> {
            new Quantity<>(1.0, null);
        }, "Creating Quantity with null unit should throw exception");
    }

    // UC10: GENERIC QUANTITY AND IMEASURABLE INTERFACE TEST CASES

    @Test
    @DisplayName("UC10: LengthUnit implements Measurable interface")
    public void testMeasurable_LengthUnitImplementation() {
        assertTrue(Measurable.class.isAssignableFrom(LengthUnit.class), "LengthUnit should implement Measurable");
        Measurable unit = LengthUnit.FEET;
        assertNotEquals(null, unit.getConversionFactor());
        assertNotEquals(null, unit.getUnitName());
    }

    @Test
    @DisplayName("UC10: WeightUnit implements Measurable interface")
    public void testMeasurable_WeightUnitImplementation() {
        assertTrue(Measurable.class.isAssignableFrom(WeightUnit.class), "WeightUnit should implement Measurable");
        Measurable unit = WeightUnit.KILOGRAM;
        assertNotEquals(null, unit.getConversionFactor());
        assertNotEquals(null, unit.getUnitName());
    }

    @Test
    @DisplayName("UC10: Generic Quantity<LengthUnit> works as expected")
    public void testGenericQuantity_Length() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCH);
        assertEquals(feet, inches);
    }

    @Test
    @DisplayName("UC10: Generic Quantity<WeightUnit> works as expected")
    public void testGenericQuantity_Weight() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertEquals(kg, g);
    }

    @Test
    @DisplayName("UC10: Cross-category comparison prevention (Length vs Weight)")
    public void testCrossCategory_Prevention() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(feet, kg, "Different measurement categories should not be equal");
    }

    @Test
    @DisplayName("UC10: Generic demonstration methods work with Quantity<LengthUnit>")
    public void testGenericDemoMethods_Length() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCH);
        // Should not throw any exceptions
        QuantityMeasurementApp.demonstrateEquality(feet, inches);
        QuantityMeasurementApp.demonstrateConversion(feet, LengthUnit.INCH);
        QuantityMeasurementApp.demonstrateAddition(feet, inches);
    }

    @Test
    @DisplayName("UC10: Generic demonstration methods work with Quantity<WeightUnit>")
    public void testGenericDemoMethods_Weight() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);
        // Should not throw any exceptions
        QuantityMeasurementApp.demonstrateEquality(kg, g);
        QuantityMeasurementApp.demonstrateConversion(kg, WeightUnit.GRAM);
        QuantityMeasurementApp.demonstrateAddition(kg, g);
    }

    @Test
    @DisplayName("UC10: Measurable interface defines required methods")
    public void testMeasurable_Contract() {
        LengthUnit feet = LengthUnit.FEET;
        assertEquals(1.0, feet.getConversionFactor());
        assertEquals("FEET", feet.getUnitName());
        assertEquals(5.0, feet.convertToBaseUnit(5.0), EPSILON);
        assertEquals(5.0, feet.convertFromBaseUnit(5.0), EPSILON);
    }

    @Test
    @DisplayName("UC10: Backward compatibility - Quantity factory methods still work")
    public void testBackwardCompatibility_Quantity() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(1.0, feet.getValue());
        assertEquals(LengthUnit.FEET, feet.getUnit());
    }

    @Test
    @DisplayName("UC10: Simplified QuantityMeasurementApp methods for length")
    public void testBackwardCompatibility_AppMethods_Length() {
        assertEquals(12.0, QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCH), EPSILON);
        assertEquals(2.0, QuantityMeasurementApp.add(1.0, LengthUnit.FEET, 1.0, LengthUnit.FEET, LengthUnit.FEET), EPSILON);
    }

    @Test
    @DisplayName("UC10: Simplified QuantityMeasurementApp methods for weight")
    public void testBackwardCompatibility_AppMethods_Weight() {
        assertEquals(2000.0, QuantityMeasurementApp.convertWeight(2.0, WeightUnit.KILOGRAM, WeightUnit.GRAM), EPSILON);
        assertEquals(2.0, QuantityMeasurementApp.addWeight(1.0, WeightUnit.KILOGRAM, 1.0, WeightUnit.KILOGRAM, WeightUnit.KILOGRAM), EPSILON);
    }

    @Test
    @DisplayName("UC10: Check feet equality helper method")
    public void testCheckFeetEquality() {
        assertTrue(QuantityMeasurementApp.checkFeetEquality(1.0, 1.0));
        assertNotEquals(QuantityMeasurementApp.checkFeetEquality(1.0, 2.0), true);
    }

    @Test
    @DisplayName("UC10: Check kilogram equality helper method")
    public void testCheckKilogramEquality() {
        assertTrue(QuantityMeasurementApp.checkKilogramEquality(1.0, 1.0));
        assertNotEquals(QuantityMeasurementApp.checkKilogramEquality(1.0, 2.0), true);
    }

    @Test
    @DisplayName("UC10: Null unit throws exception on construction")
    public void testNullUnitThrowsException() {
        assertThrows(NullPointerException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    @DisplayName("UC10: NaN value throws exception on construction")
    public void testNaNValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    @DisplayName("UC10: Infinite value throws exception on construction")
    public void testInfiniteValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }
}