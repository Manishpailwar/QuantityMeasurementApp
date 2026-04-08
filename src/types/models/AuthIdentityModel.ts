export type AuthIdentityModel = {
  userId: number | null;
  username: string | null;
};

export type JwtClaimsModel = {
  id?: unknown;
  username?: unknown;
  exp?: unknown;
};
