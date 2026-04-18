package com.example.lengthservice.util;

import com.example.lengthservice.exception.LengthMeasurementException;

import java.util.Locale;

/**
 * Pure-function math helper for length unit conversions and operations.
 * Supported units: INCH/INCHES, FOOT/FEET, YARD/YARDS, CENTIMETER/CENTIMETERS/CM, METER/METERS/M
 */
public final class LengthMathHelper {

    private static final double EPSILON = 1e-6;

    private LengthMathHelper() {}

    public static double convert(double value, String fromUnit, String toUnit) {
        double meters = toMeters(value, normalize(fromUnit));
        return fromMeters(meters, normalize(toUnit));
    }

    public static boolean compare(double v1, String u1, double v2, String u2) {
        double converted = convert(v2, u2, u1);
        return Math.abs(v1 - converted) <= EPSILON;
    }

    public static double add(double v1, String u1, double v2, String u2) {
        return v1 + convert(v2, u2, u1);
    }

    public static double subtract(double v1, String u1, double v2, String u2) {
        return v1 - convert(v2, u2, u1);
    }

    public static double multiply(double v1, String u1, double v2, String u2) {
        return v1 * convert(v2, u2, u1);
    }

    public static double divide(double v1, String u1, double v2, String u2) {
        double converted = convert(v2, u2, u1);
        if (Math.abs(converted) <= EPSILON) {
            throw new ArithmeticException("Divide by zero");
        }
        return v1 / converted;
    }

    public static boolean isValidUnit(String unit) {
        if (unit == null) return false;
        return switch (normalize(unit)) {
            case "INCH", "INCHES", "FOOT", "FEET", "YARD", "YARDS",
                 "CENTIMETER", "CENTIMETERS", "CM", "METER", "METERS", "M" -> true;
            default -> false;
        };
    }

    private static String normalize(String unit) {
        if (unit == null || unit.isBlank()) {
            throw new LengthMeasurementException("Unit name is required.");
        }
        return unit.trim().toUpperCase(Locale.ROOT);
    }

    private static double toMeters(double value, String unit) {
        return switch (unit) {
            case "INCH", "INCHES"                          -> value * 0.0254;
            case "FOOT", "FEET"                            -> value * 0.3048;
            case "YARD", "YARDS"                           -> value * 0.9144;
            case "CENTIMETER", "CENTIMETERS", "CM"         -> value * 0.01;
            case "METER", "METERS", "M"                    -> value;
            default -> throw new LengthMeasurementException("Invalid length unit: " + unit);
        };
    }

    private static double fromMeters(double value, String unit) {
        return switch (unit) {
            case "INCH", "INCHES"                          -> value / 0.0254;
            case "FOOT", "FEET"                            -> value / 0.3048;
            case "YARD", "YARDS"                           -> value / 0.9144;
            case "CENTIMETER", "CENTIMETERS", "CM"         -> value / 0.01;
            case "METER", "METERS", "M"                    -> value;
            default -> throw new LengthMeasurementException("Invalid length unit: " + unit);
        };
    }
}
