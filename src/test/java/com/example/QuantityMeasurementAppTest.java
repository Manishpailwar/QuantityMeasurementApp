package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;

    @Test
    @DisplayName("Length: 1.0 ft equals 12.0 in")
    public void testLengthEquality_FeetToInch() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCH);
        assertEquals(feet, inches);
    }

    @Test
    @DisplayName("Length: convert 1.0 ft to 12.0 in")
    public void testLengthConversion_FeetToInches() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(12.0, result, EPSILON);
    }

    @Test
    @DisplayName("Length: add 1.0 ft + 2.0 ft = 3.0 ft")
    public void testLengthAddition_SameUnit() {
        Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> feet2 = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(3.0, LengthUnit.FEET), feet1.add(feet2));
    }

    @Test
    @DisplayName("Weight: 1.0 kg equals 1000.0 g")
    public void testWeightEquality_KilogramToGram() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertEquals(kg, g);
    }

    @Test
    @DisplayName("Weight: convert 2.0 kg to 2000.0 g")
    public void testWeightConversion_KilogramToGram() {
        Quantity<WeightUnit> kg = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = kg.convertTo(WeightUnit.GRAM);
        assertEquals(new Quantity<>(2000.0, WeightUnit.GRAM), g);
    }

    @Test
    @DisplayName("Weight: add 3.0 kg + 500.0 g = 3.5 kg")
    public void testWeightAddition_ExplicitTarget() {
        Quantity<WeightUnit> kg = new Quantity<>(3.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(500.0, WeightUnit.GRAM);
        assertEquals(new Quantity<>(3.5, WeightUnit.KILOGRAM), kg.add(g, WeightUnit.KILOGRAM));
    }

    @Test
    @DisplayName("Cross-category: length vs weight")
    public void testCrossCategory_Prevention() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(feet, kg);
    }

    @Test
    @DisplayName("Volume: 1.0 L equals 1000.0 mL")
    public void testVolumeEquality_LitreToMillilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> millilitre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(litre, millilitre);
    }

    @Test
    @DisplayName("Volume: convert 1.0 gallon to ~3.78541 L")
    public void testVolumeConversion_GallonToLitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = gallon.convertTo(VolumeUnit.LITRE);
        assertEquals(3.79, result.getValue(), 0.01);
    }

    @Test
    @DisplayName("Volume: add 1.0 L + 1000.0 mL = 2.0 L")
    public void testVolumeAddition_ImplicitTarget() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> millilitre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), litre.add(millilitre));
    }

    @Test
    @DisplayName("Volume: volume and length are incompatible")
    public void testVolumeCrossCategory_Prevention() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> foot = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotEquals(litre, foot);
    }

    @Test
    @DisplayName("VolumeUnit: base conversion factor")
    public void testVolumeUnit_ConversionFactor() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), EPSILON);
        assertEquals(3.78541, VolumeUnit.GALLON.convertToBaseUnit(1.0), EPSILON);
    }
}