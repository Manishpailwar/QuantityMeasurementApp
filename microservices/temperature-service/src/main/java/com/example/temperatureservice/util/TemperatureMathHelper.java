package com.example.temperatureservice.util;

import com.example.temperatureservice.exception.TemperatureException;

import java.util.Locale;

/**
 * Pure-function math helper for temperature conversions.
 * Supported units: CELSIUS/C, FAHRENHEIT/F, KELVIN/K
 *
 * Note: arithmetic operations (add/subtract/multiply/divide) on temperature are
 * physically meaningful only on the Kelvin scale, but the service exposes them
 * for completeness, operating in the source unit then converting to the result unit.
 */
public final class TemperatureMathHelper {

    private static final double EPSILON = 1e-6;

    private TemperatureMathHelper() {}

    public static double convert(double value, String fromUnit, String toUnit) {
        double celsius = toCelsius(value, norm(fromUnit));
        return fromCelsius(celsius, norm(toUnit));
    }

    public static boolean compare(double v1, String u1, double v2, String u2) {
        return Math.abs(v1 - convert(v2, u2, u1)) <= EPSILON;
    }

    public static boolean isValidUnit(String unit) {
        if (unit == null) return false;
        return switch (norm(unit)) {
            case "CELSIUS","C","FAHRENHEIT","F","KELVIN","K" -> true;
            default -> false;
        };
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    private static String norm(String unit) {
        if (unit == null || unit.isBlank()) throw new TemperatureException("Unit name is required.");
        return unit.trim().toUpperCase(Locale.ROOT);
    }

    private static double toCelsius(double v, String u) {
        return switch (u) {
            case "CELSIUS","C"       -> v;
            case "FAHRENHEIT","F"    -> (v - 32.0) * 5.0 / 9.0;
            case "KELVIN","K"        -> v - 273.15;
            default -> throw new TemperatureException("Invalid temperature unit: " + u +
                    ". Supported: CELSIUS, FAHRENHEIT, KELVIN");
        };
    }

    private static double fromCelsius(double v, String u) {
        return switch (u) {
            case "CELSIUS","C"       -> v;
            case "FAHRENHEIT","F"    -> (v * 9.0 / 5.0) + 32.0;
            case "KELVIN","K"        -> v + 273.15;
            default -> throw new TemperatureException("Invalid temperature unit: " + u +
                    ". Supported: CELSIUS, FAHRENHEIT, KELVIN");
        };
    }
}
