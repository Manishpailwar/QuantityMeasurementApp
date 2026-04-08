import type { LucideIcon } from "lucide-react";

export type MeasurementCardModel = {
  name: string;
  description: string;
  quantities: string[];
  operations: string[];
  route: string;
  Icon: LucideIcon;
};
