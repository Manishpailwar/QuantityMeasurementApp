import axios, { AxiosError } from "axios";
import { clearAccessToken, getAccessToken } from "../utils/auth";



const apiBaseUrl =(import.meta.env.VITE_API_BASE_URL as string | undefined) ?? "http://localhost:8080";

export const apiClient = axios.create({
  baseURL: apiBaseUrl,
  timeout: 15000,
  withCredentials: false,
  headers: {
    "Content-Type": "application/json",
  },
});
export const API_ENDPOINTS = {
  auth: {
    login: "/api/auth/login",
    signup: "/api/auth/signup",
    me: "/api/auth/me",
  },
  quantity: {
    convert: "/api/quantity/convert",
    compare: "/api/quantity/compare",
    add: "/api/quantity/add",
    subtract: "/api/quantity/subtract",
    multiply: "/api/quantity/multiply",
    divide: "/api/quantity/divide",
    operate: "/api/quantity/operate",
    history: "/api/quantity/history",
    historyByOperation: (operationType: string) => `/api/quantity/history/operation/${operationType}`,
  },
} as const;

apiClient.interceptors.request.use((config) => {
  const token = getAccessToken();

  if (token && !config.headers.Authorization) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
});

apiClient.interceptors.response.use(
  (response) => response,
  (error: AxiosError) => {
    if (error.response?.status === 401) {
      clearAccessToken();
    }

    return Promise.reject(error);
  }
);
