import { useState } from "react";
import type { OperationCardProps } from "../../types/props/OperationCardProps";
import { computeMeasurement } from "../../apis/measurementApi";

export function OtherOperationsCard({ quantities, operation, quantityType }: OperationCardProps) {
  const [firstValue, setFirstValue] = useState<string>("");
  const [secondValue, setSecondValue] = useState<string>("");
  const [firstUnit, setFirstUnit] = useState<string>(quantities[0] ?? "");
  const [secondUnit, setSecondUnit] = useState<string>(quantities[1] ?? quantities[0] ?? "");
  const [resultText, setResultText] = useState<string>("");
  const [isComputing, setIsComputing] = useState<boolean>(false);

  const handleReset = () => {
    setFirstValue("");
    setSecondValue("");
    setFirstUnit(quantities[0] ?? "");
    setSecondUnit(quantities[1] ?? quantities[0] ?? "");
    setResultText("");
  };

  const handleCompute = async () => {
    const firstNumber = Number(firstValue);
    const secondNumber = Number(secondValue);
    const selectedOperation = operation ?? "compare";

    if (firstValue.trim() === "" || secondValue.trim() === "") {
      setResultText("Please enter both quantity values before computing.");
      return;
    }

    if (Number.isNaN(firstNumber) || Number.isNaN(secondNumber)) {
      setResultText("Please provide valid numeric values.");
      return;
    }

    setIsComputing(true);
    try {
      const apiResult = await computeMeasurement({
        operation: selectedOperation,
        quantityType,
        value1: firstNumber,
        unit1: firstUnit,
        value2: secondNumber,
        unit2: secondUnit,
      });
      setResultText(apiResult);
    } catch {
      setResultText("Unable to compute right now. Please check API connection and try again.");
    } finally {
      setIsComputing(false);
    }
  };

  return (
    <section>
      <div className="mt-3 grid gap-3 md:grid-cols-2">
        <div className="rounded-xl border border-slate-200 bg-slate-50 p-3">
          <label className="text-xs text-slate-600">
            Quantity 1
            <input
              type="number"
              value={firstValue}
              onChange={(event) => setFirstValue(event.target.value)}
              placeholder="Enter first value"
              className="mt-1 w-full rounded-lg border border-slate-300 bg-white px-3 py-2 text-slate-800 outline-none focus:border-cyan-400/60"
            />
          </label>

          <label className="mt-3 block text-xs text-slate-600">
            Quantity Type 1
            <select
              value={firstUnit}
              onChange={(event) => setFirstUnit(event.target.value)}
              className="mt-1 w-full rounded-xl border border-slate-300 bg-white px-3 py-2 text-slate-800 outline-none focus:border-cyan-400/60"
            >
              {quantities.map((unit) => (
                <option key={`one-${unit}`} value={unit}>
                  {unit}
                </option>
              ))}
            </select>
          </label>
        </div>

        <div className="rounded-xl border border-slate-200 bg-slate-50 p-3">
          <label className="text-xs text-slate-600">
            Quantity 2
            <input
              type="number"
              value={secondValue}
              onChange={(event) => setSecondValue(event.target.value)}
              placeholder="Enter second value"
              className="mt-1 w-full rounded-lg border border-slate-300 bg-white px-3 py-2 text-slate-800 outline-none focus:border-cyan-400/60"
            />
          </label>

          <label className="mt-3 block text-xs text-slate-600">
            Quantity Type 2
            <select
              value={secondUnit}
              onChange={(event) => setSecondUnit(event.target.value)}
              className="mt-1 w-full rounded-xl border border-slate-300 bg-white px-3 py-2 text-slate-800 outline-none focus:border-cyan-400/60"
            >
              {quantities.map((unit) => (
                <option key={`two-${unit}`} value={unit}>
                  {unit}
                </option>
              ))}
            </select>
          </label>
        </div>
      </div>

      <div className="mt-4 flex items-center gap-2">
        <button
          type="button"
          onClick={handleCompute}
          disabled={isComputing}
          className="rounded-lg bg-slate-900 px-4 py-2 text-sm font-semibold text-white transition hover:bg-slate-700 disabled:cursor-not-allowed disabled:bg-slate-500"
        >
          {isComputing ? "Computing..." : "Compute"}
        </button>
        <button
          type="button"
          onClick={handleReset}
          disabled={isComputing}
          className="rounded-lg border border-slate-300 bg-white px-4 py-2 text-sm font-semibold text-slate-700 transition hover:bg-slate-100 disabled:cursor-not-allowed disabled:opacity-60"
        >
          Reset
        </button>
      </div>

      {resultText && (
        <div className="mt-3 rounded-xl border border-slate-200 bg-slate-50 p-3 text-sm text-slate-700">
          {resultText}
        </div>
      )}
    </section>
  );
}
