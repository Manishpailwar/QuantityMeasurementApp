import { Beaker, Ruler, Thermometer, Weight } from "lucide-react";
import { DASHBOARD_ROUTES } from "../../routes";
import type { MeasurementCardModel } from "../../types/models/MeasurementCardModel";

const standardOperations = ["compare", "add", "subtract", "multiply", "divide", "convert"];
const temperatureOperations = ["compare", "add", "subtract", "convert"];

export const measurementCards: MeasurementCardModel[] = [
  {
    name: "Length",
    description:
      "Convert values between common distance units used in daily and engineering calculations.",
    quantities: ["inch", "foot", "yard", "centimeter", "meter"],
    operations: standardOperations,
    route: DASHBOARD_ROUTES.length,
    Icon: Ruler,
  },
  {
    name: "Weight",
    description: "Convert mass and weight values quickly with accurate metric and imperial units.",
    quantities: ["kilogram", "gram", "pound"],
    operations: standardOperations,
    route: DASHBOARD_ROUTES.weight,
    Icon: Weight,
  },
  {
    name: "Volume",
    description:
      "Switch between liquid measurement units for kitchen, lab, and industrial use cases.",
    quantities: ["milliliter", "liter", "gallon"],
    operations: standardOperations,
    route: DASHBOARD_ROUTES.volume,
    Icon: Beaker,
  },
  {
    name: "Temperature",
    description: "Compare and convert thermal values across the most used temperature scales.",
    quantities: ["Celsius", "Fahrenheit", "Kelvin"],
    operations: temperatureOperations,
    route: DASHBOARD_ROUTES.temperature,
    Icon: Thermometer,
  },
];