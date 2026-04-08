import { useState } from "react";
import type { OperationCardProps } from "../../types/props/OperationCardProps";
import { computeMeasurement } from "../../apis/measurementApi";

export function ConvertOperationCard({ quantities, operation, quantityType }: OperationCardProps) {
  const [value, setValue] = useState<string>("");
  const [fromUnit, setFromUnit] = useState<string>(quantities[0] ?? "");
  const [toUnit, setToUnit] = useState<string>(quantities[1] ?? quantities[0] ?? "");
  const [resultText, setResultText] = useState<string>("");
  const [isComputing, setIsComputing] = useState<boolean>(false);

  const handleReset = () => {
    setValue("");
    setFromUnit(quantities[0] ?? "");
    setToUnit(quantities[1] ?? quantities[0] ?? "");
    setResultText("");
  };

  const handleCompute = async () => {
    const numericValue = Number(value);
    const selectedOperation = operation ?? "convert";

    if (value.trim() === "" || Number.isNaN(numericValue)) {
      setResultText("Please enter a valid quantity before computing.");
      return;
    }

    setIsComputing(true);
    try {
      const apiResult = await computeMeasurement({
        operation: selectedOperation,
        quantityType,
        value1: numericValue,
        unit1: fromUnit,
        unit2: toUnit,
      });
      setResultText(apiResult);
    } catch {
      setResultText("Unable to compute right now. Please check API connection and try again.");
    } finally {
      setIsComputing(false);
    }
  };

  return (
    <section className="mt-5 rounded-2xl border border-slate-200 bg-white p-4 shadow-[0_8px_20px_rgba(15,23,42,0.08)]">
      <h4 className="text-sm font-semibold uppercase tracking-wider text-slate-600">Convert</h4>
      <div className="mt-3 grid gap-3 md:grid-cols-3">
        <label className="text-xs text-slate-600">
          Quantity
          <input
            type="number"
            value={value}
            onChange={(event) => setValue(event.target.value)}
            placeholder="Enter value"
            className="mt-1 w-full rounded-lg border border-slate-300 bg-white px-3 py-2 text-slate-800 outline-none focus:border-cyan-400/60"
          />
        </label>

        <label className="text-xs text-slate-600">
          From Type
          <select
            value={fromUnit}
            onChange={(event) => setFromUnit(event.target.value)}
            className="mt-1 w-full rounded-xl border border-slate-300 bg-white px-3 py-2 text-slate-800 outline-none focus:border-cyan-400/60"
          >
            {quantities.map((unit) => (
              <option key={`from-${unit}`} value={unit}>
                {unit}
              </option>
            ))}
          </select>
        </label>

        <label className="text-xs text-slate-600">
          To Type
          <select
            value={toUnit}
            onChange={(event) => setToUnit(event.target.value)}
            className="mt-1 w-full rounded-xl border border-slate-300 bg-white px-3 py-2 text-slate-800 outline-none focus:border-cyan-400/60"
          >
            {quantities.map((unit) => (
              <option key={`to-${unit}`} value={unit}>
                {unit}
              </option>
            ))}
          </select>
        </label>
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
