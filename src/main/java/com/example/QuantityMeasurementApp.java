package com.example;

public class QuantityMeasurementApp {

    private static final double EPSILON = 1e-6;

    public static <U extends Measurable> double convert(double value, U source, U target) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number.");
        }
        if (source == null) {
            throw new IllegalArgumentException("Source unit must not be null.");
        }
        if (target == null) {
            throw new IllegalArgumentException("Target unit must not be null.");
        }

        Quantity<U> quantity = new Quantity<>(value, source);
        return quantity.convertTo(target).getValue();
    }

    public static <U extends Measurable> double add(double value1, U unit1, double value2, U unit2, U resultUnit) {
        Quantity<U> q1 = new Quantity<>(value1, unit1);
        Quantity<U> q2 = new Quantity<>(value2, unit2);
        return q1.add(q2, resultUnit).getValue();
    }

    public static <U extends Measurable> Quantity<U> add(Quantity<U> q1, Quantity<U> q2, U resultUnit) {
        if (q1 == null || q2 == null) {
            throw new IllegalArgumentException("Quantities must not be null.");
        }
        return q1.add(q2, resultUnit);
    }

    public static <U extends Measurable> Quantity<U> add(Quantity<U> q1, Quantity<U> q2) {
        if (q1 == null || q2 == null) {
            throw new IllegalArgumentException("Quantities must not be null.");
        }
        return q1.add(q2);
    }

    public static <U extends Measurable> Quantity<U> subtract(Quantity<U> q1, Quantity<U> q2) {
        if (q1 == null || q2 == null) {
            throw new IllegalArgumentException("Quantities must not be null.");
        }
        return q1.subtract(q2);
    }

    public static <U extends Measurable> Quantity<U> subtract(Quantity<U> q1, Quantity<U> q2, U resultUnit) {
        if (q1 == null || q2 == null) {
            throw new IllegalArgumentException("Quantities must not be null.");
        }
        return q1.subtract(q2, resultUnit);
    }

    public static <U extends Measurable> Quantity<U> divide(Quantity<U> q1, Quantity<U> q2) {
        if (q1 == null || q2 == null) {
            throw new IllegalArgumentException("Quantities must not be null.");
        }
        return q1.divide(q2);
    }

    public static <U extends Measurable> Quantity<U> divide(Quantity<U> q1, Quantity<U> q2, U resultUnit) {
        if (q1 == null || q2 == null) {
            throw new IllegalArgumentException("Quantities must not be null.");
        }
        return q1.divide(q2, resultUnit);
    }

    public static <U extends Measurable> void demonstrateConversion(double value, U fromUnit, U toUnit) {
        double result = convert(value, fromUnit, toUnit);
        System.out.printf("Converting %.2f %s to %s = %.2f%n", value, fromUnit.getUnitName(), toUnit.getUnitName(), result);
    }

    public static <U extends Measurable> void demonstrateEquality(double value1, U unit1, double value2, U unit2) {
        Quantity<U> q1 = new Quantity<>(value1, unit1);
        Quantity<U> q2 = new Quantity<>(value2, unit2);
        System.out.println(q1 + " equals " + q2 + " : " + q1.equals(q2));
    }

    public static boolean checkFeetEquality(double v1, double v2) {
        return new Quantity<>(v1, LengthUnit.FEET).equals(new Quantity<>(v2, LengthUnit.FEET));
    }

    public static boolean checkInchesEquality(double v1, double v2) {
        return new Quantity<>(v1, LengthUnit.INCH).equals(new Quantity<>(v2, LengthUnit.INCH));
    }

    public static boolean checkYardEquality(double v1, double v2) {
        return new Quantity<>(v1, LengthUnit.YARD).equals(new Quantity<>(v2, LengthUnit.YARD));
    }

    public static boolean checkCentimeterEquality(double v1, double v2) {
        return new Quantity<>(v1, LengthUnit.CENTIMETER).equals(new Quantity<>(v2, LengthUnit.CENTIMETER));
    }

    public static boolean checkKilogramEquality(double v1, double v2) {
        return new Quantity<>(v1, WeightUnit.KILOGRAM).equals(new Quantity<>(v2, WeightUnit.KILOGRAM));
    }

    public static boolean checkGramEquality(double v1, double v2) {
        return new Quantity<>(v1, WeightUnit.GRAM).equals(new Quantity<>(v2, WeightUnit.GRAM));
    }

    public static boolean checkPoundEquality(double v1, double v2) {
        return new Quantity<>(v1, WeightUnit.POUND).equals(new Quantity<>(v2, WeightUnit.POUND));
    }

    public static double convertTemperature(double value, TemperatureUnit source, TemperatureUnit target) {
        return convert(value, source, target);
    }

    public static void demonstrateTemperatureConversion(double value, TemperatureUnit from, TemperatureUnit to) {
        demonstrateConversion(value, from, to);
    }

    public static void demonstrateTemperatureEquality(double v1, TemperatureUnit u1, double v2, TemperatureUnit u2) {
        demonstrateEquality(v1, u1, v2, u2);
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> feet5 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches24 = new Quantity<>(24.0, LengthUnit.INCH);

        System.out.println(feet5.add(inches24));
        System.out.println(feet5.subtract(inches24));
        System.out.println(inches24.divide(feet5));

        Quantity<WeightUnit> kg3 = new Quantity<>(3.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g500 = new Quantity<>(500.0, WeightUnit.GRAM);

        System.out.println(kg3.add(g500));
        System.out.println(kg3.subtract(g500));
        System.out.println(kg3.divide(g500));

        Quantity<TemperatureUnit> litre2 = new Quantity<>(2.0, TemperatureUnit.LITRE);
        Quantity<TemperatureUnit> ml500 = new Quantity<>(500.0, TemperatureUnit.MILLILITRE);

        System.out.println(litre2.add(ml500));
        System.out.println(litre2.subtract(ml500));
        System.out.println(litre2.divide(ml500));

        demonstrateTemperatureConversion(0, TemperatureUnit.CELSIUS, TemperatureUnit.FAHRENHEIT);
        demonstrateTemperatureConversion(32, TemperatureUnit.FAHRENHEIT, TemperatureUnit.CELSIUS);
        demonstrateTemperatureConversion(273.15, TemperatureUnit.KELVIN, TemperatureUnit.CELSIUS);

        demonstrateTemperatureEquality(0, TemperatureUnit.CELSIUS, 32, TemperatureUnit.FAHRENHEIT);
    }
}