import type { AuthIdentityModel, JwtClaimsModel } from "../types/models/AuthIdentityModel";

const ACCESS_TOKEN_STORAGE_KEY = "accessToken";

let inMemoryAccessToken: string | null = null;

export function decodeJwtPayload(token: string): JwtClaimsModel | null {
  try {
    const parts = token.split(".");
    if (parts.length < 2) {
      return null;
    }

    const base64Url = parts[1];
    const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
    const padded = base64.padEnd(Math.ceil(base64.length / 4) * 4, "=");
    return JSON.parse(atob(padded)) as JwtClaimsModel;
  } catch {
    return null;
  }
}

export function getAuthIdentityFromToken(token: string): AuthIdentityModel {
  const payload = decodeJwtPayload(token);
  const idClaim = payload?.id;
  const usernameClaim = payload?.username;

  return {
    userId: typeof idClaim === "number" ? idClaim : null,
    username: typeof usernameClaim === "string" ? usernameClaim : null,
  };
}

function isTokenExpired(token: string): boolean {
  const payload = decodeJwtPayload(token);
  const exp = payload?.exp;

  if (typeof exp !== "number") {
    return false;
  }

  const nowInSeconds = Math.floor(Date.now() / 1000);
  return exp <= nowInSeconds;
}

export function setAccessToken(token: string) {
  inMemoryAccessToken = token;
  sessionStorage.setItem(ACCESS_TOKEN_STORAGE_KEY, token);
}

export function getAccessToken() {
  if (!inMemoryAccessToken) {
    const tokenFromStorage = sessionStorage.getItem(ACCESS_TOKEN_STORAGE_KEY);
    if (!tokenFromStorage) {
      return null;
    }

    inMemoryAccessToken = tokenFromStorage;
  }

  if (isTokenExpired(inMemoryAccessToken)) {
    clearAccessToken();
    return null;
  }

  return inMemoryAccessToken;
}

export function clearAccessToken() {
  inMemoryAccessToken = null;
  sessionStorage.removeItem(ACCESS_TOKEN_STORAGE_KEY);
}

export function isAuthenticated() {
  return Boolean(getAccessToken());
}
