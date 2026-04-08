export type LoginRequestModel = {
  email: string;
  password: string;
};

export type SignupRequestModel = {
  username: string;
  email: string;
  password: string;
};

export type TokenResponseModel = string | { token: string };

export type UserModel = {
  id: number;
  username: string;
  email: string;
};
