import { ChevronRight } from "lucide-react";
import { NavLink } from "react-router-dom";
import { measurementCards } from "../cards/MeasurementCard";
import { cardThemeByName } from "./config/sidebarThemes";




export function SideBar() {
  return (
    <aside className="rounded-2xl border border-slate-300/40 bg-white/75 p-4 shadow-[0_20px_45px_rgba(15,23,42,0.22)] backdrop-blur">
      <h2 className="mb-4 text-xs font-semibold uppercase tracking-[0.14em] text-slate-500">
        Categories
      </h2>

      <div className="space-y-3">
        {measurementCards.map((card) => {
          const theme = cardThemeByName[card.name] ?? cardThemeByName.Length;

          return (
          <NavLink
            key={card.name}
            to={card.route}
            className={({ isActive }) =>
              `group flex items-center justify-between rounded-xl border px-4 py-3.5 transition ${
                isActive
                  ? theme.activeCard
                  : theme.idleCard
              }`
            }
          >
            {({ isActive }) => (
              <>
                <div className="flex items-center gap-3">
                  <span
                    className={`inline-flex h-10 w-10 shrink-0 items-center justify-center rounded-lg border ${
                      isActive
                        ? theme.activeIcon
                        : theme.idleIcon
                    }`}
                  >
                    <card.Icon size={18} strokeWidth={2.2} />
                  </span>

                  <div>
                    <div className="flex items-center gap-2">
                      <h3 className="text-sm font-semibold text-slate-800">{card.name}</h3>
                      <span className="rounded-full bg-slate-100 px-2 py-0.5 text-[10px] font-semibold uppercase tracking-[0.08em] text-slate-500">
                        {card.quantities.length} units
                      </span>
                    </div>
                    <p className="mt-1 text-xs text-slate-500">
                      Quantities: {card.quantities.join(", ")}
                    </p>
                  </div>
                </div>

                <ChevronRight
                  size={16}
                  className={`shrink-0 ${
                    isActive
                      ? theme.activeChevron
                      : theme.idleChevron
                  }`}
                />
              </>
            )}
          </NavLink>
          );
        })}
      </div>
    </aside>
  );
}
