export type MeasurementApiPayloadModel = {
  operation: string;
  quantityType: string;
  value1: number;
  unit1: string;
  value2?: number;
  unit2?: string;
};

export type QuantityModelPayloadModel = {
  value: number;
  measurementType: string;
  unitName: string;
};

export type ConversionRequestDtoModel = {
  sourceQuantity: QuantityModelPayloadModel;
  targetUnit: string;
};

export type BinaryOperationRequestDtoModel = {
  firstQuantity: QuantityModelPayloadModel;
  secondQuantity: QuantityModelPayloadModel;
  resultUnit?: string;
};

export type OperationRequestDtoModel = {
  operationType: string;
  firstQuantity: QuantityModelPayloadModel;
  secondQuantity?: QuantityModelPayloadModel;
  resultUnit?: string;
  targetUnit?: string;
};

export type QuantityOperationResultDtoModel = {
  resultOperandValue?: number | string;
  resultMeasurementType?: string;
  resultUnit?: string;
  comparisonResult?: boolean | string;
  successful?: boolean;
  errorMessage?: string;
};

export type QuantityMeasurementHistoryItemModel = {
  id: string;
  operationType: string;
  firstOperandValue?: number;
  firstMeasurementType?: string;
  firstUnit?: string;
  secondOperandValue?: number;
  secondMeasurementType?: string;
  secondUnit?: string;
  resultOperandValue?: number | string;
  resultMeasurementType?: string;
  resultUnit?: string;
  comparisonResult?: boolean | string;
  errorMessage?: string;
  successful: boolean;
  createdAt: string;
};
