import { useNavigate, useParams } from "react-router-dom";
import type { CategoryPanelProps } from "../../types/props/CategoryPanelProps";
import { ConvertOperationCard } from "../cards/ConvertOperationCard";
import { OtherOperationsCard } from "../cards/OtherOperationsCard";

export function CategoryPanel({
  categoryRoute,
  title,
  description,
  quantities,
  operations,
}: CategoryPanelProps) {
  const navigate = useNavigate();
  const operation = useParams().operation;
  const selectedOperation = operation && operations.includes(operation) ? operation : operations[0];
  const isConvert = selectedOperation === "convert";

  return (
    <section className="flex h-160 flex-col overflow-hidden rounded-2xl border border-slate-300/40 bg-white/75 p-6 text-slate-700 shadow-[0_20px_45px_rgba(15,23,42,0.22)] backdrop-blur">
      <h2 className="text-2xl font-semibold text-slate-800">{title} Converter</h2>
      <p className="mt-2 text-sm text-slate-600">{description}</p>

      <h3 className="mt-6 text-sm font-semibold uppercase tracking-widest text-slate-500">
        Select Operation
      </h3>

      <select
        value={selectedOperation}
        onChange={(event) =>
          navigate(`/dashboard/${categoryRoute}/${event.target.value}`)
        }
        className="mt-3 w-full rounded-xl border border-slate-300 bg-white px-3 py-2 text-sm font-semibold capitalize text-slate-700 outline-none focus:border-cyan-400/60"
      >
        {operations.map((item) => (
          <option key={item} value={item} className="capitalize">
            {item}
          </option>
        ))}
      </select>

      <div className="mt-5 flex-1 overflow-y-auto pr-1">
        <div className={isConvert ? "block" : "hidden"}>
          <ConvertOperationCard
            quantities={quantities}
            operation={selectedOperation}
            quantityType={categoryRoute}
          />
        </div>
        <div className={isConvert ? "hidden" : "block"}>
          <OtherOperationsCard
            quantities={quantities}
            operation={selectedOperation}
            quantityType={categoryRoute}
          />
        </div>
      </div>
    </section>
  );
}
