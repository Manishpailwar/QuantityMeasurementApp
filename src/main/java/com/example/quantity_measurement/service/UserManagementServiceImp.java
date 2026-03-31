package com.example.quantity_measurement.service;

import com.example.quantity_measurement.dto.LoginDTO;
import com.example.quantity_measurement.dto.SignupDTO;
import com.example.quantity_measurement.dto.TokenDTO;
import com.example.quantity_measurement.dto.UserDTO;
import com.example.quantity_measurement.entity.User;
import com.example.quantity_measurement.exception.InvalidCredentialsException;
import com.example.quantity_measurement.exception.UserAlreadyExistsException;
import com.example.quantity_measurement.exception.UserNotFoundException;
import com.example.quantity_measurement.repository.UserManagementRepository;
import com.example.quantity_measurement.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementServiceImp implements IUserManagementService {

    private final UserManagementRepository userManagementRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public TokenDTO signup(SignupDTO signupDTO) {
        if (userManagementRepository.findByUsername(signupDTO.getUsername()) != null) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        if (userManagementRepository.findByEmail(signupDTO.getEmail()) != null) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        
        userManagementRepository.save(user);
        
        String jwtToken = jwtService.generateToken(user);
        return TokenDTO.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public TokenDTO login(LoginDTO loginDTO) {
        User user = userManagementRepository.findByEmail(loginDTO.getEmail());
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            String jwtToken = jwtService.generateToken(user);
            return TokenDTO.builder()
                    .token(jwtToken)
                    .build();
        }
        throw new InvalidCredentialsException("Invalid password");
    }

    @Override
    public UserDTO getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new InvalidCredentialsException("User not authenticated");
        }
        
        String email = authentication.getName();
        User user = userManagementRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
