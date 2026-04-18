package com.example.authservice.controller;

import com.example.authservice.dto.AuthDTOs.*;
import com.example.authservice.service.UserManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserManagementService userManagementService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@Valid @RequestBody SignupDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userManagementService.signup(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO dto) {
        return ResponseEntity.ok(userManagementService.login(dto));
    }

    /**
     * Internal endpoint called by other microservices to validate a JWT token.
     * Accepts the raw token (without "Bearer " prefix).
     */
    @PostMapping("/validate")
    public ResponseEntity<TokenValidationDTO> validate(@RequestParam String token) {
        return ResponseEntity.ok(userManagementService.validateToken(token));
    }
}
