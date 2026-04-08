import { createContext, useContext, useMemo, useState } from "react";
import type {
  AuthContextValueModel,
  AuthProviderPropsModel,
  AuthStateModel,
} from "../types/models/AuthContextValueModel";
import { clearAccessToken, getAccessToken, getAuthIdentityFromToken, setAccessToken } from "../utils/auth";

const AuthContext = createContext<AuthContextValueModel | undefined>(undefined);

function createAuthState(token: string | null): AuthStateModel {
  if (!token) {
    return {
      accessToken: null,
      userId: null,
      username: null,
    };
  }

  const identity = getAuthIdentityFromToken(token);

  return {
    accessToken: token,
    userId: identity.userId,
    username: identity.username,
  };
}

export function AuthProvider({ children }: AuthProviderPropsModel) {
  const [authState, setAuthState] = useState<AuthStateModel>(() => createAuthState(getAccessToken()));

  const authenticateWithToken = (token: string) => {
    setAccessToken(token);
    setAuthState(createAuthState(token));
  };

  const logout = () => {
    clearAccessToken();
    setAuthState(createAuthState(null));
  };

  const value = useMemo<AuthContextValueModel>(
    () => ({
      ...authState,
      isAuthenticated: Boolean(authState.accessToken),
      authenticateWithToken,
      logout,
    }),
    [authState]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within AuthProvider");
  }

  return context;
}
