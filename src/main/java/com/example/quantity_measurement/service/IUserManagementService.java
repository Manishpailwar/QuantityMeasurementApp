package com.example.quantity_measurement.service;

import com.example.quantity_measurement.dto.LoginDTO;
import com.example.quantity_measurement.dto.SignupDTO;
import com.example.quantity_measurement.dto.TokenDTO;
import com.example.quantity_measurement.dto.UserDTO;

public interface IUserManagementService {
    TokenDTO signup(SignupDTO signupDTO);
    TokenDTO login(LoginDTO loginDTO);
    UserDTO getUserDetails();
}
