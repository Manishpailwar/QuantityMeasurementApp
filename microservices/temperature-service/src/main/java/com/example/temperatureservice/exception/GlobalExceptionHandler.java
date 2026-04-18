package com.example.temperatureservice.exception;

import com.example.temperatureservice.dto.TemperatureDTOs.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TemperatureException.class)
    public ResponseEntity<ErrorDTO> handleTemperature(TemperatureException ex) {
        return ResponseEntity.badRequest().body(error(400, ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorDTO> handleUnauthorized(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error(401, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error(500, "An unexpected error occurred."));
    }

    private ErrorDTO error(int status, String message) {
        return ErrorDTO.builder()
                .status(status)
                .message(message)
                .timestamp(Instant.now().toString())
                .build();
    }
}
