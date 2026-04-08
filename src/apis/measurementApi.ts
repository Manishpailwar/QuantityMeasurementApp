import { API_ENDPOINTS, apiClient } from "./apiClient";
import type {
  BinaryOperationRequestDtoModel,
  ConversionRequestDtoModel,
  MeasurementApiPayloadModel,
  OperationRequestDtoModel,
  QuantityMeasurementHistoryItemModel,
  QuantityModelPayloadModel,
  QuantityOperationResultDtoModel,
} from "../types/models/MeasurementApiModel";

function buildQuantityModel(value: number, measurementType: string, unitName: string): QuantityModelPayloadModel {
  return { value, measurementType, unitName };
}

function parseComparisonResult(value: unknown): boolean | null {
  if (typeof value === "boolean") {
    return value;
  }

  if (typeof value === "string") {
    const normalized = value.trim().toLowerCase();
    if (normalized === "true") {
      return true;
    }
    if (normalized === "false") {
      return false;
    }
  }

  return null;
}

function parseResultOperandValue(value: unknown): number | null {
  if (typeof value === "number" && Number.isFinite(value)) {
    return value;
  }

  if (typeof value === "string") {
    const parsed = Number(value.trim());
    if (Number.isFinite(parsed)) {
      return parsed;
    }
  }

  return null;
}

function readString(value: unknown): string | null {
  if (typeof value === "string") {
    const trimmed = value.trim();
    return trimmed.length > 0 ? trimmed : null;
  }

  return null;
}

function formatResult(data: QuantityOperationResultDtoModel | string | number | boolean | null | undefined): string {
  if (typeof data === "string") {
    const text = readString(data);
    return text ?? "Operation failed.";
  }

  if (typeof data === "number") {
    return String(data);
  }

  if (typeof data === "boolean") {
    return data ? "true" : "false";
  }

  if (!data || typeof data !== "object") {
    return "Operation failed.";
  }

  const payload = data as Record<string, unknown>;

  const errorMessage = readString(payload.errorMessage) ?? readString(payload.message);
  if (errorMessage) {
    return errorMessage;
  }

  const comparisonResult = parseComparisonResult(payload.comparisonResult);
  if (comparisonResult !== null) {
    return comparisonResult ? "true" : "false";
  }

  const nestedResult =
    payload.resultQuantity && typeof payload.resultQuantity === "object"
      ? (payload.resultQuantity as Record<string, unknown>)
      : null;

  const resultOperandValue = parseResultOperandValue(
    payload.resultOperandValue ?? payload.resultValue ?? payload.value ?? nestedResult?.value
  );
  if (resultOperandValue !== null) {
    const unit =
      readString(payload.resultUnit) ??
      readString(payload.unit) ??
      readString(nestedResult?.unitName) ??
      readString(nestedResult?.unit);
    const unitLabel = unit ? ` ${unit}` : "";
    return `${resultOperandValue}${unitLabel}`;
  }

  const plainResultText = readString(payload.result);
  if (plainResultText) {
    return plainResultText;
  }

  if (payload.successful === false) {
    return "Operation failed.";
  }

  return "No result returned by API.";
}

export async function computeMeasurement(payload: MeasurementApiPayloadModel): Promise<string> {
  const operation = payload.operation.trim().toLowerCase();
  const firstQuantity = buildQuantityModel(payload.value1, payload.quantityType, payload.unit1);

  if (operation === "convert") {
    const requestBody: ConversionRequestDtoModel = {
      sourceQuantity: firstQuantity,
      targetUnit: payload.unit2 ?? payload.unit1,
    };

    const response = await apiClient.post<QuantityOperationResultDtoModel>(API_ENDPOINTS.quantity.convert, requestBody);
    return formatResult(response.data);
  }

  const hasSecondOperand = typeof payload.value2 === "number" && typeof payload.unit2 === "string";
  if (!hasSecondOperand) {
    throw new Error("Second quantity is required for this operation");
  }

  const secondQuantity = buildQuantityModel(payload.value2!, payload.quantityType, payload.unit2!);

  if (operation === "compare") {
    const requestBody: BinaryOperationRequestDtoModel = {
      firstQuantity,
      secondQuantity,
    };

    const response = await apiClient.post<QuantityOperationResultDtoModel>(API_ENDPOINTS.quantity.compare, requestBody);
    return formatResult(response.data);
  }

  if (["add", "subtract", "multiply", "divide"].includes(operation)) {
    const requestBody: BinaryOperationRequestDtoModel = {
      firstQuantity,
      secondQuantity,
      resultUnit: payload.unit2,
    };

    const endpoint =
      operation === "add"
        ? API_ENDPOINTS.quantity.add
        : operation === "subtract"
          ? API_ENDPOINTS.quantity.subtract
          : operation === "multiply"
            ? API_ENDPOINTS.quantity.multiply
            : API_ENDPOINTS.quantity.divide;
    const response = await apiClient.post<QuantityOperationResultDtoModel>(endpoint, requestBody);
    return formatResult(response.data);
  }

  const requestBody: OperationRequestDtoModel = {
    operationType: operation.toUpperCase(),
    firstQuantity,
    secondQuantity,
    resultUnit: payload.unit2,
    targetUnit: payload.unit2,
  };

  const response = await apiClient.post<QuantityOperationResultDtoModel>(API_ENDPOINTS.quantity.operate, requestBody);
  return formatResult(response.data);
}

export async function getMeasurementHistory(): Promise<QuantityMeasurementHistoryItemModel[]> {
  const response = await apiClient.get<QuantityMeasurementHistoryItemModel[]>(API_ENDPOINTS.quantity.history);
  return response.data;
}

export async function getMeasurementHistoryByOperation(
  operationType: string
): Promise<QuantityMeasurementHistoryItemModel[]> {
  const response = await apiClient.get<QuantityMeasurementHistoryItemModel[]>(
    API_ENDPOINTS.quantity.historyByOperation(operationType)
  );
  return response.data;
}