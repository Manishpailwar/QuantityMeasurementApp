import { useEffect, useState } from "react";
import { getMeasurementHistory } from "../apis/measurementApi";
import type { QuantityMeasurementHistoryItemModel } from "../types/models/MeasurementApiModel";

function HistoryPage() {
  const [historyItems, setHistoryItems] = useState<QuantityMeasurementHistoryItemModel[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [errorMessage, setErrorMessage] = useState<string>("");
  const [selectedItem, setSelectedItem] = useState<QuantityMeasurementHistoryItemModel | null>(null);

  useEffect(() => {
    const loadHistory = async () => {
      setIsLoading(true);
      setErrorMessage("");

      try {
        const data = await getMeasurementHistory();
        setHistoryItems(data);
      } catch {
        setErrorMessage("Unable to load history right now.");
      } finally {
        setIsLoading(false);
      }
    };

    void loadHistory();
  }, []);

  return (
    <section className="rounded-2xl border border-slate-300/40 bg-white/75 p-6 text-slate-700 shadow-[0_20px_45px_rgba(15,23,42,0.22)] backdrop-blur">
      <h2 className="text-2xl font-semibold text-slate-800">History</h2>
      <p className="mt-2 text-sm text-slate-600">Recent quantity operations for your account.</p>

      {isLoading && <p className="mt-4 text-sm text-slate-600">Loading history...</p>}

      {!isLoading && errorMessage && (
        <p className="mt-4 rounded-lg border border-rose-200 bg-rose-50 px-3 py-2 text-sm text-rose-700">
          {errorMessage}
        </p>
      )}

      {!isLoading && !errorMessage && historyItems.length === 0 && (
        <p className="mt-4 text-sm text-slate-600">No history found.</p>
      )}

      {!isLoading && !errorMessage && historyItems.length > 0 && (
        <div className="mt-4 overflow-x-auto rounded-xl border border-slate-200">
          <table className="min-w-full divide-y divide-slate-200 text-sm">
            <thead className="bg-slate-50">
              <tr>
                <th className="px-3 py-2 text-left font-semibold text-slate-700">Operation</th>
                <th className="px-3 py-2 text-left font-semibold text-slate-700">First Quantity</th>
                <th className="px-3 py-2 text-left font-semibold text-slate-700">Second Quantity</th>
                <th className="px-3 py-2 text-left font-semibold text-slate-700">Result</th>
                <th className="px-3 py-2 text-left font-semibold text-slate-700">Action</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-100 bg-white">
              {historyItems.map((item) => (
                <tr key={item.id}>
                  <td className="px-3 py-2 capitalize">{item.operationType}</td>
                  <td className="px-3 py-2">
                    {typeof item.firstOperandValue === "number"
                      ? `${item.firstOperandValue}${item.firstUnit ? ` ${item.firstUnit}` : ""}`
                      : "-"}
                  </td>
                  <td className="px-3 py-2">
                    {typeof item.secondOperandValue === "number"
                      ? `${item.secondOperandValue}${item.secondUnit ? ` ${item.secondUnit}` : ""}`
                      : "-"}
                  </td>
                  <td className="px-3 py-2">
                    {typeof item.resultOperandValue === "number"
                      ? `${item.resultOperandValue}${item.resultUnit ? ` ${item.resultUnit}` : ""}`
                      : item.comparisonResult === true
                        ? "true"
                        : item.comparisonResult === false
                          ? "false"
                          : "-"}
                  </td>
                  <td className="px-3 py-2">
                    <button
                      type="button"
                      onClick={() => setSelectedItem(item)}
                      className="rounded-md border border-slate-300 bg-white px-2.5 py-1 text-xs font-semibold text-slate-700 transition hover:bg-slate-100"
                    >
                      View Details
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {selectedItem && (
        <div className="mt-4 rounded-xl border border-slate-200 bg-slate-50 p-4">
          <div className="mb-3 flex items-center justify-between">
            <h3 className="text-base font-semibold text-slate-800">Operation Details</h3>
            <button
              type="button"
              onClick={() => setSelectedItem(null)}
              className="rounded-md border border-slate-300 bg-white px-2.5 py-1 text-xs font-semibold text-slate-700 transition hover:bg-slate-100"
            >
              Close
            </button>
          </div>

          {(() => {
            const operationType = selectedItem.operationType.toLowerCase();
            const isCompare = operationType === "compare";
            const isConvert = operationType === "convert";

            return (
          <div className="grid gap-2 text-sm text-slate-700 md:grid-cols-2">
            <p><span className="font-semibold">Operation:</span> {selectedItem.operationType}</p>
            <p><span className="font-semibold">First Value:</span> {selectedItem.firstOperandValue ?? "-"}</p>
            <p><span className="font-semibold">First Type:</span> {selectedItem.firstMeasurementType ?? "-"}</p>
            <p><span className="font-semibold">First Unit:</span> {selectedItem.firstUnit ?? "-"}</p>
            {!isConvert && <p><span className="font-semibold">Second Value:</span> {selectedItem.secondOperandValue ?? "-"}</p>}
            {!isConvert && <p><span className="font-semibold">Second Type:</span> {selectedItem.secondMeasurementType ?? "-"}</p>}
            {!isConvert && <p><span className="font-semibold">Second Unit:</span> {selectedItem.secondUnit ?? "-"}</p>}
            {!isCompare && <p><span className="font-semibold">Result Value:</span> {selectedItem.resultOperandValue ?? "-"}</p>}
            {!isCompare && <p><span className="font-semibold">Result Type:</span> {selectedItem.resultMeasurementType ?? "-"}</p>}
            {!isCompare && <p><span className="font-semibold">Result Unit:</span> {selectedItem.resultUnit ?? "-"}</p>}
            {isCompare && (
              <p><span className="font-semibold">Comparison Result:</span> {typeof selectedItem.comparisonResult === "boolean" ? String(selectedItem.comparisonResult) : "-"}</p>
            )}
          </div>
            );
          })()}
        </div>
      )}
    </section>
  );
}

export default HistoryPage;
