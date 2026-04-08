import type { ReactNode } from "react";
import type { AuthIdentityModel } from "./AuthIdentityModel";

export type AuthStateModel = {
  accessToken: string | null;
} & AuthIdentityModel;

export type AuthContextValueModel = AuthStateModel & {
  isAuthenticated: boolean;
  authenticateWithToken: (token: string) => void;
  logout: () => void;
};

export type AuthProviderPropsModel = {
  children: ReactNode;
};
