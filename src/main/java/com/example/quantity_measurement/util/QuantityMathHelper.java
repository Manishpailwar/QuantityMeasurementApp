package com.example.quantity_measurement.util;

import com.example.quantity_measurement.exception.QuantityMeasurementException;
import java.util.Locale;

public final class QuantityMathHelper {
    private static final double EPSILON = 1e-6;

    private QuantityMathHelper() {
    }

    public static boolean compare(double thisValue, String thisUnit, String thisMeasurementType,
            double thatValue, String thatUnit, String thatMeasurementType) {
        ensureSameMeasurementType(thisMeasurementType, thatMeasurementType, "compare");
        double convertedThatValue = convert(thatValue, thatUnit, thisUnit, thisMeasurementType);
        return Math.abs(thisValue - convertedThatValue) <= EPSILON;
    }

    public static double convert(double value, String fromUnit, String toUnit, String measurementType) {
        String normalizedMeasurementType = normalizeMeasurementType(measurementType);
        String normalizedFromUnit = normalizeUnit(fromUnit);
        String normalizedToUnit = normalizeUnit(toUnit);

        return switch (normalizedMeasurementType) {
            case "LengthUnit" -> {
                double baseMeters = toMeters(value, normalizedFromUnit);
                yield fromMeters(baseMeters, normalizedToUnit);
            }
            case "WeightUnit" -> {
                double baseKilograms = toKilograms(value, normalizedFromUnit);
                yield fromKilograms(baseKilograms, normalizedToUnit);
            }
            case "VolumeUnit" -> {
                double baseLiters = toLiters(value, normalizedFromUnit);
                yield fromLiters(baseLiters, normalizedToUnit);
            }
            case "TemperatureUnit" -> {
                double baseCelsius = toCelsius(value, normalizedFromUnit);
                yield fromCelsius(baseCelsius, normalizedToUnit);
            }
            default -> throw new QuantityMeasurementException("Unsupported measurement type: " + measurementType);
        };
    }

    public static double add(double thisValue, String thisUnit, String thisMeasurementType,
            double thatValue, String thatUnit, String thatMeasurementType) {
        ensureSameMeasurementType(thisMeasurementType, thatMeasurementType, "add");
        double convertedThatValue = convert(thatValue, thatUnit, thisUnit, thisMeasurementType);
        return thisValue + convertedThatValue;
    }

    public static double subtract(double thisValue, String thisUnit, String thisMeasurementType,
            double thatValue, String thatUnit, String thatMeasurementType) {
        ensureSameMeasurementType(thisMeasurementType, thatMeasurementType, "subtract");
        double convertedThatValue = convert(thatValue, thatUnit, thisUnit, thisMeasurementType);
        return thisValue - convertedThatValue;
    }

    public static double multiply(double thisValue, String thisUnit, String thisMeasurementType,
            double thatValue, String thatUnit, String thatMeasurementType) {
        ensureSameMeasurementType(thisMeasurementType, thatMeasurementType, "multiply");
        double convertedThatValue = convert(thatValue, thatUnit, thisUnit, thisMeasurementType);
        return thisValue * convertedThatValue;
    }

    public static double divide(double thisValue, String thisUnit, String thisMeasurementType,
            double thatValue, String thatUnit, String thatMeasurementType) {
        ensureSameMeasurementType(thisMeasurementType, thatMeasurementType, "divide");
        double convertedThatValue = convert(thatValue, thatUnit, thisUnit, thisMeasurementType);
        if (Math.abs(convertedThatValue) <= EPSILON) {
            throw new ArithmeticException("Divide by zero");
        }
        return thisValue / convertedThatValue;
    }

    public static boolean isValidUnitForMeasurementType(String unit, String measurementType) {
        if (unit == null || measurementType == null) {
            return false;
        }

        try {
            String normalizedMeasurementType = normalizeMeasurementType(measurementType);
            String normalizedUnit = normalizeUnit(unit);
            return switch (normalizedMeasurementType) {
                case "LengthUnit" -> isLengthUnit(normalizedUnit);
                case "WeightUnit" -> isWeightUnit(normalizedUnit);
                case "VolumeUnit" -> isVolumeUnit(normalizedUnit);
                case "TemperatureUnit" -> isTemperatureUnit(normalizedUnit);
                default -> false;
            };
        } catch (QuantityMeasurementException exception) {
            return false;
        }
    }

    public static String normalizeMeasurementType(String measurementType) {
        if (measurementType == null || measurementType.trim().isEmpty()) {
            throw new QuantityMeasurementException("Measurement type is required.");
        }

        String normalized = measurementType.trim().toUpperCase(Locale.ROOT);
        if (normalized.equals("LENGTH") || normalized.equals("LENGTHUNIT")) {
            return "LengthUnit";
        }
        if (normalized.equals("WEIGHT") || normalized.equals("WEIGHTUNIT")) {
            return "WeightUnit";
        }
        if (normalized.equals("VOLUME") || normalized.equals("VOLUMEUNIT")) {
            return "VolumeUnit";
        }
        if (normalized.equals("TEMPERATURE") || normalized.equals("TEMPERATUREUNIT")) {
            return "TemperatureUnit";
        }

        throw new QuantityMeasurementException("Invalid measurement type: " + measurementType);
    }

    private static void ensureSameMeasurementType(String thisMeasurementType, String thatMeasurementType,
            String operationName) {
        String thisType = normalizeMeasurementType(thisMeasurementType);
        String thatType = normalizeMeasurementType(thatMeasurementType);
        if (!thisType.equals(thatType)) {
            throw new QuantityMeasurementException(operationName + " Error: Cannot perform arithmetic between different "
                    + "measurement categories: " + thisType + " and " + thatType);
        }
    }

    private static String normalizeUnit(String unit) {
        if (unit == null || unit.trim().isEmpty()) {
            throw new QuantityMeasurementException("Unit name is required.");
        }
        return unit.trim().toUpperCase(Locale.ROOT);
    }

    private static double toMeters(double value, String unit) {
        return switch (unit) {
            case "INCH", "INCHES" -> value * 0.0254;
            case "FOOT", "FEET" -> value * 0.3048;
            case "YARD", "YARDS" -> value * 0.9144;
            case "CENTIMETER", "CENTIMETERS", "CM" -> value * 0.01;
            case "METER", "METERS", "M" -> value;
            default -> throw new QuantityMeasurementException("Invalid unit name: " + unit + ".");
        };
    }

    private static double fromMeters(double value, String unit) {
        return switch (unit) {
            case "INCH", "INCHES" -> value / 0.0254;
            case "FOOT", "FEET" -> value / 0.3048;
            case "YARD", "YARDS" -> value / 0.9144;
            case "CENTIMETER", "CENTIMETERS", "CM" -> value / 0.01;
            case "METER", "METERS", "M" -> value;
            default -> throw new QuantityMeasurementException("Invalid unit name: " + unit + ".");
        };
    }

    private static double toKilograms(double value, String unit) {
        return switch (unit) {
            case "GRAM", "GRAMS", "G" -> value * 0.001;
            case "KILOGRAM", "KILOGRAMS", "KG" -> value;
            case "POUND", "POUNDS", "LB", "LBS" -> value * 0.45359237;
            default -> throw new QuantityMeasurementException("Invalid unit name: " + unit + ".");
        };
    }

    private static double fromKilograms(double value, String unit) {
        return switch (unit) {
            case "GRAM", "GRAMS", "G" -> value / 0.001;
            case "KILOGRAM", "KILOGRAMS", "KG" -> value;
            case "POUND", "POUNDS", "LB", "LBS" -> value / 0.45359237;
            default -> throw new QuantityMeasurementException("Invalid unit name: " + unit + ".");
        };
    }

    private static double toLiters(double value, String unit) {
        return switch (unit) {
            case "MILLILITER", "MILLILITERS", "MILLILITRE", "MILLILITRES", "ML" -> value * 0.001;
            case "LITER", "LITERS", "LITRE", "LITRES", "L" -> value;
            case "GALLON", "GALLONS" -> value * 3.78541;
            default -> throw new QuantityMeasurementException("Invalid unit name: " + unit + ".");
        };
    }

    private static double fromLiters(double value, String unit) {
        return switch (unit) {
            case "MILLILITER", "MILLILITERS", "MILLILITRE", "MILLILITRES", "ML" -> value / 0.001;
            case "LITER", "LITERS", "LITRE", "LITRES", "L" -> value;
            case "GALLON", "GALLONS" -> value / 3.78541;
            default -> throw new QuantityMeasurementException("Invalid unit name: " + unit + ".");
        };
    }

    private static double toCelsius(double value, String unit) {
        return switch (unit) {
            case "CELSIUS", "C" -> value;
            case "FAHRENHEIT", "F" -> (value - 32.0) * 5.0 / 9.0;
            case "KELVIN", "K" -> value - 273.15;
            default -> throw new QuantityMeasurementException("Invalid unit name: " + unit + ".");
        };
    }

    private static double fromCelsius(double value, String unit) {
        return switch (unit) {
            case "CELSIUS", "C" -> value;
            case "FAHRENHEIT", "F" -> (value * 9.0 / 5.0) + 32.0;
            case "KELVIN", "K" -> value + 273.15;
            default -> throw new QuantityMeasurementException("Invalid unit name: " + unit + ".");
        };
    }

    private static boolean isLengthUnit(String unit) {
        return switch (unit) {
            case "INCH", "INCHES", "FOOT", "FEET", "YARD", "YARDS",
                    "CENTIMETER", "CENTIMETERS", "CM", "METER", "METERS", "M" -> true;
            default -> false;
        };
    }

    private static boolean isWeightUnit(String unit) {
        return switch (unit) {
            case "GRAM", "GRAMS", "G", "KILOGRAM", "KILOGRAMS", "KG", "POUND", "POUNDS", "LB", "LBS" -> true;
            default -> false;
        };
    }

    private static boolean isVolumeUnit(String unit) {
        return switch (unit) {
            case "MILLILITER", "MILLILITERS", "MILLILITRE", "MILLILITRES", "ML",
                    "LITER", "LITERS", "LITRE", "LITRES", "L", "GALLON", "GALLONS" -> true;
            default -> false;
        };
    }

    private static boolean isTemperatureUnit(String unit) {
        return switch (unit) {
            case "CELSIUS", "C", "FAHRENHEIT", "F", "KELVIN", "K" -> true;
            default -> false;
        };
    }
}
