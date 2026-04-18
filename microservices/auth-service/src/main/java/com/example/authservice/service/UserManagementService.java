package com.example.authservice.service;

import com.example.authservice.dto.AuthDTOs.*;
import com.example.authservice.entity.User;
import com.example.authservice.exception.InvalidCredentialsException;
import com.example.authservice.exception.UserAlreadyExistsException;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public UserDTO signup(SignupDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered: " + dto.getEmail());
        }
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        user = userRepository.save(user);
        return toDTO(user);
    }

    public TokenDTO login(LoginDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password."));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password.");
        }
        String token = jwtService.generateToken(user);
        return TokenDTO.builder().token(token).build();
    }

    public TokenValidationDTO validateToken(String token) {
        boolean valid = jwtService.validateToken(token);
        if (!valid) {
            return TokenValidationDTO.builder().valid(false).build();
        }
        return TokenValidationDTO.builder()
                .valid(true)
                .userId(jwtService.extractId(token))
                .email(jwtService.extractEmail(token))
                .username(jwtService.extractUsername(token))
                .build();
    }

    private UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
