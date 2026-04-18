package com.example.weightvolumeservice.util;

import com.example.weightvolumeservice.exception.MeasurementException;

import java.util.Locale;

/**
 * Pure-function math helper for weight and volume conversions.
 *
 * Weight units : GRAM/GRAMS/G, KILOGRAM/KILOGRAMS/KG, POUND/POUNDS/LB/LBS
 * Volume units : MILLILITER/MILLILITERS/ML, LITER/LITERS/L, GALLON/GALLONS
 */
public final class WeightVolumeMathHelper {

    private static final double EPSILON = 1e-6;

    private WeightVolumeMathHelper() {}

    // ── Weight ────────────────────────────────────────────────────────────────

    public static double convertWeight(double value, String from, String to) {
        double kg = toKilograms(value, norm(from));
        return fromKilograms(kg, norm(to));
    }

    public static boolean compareWeight(double v1, String u1, double v2, String u2) {
        return Math.abs(v1 - convertWeight(v2, u2, u1)) <= EPSILON;
    }

    public static double addWeight(double v1, String u1, double v2, String u2) {
        return v1 + convertWeight(v2, u2, u1);
    }

    public static double subtractWeight(double v1, String u1, double v2, String u2) {
        return v1 - convertWeight(v2, u2, u1);
    }

    public static double multiplyWeight(double v1, String u1, double v2, String u2) {
        return v1 * convertWeight(v2, u2, u1);
    }

    public static double divideWeight(double v1, String u1, double v2, String u2) {
        double conv = convertWeight(v2, u2, u1);
        if (Math.abs(conv) <= EPSILON) throw new ArithmeticException("Divide by zero");
        return v1 / conv;
    }

    // ── Volume ────────────────────────────────────────────────────────────────

    public static double convertVolume(double value, String from, String to) {
        double liters = toLiters(value, norm(from));
        return fromLiters(liters, norm(to));
    }

    public static boolean compareVolume(double v1, String u1, double v2, String u2) {
        return Math.abs(v1 - convertVolume(v2, u2, u1)) <= EPSILON;
    }

    public static double addVolume(double v1, String u1, double v2, String u2) {
        return v1 + convertVolume(v2, u2, u1);
    }

    public static double subtractVolume(double v1, String u1, double v2, String u2) {
        return v1 - convertVolume(v2, u2, u1);
    }

    public static double multiplyVolume(double v1, String u1, double v2, String u2) {
        return v1 * convertVolume(v2, u2, u1);
    }

    public static double divideVolume(double v1, String u1, double v2, String u2) {
        double conv = convertVolume(v2, u2, u1);
        if (Math.abs(conv) <= EPSILON) throw new ArithmeticException("Divide by zero");
        return v1 / conv;
    }

    // ── Validation ────────────────────────────────────────────────────────────

    public static boolean isWeightUnit(String unit) {
        if (unit == null) return false;
        return switch (norm(unit)) {
            case "GRAM","GRAMS","G","KILOGRAM","KILOGRAMS","KG","POUND","POUNDS","LB","LBS" -> true;
            default -> false;
        };
    }

    public static boolean isVolumeUnit(String unit) {
        if (unit == null) return false;
        return switch (norm(unit)) {
            case "MILLILITER","MILLILITERS","ML","LITER","LITERS","L","GALLON","GALLONS" -> true;
            default -> false;
        };
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    private static String norm(String unit) {
        if (unit == null || unit.isBlank()) throw new MeasurementException("Unit name is required.");
        return unit.trim().toUpperCase(Locale.ROOT);
    }

    private static double toKilograms(double v, String u) {
        return switch (u) {
            case "GRAM","GRAMS","G"           -> v * 0.001;
            case "KILOGRAM","KILOGRAMS","KG"  -> v;
            case "POUND","POUNDS","LB","LBS"  -> v * 0.45359237;
            default -> throw new MeasurementException("Invalid weight unit: " + u);
        };
    }

    private static double fromKilograms(double v, String u) {
        return switch (u) {
            case "GRAM","GRAMS","G"           -> v / 0.001;
            case "KILOGRAM","KILOGRAMS","KG"  -> v;
            case "POUND","POUNDS","LB","LBS"  -> v / 0.45359237;
            default -> throw new MeasurementException("Invalid weight unit: " + u);
        };
    }

    private static double toLiters(double v, String u) {
        return switch (u) {
            case "MILLILITER","MILLILITERS","ML" -> v * 0.001;
            case "LITER","LITERS","L"            -> v;
            case "GALLON","GALLONS"              -> v * 3.78541;
            default -> throw new MeasurementException("Invalid volume unit: " + u);
        };
    }

    private static double fromLiters(double v, String u) {
        return switch (u) {
            case "MILLILITER","MILLILITERS","ML" -> v / 0.001;
            case "LITER","LITERS","L"            -> v;
            case "GALLON","GALLONS"              -> v / 3.78541;
            default -> throw new MeasurementException("Invalid volume unit: " + u);
        };
    }
}
