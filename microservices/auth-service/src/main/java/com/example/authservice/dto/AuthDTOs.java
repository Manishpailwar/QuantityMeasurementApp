package com.example.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class AuthDTOs {

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SignupDTO {
        @NotBlank private String username;
        @Email @NotBlank private String email;
        @NotBlank private String password;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class LoginDTO {
        @Email @NotBlank private String email;
        @NotBlank private String password;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class TokenDTO {
        private String token;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UserDTO {
        private Long id;
        private String username;
        private String email;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class TokenValidationDTO {
        private boolean valid;
        private Long userId;
        private String email;
        private String username;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ErrorDTO {
        private int status;
        private String message;
        private String timestamp;
    }
}
