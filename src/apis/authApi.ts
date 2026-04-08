import { API_ENDPOINTS, apiClient } from "./apiClient";
import type {
  LoginRequestModel,
  SignupRequestModel,
  TokenResponseModel,
  UserModel,
} from "../types/models/AuthApiModel";

function normalizeTokenResponse(data: unknown): string {
  if (typeof data === "string") {
    return data;
  }

  if (data && typeof data === "object" && "token" in data) {
    const tokenValue = (data as { token?: unknown }).token;
    if (typeof tokenValue === "string") {
      return tokenValue;
    }
  }

  throw new Error("Invalid auth response: token not found");
}

export async function login(payload: LoginRequestModel): Promise<string> {
  const response = await apiClient.post<TokenResponseModel>(API_ENDPOINTS.auth.login, payload);
  return normalizeTokenResponse(response.data);
}

export async function signup(payload: SignupRequestModel): Promise<string> {
  const response = await apiClient.post<TokenResponseModel>(API_ENDPOINTS.auth.signup, payload);
  return normalizeTokenResponse(response.data);
}

export async function getCurrentUser(): Promise<UserModel> {
  const response = await apiClient.get<UserModel>(API_ENDPOINTS.auth.me);
  return response.data;
}
