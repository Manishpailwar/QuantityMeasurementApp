package com.example.quantity_measurement.controller;

import com.example.quantity_measurement.dto.LoginDTO;
import com.example.quantity_measurement.dto.SignupDTO;
import com.example.quantity_measurement.dto.TokenDTO;
import com.example.quantity_measurement.dto.UserDTO;
import com.example.quantity_measurement.service.IUserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserManagementController {

    private final IUserManagementService userManagementService;

    @PostMapping("/signup")
    public ResponseEntity<TokenDTO> signup(@RequestBody SignupDTO signupDTO) {
        TokenDTO token = userManagementService.signup(signupDTO);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        TokenDTO token = userManagementService.login(loginDTO);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserDetails() {
        UserDTO userDTO = userManagementService.getUserDetails();
        return ResponseEntity.ok(userDTO);
    }
}
