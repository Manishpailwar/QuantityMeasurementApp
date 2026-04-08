export type SidebarTheme = {
  activeCard: string;
  idleCard: string;
  activeIcon: string;
  idleIcon: string;
  activeChevron: string;
  idleChevron: string;
};

export const cardThemeByName: Record<string, SidebarTheme> = {
  Length: {
    activeCard:
      "border-blue-400/45 bg-linear-to-r from-blue-100 to-sky-100 shadow-[0_10px_24px_rgba(37,99,235,0.18)]",
    idleCard:
      "border-slate-200 bg-white shadow-[0_8px_20px_rgba(15,23,42,0.08)] hover:border-blue-200 hover:bg-blue-50/50",
    activeIcon: "border-blue-400/30 bg-blue-500/15 text-blue-700",
    idleIcon:
      "border-slate-200 bg-slate-100 text-slate-600 group-hover:border-blue-200 group-hover:bg-blue-100 group-hover:text-blue-700",
    activeChevron: "text-blue-700",
    idleChevron: "text-slate-400 group-hover:text-blue-700",
  },
  Weight: {
    activeCard:
      "border-amber-400/45 bg-linear-to-r from-amber-100 to-yellow-100 shadow-[0_10px_24px_rgba(217,119,6,0.18)]",
    idleCard:
      "border-slate-200 bg-white shadow-[0_8px_20px_rgba(15,23,42,0.08)] hover:border-amber-200 hover:bg-amber-50/50",
    activeIcon: "border-amber-400/30 bg-amber-500/15 text-amber-700",
    idleIcon:
      "border-slate-200 bg-slate-100 text-slate-600 group-hover:border-amber-200 group-hover:bg-amber-100 group-hover:text-amber-700",
    activeChevron: "text-amber-700",
    idleChevron: "text-slate-400 group-hover:text-amber-700",
  },
  Volume: {
    activeCard:
      "border-emerald-400/45 bg-linear-to-r from-emerald-100 to-teal-100 shadow-[0_10px_24px_rgba(5,150,105,0.18)]",
    idleCard:
      "border-slate-200 bg-white shadow-[0_8px_20px_rgba(15,23,42,0.08)] hover:border-emerald-200 hover:bg-emerald-50/50",
    activeIcon: "border-emerald-400/30 bg-emerald-500/15 text-emerald-700",
    idleIcon:
      "border-slate-200 bg-slate-100 text-slate-600 group-hover:border-emerald-200 group-hover:bg-emerald-100 group-hover:text-emerald-700",
    activeChevron: "text-emerald-700",
    idleChevron: "text-slate-400 group-hover:text-emerald-700",
  },
  Temperature: {
    activeCard:
      "border-rose-400/45 bg-linear-to-r from-rose-100 to-red-100 shadow-[0_10px_24px_rgba(225,29,72,0.18)]",
    idleCard:
      "border-slate-200 bg-white shadow-[0_8px_20px_rgba(15,23,42,0.08)] hover:border-rose-200 hover:bg-rose-50/50",
    activeIcon: "border-rose-400/30 bg-rose-500/15 text-rose-700",
    idleIcon:
      "border-slate-200 bg-slate-100 text-slate-600 group-hover:border-rose-200 group-hover:bg-rose-100 group-hover:text-rose-700",
    activeChevron: "text-rose-700",
    idleChevron: "text-slate-400 group-hover:text-rose-700",
  },
};
