package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("Subtraction: 5.0 ft - 2.0 ft = 3.0 ft")
    public void testSubtraction_Length_SameUnit() {
        Quantity<LengthUnit> feet1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> feet2 = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(3.0, LengthUnit.FEET), feet1.subtract(feet2));
    }

    @Test
    @DisplayName("Subtraction: 3.0 kg - 500.0 g = 2.5 kg")
    public void testSubtraction_Weight() {
        Quantity<WeightUnit> kg = new Quantity<>(3.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(500.0, WeightUnit.GRAM);
        assertEquals(new Quantity<>(2.5, WeightUnit.KILOGRAM), kg.subtract(g));
    }

    @Test
    @DisplayName("Subtraction: 2.0 L - 500.0 mL = 1.5 L")
    public void testSubtraction_Volume() {
        Quantity<VolumeUnit> litre = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> millilitre = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        assertEquals(new Quantity<>(1.5, VolumeUnit.LITRE), litre.subtract(millilitre));
    }

    @Test
    @DisplayName("Division: 24.0 in / 12.0 in = 2.0 in")
    public void testDivision_Length_SameUnit() {
        Quantity<LengthUnit> inches1 = new Quantity<>(24.0, LengthUnit.INCH);
        Quantity<LengthUnit> inches2 = new Quantity<>(12.0, LengthUnit.INCH);
        assertEquals(new Quantity<>(2.0, LengthUnit.INCH), inches1.divide(inches2));
    }

    @Test
    @DisplayName("Division: by zero throws exception")
    public void testDivision_ByZero() {
        Quantity<LengthUnit> feet = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> zero = new Quantity<>(0.0, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> feet.divide(zero));
    }

    @Test
    @DisplayName("Temperature: 0 C equals 32 F")
    public void testTemperatureEquality_CelsiusFahrenheit() {
        Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        assertEquals(c, f);
    }

    @Test
    @DisplayName("Temperature: 0 C equals 273.15 K")
    public void testTemperatureEquality_CelsiusKelvin() {
        Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> k = new Quantity<>(273.15, TemperatureUnit.KELVIN);
        assertEquals(c, k);
    }

    @Test
    @DisplayName("Temperature: convert 100 C to 212 F")
    public void testTemperatureConversion_CelsiusToFahrenheit() {
        Quantity<TemperatureUnit> c = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = c.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(212.0, f.getValue(), EPSILON);
    }

    @Test
    @DisplayName("Temperature: convert 32 F to 0 C")
    public void testTemperatureConversion_FahrenheitToCelsius() {
        Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> c = f.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(0.0, c.getValue(), EPSILON);
    }

    @Test
    @DisplayName("Temperature: arithmetic addition not allowed")
    public void testTemperature_Addition_NotAllowed() {
        Quantity<TemperatureUnit> c1 = new Quantity<>(10.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> c2 = new Quantity<>(20.0, TemperatureUnit.CELSIUS);
        assertThrows(UnsupportedOperationException.class, () -> c1.add(c2));
    }

    @Test
    @DisplayName("Temperature: subtraction not allowed")
    public void testTemperature_Subtraction_NotAllowed() {
        Quantity<TemperatureUnit> c1 = new Quantity<>(10.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> c2 = new Quantity<>(5.0, TemperatureUnit.CELSIUS);
        assertThrows(UnsupportedOperationException.class, () -> c1.subtract(c2));
    }

    @Test
    @DisplayName("Temperature: division not allowed")
    public void testTemperature_Division_NotAllowed() {
        Quantity<TemperatureUnit> c1 = new Quantity<>(10.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> c2 = new Quantity<>(2.0, TemperatureUnit.CELSIUS);
        assertThrows(UnsupportedOperationException.class, () -> c1.divide(c2));
    }
}