export const APP_ROUTES = {
  home: "/",
  dashboard: "/dashboard",
} as const;

export const DASHBOARD_ROUTES = {
  length: "length",
  weight: "weight",
  volume: "volume",
  temperature: "temperature",
  history: "history",
} as const;

export type AppRouteKey = keyof typeof APP_ROUTES;