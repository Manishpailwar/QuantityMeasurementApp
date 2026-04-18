package com.example.lengthservice.controller;

import com.example.lengthservice.dto.LengthDTOs.*;
import com.example.lengthservice.security.AuthClient;
import com.example.lengthservice.service.LengthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/length")
@RequiredArgsConstructor
public class LengthController {

    private final LengthService lengthService;
    private final AuthClient authClient;

    @PostMapping("/convert")
    public ResponseEntity<OperationResultDTO> convert(
            @RequestHeader("Authorization") String auth,
            @Valid @RequestBody ConversionRequestDTO request) {
        Long userId = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(lengthService.convert(request.getSourceQuantity(), request.getTargetUnit(), userId));
    }

    @PostMapping("/compare")
    public ResponseEntity<OperationResultDTO> compare(
            @RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO request) {
        Long userId = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(lengthService.compare(request.getFirstQuantity(), request.getSecondQuantity(), userId));
    }

    @PostMapping("/add")
    public ResponseEntity<OperationResultDTO> add(
            @RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO request) {
        Long userId = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(lengthService.add(request.getFirstQuantity(), request.getSecondQuantity(), request.getResultUnit(), userId));
    }

    @PostMapping("/subtract")
    public ResponseEntity<OperationResultDTO> subtract(
            @RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO request) {
        Long userId = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(lengthService.subtract(request.getFirstQuantity(), request.getSecondQuantity(), request.getResultUnit(), userId));
    }

    @PostMapping("/multiply")
    public ResponseEntity<OperationResultDTO> multiply(
            @RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO request) {
        Long userId = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(lengthService.multiply(request.getFirstQuantity(), request.getSecondQuantity(), request.getResultUnit(), userId));
    }

    @PostMapping("/divide")
    public ResponseEntity<OperationResultDTO> divide(
            @RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO request) {
        Long userId = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(lengthService.divide(request.getFirstQuantity(), request.getSecondQuantity(), request.getResultUnit(), userId));
    }

    @GetMapping("/history")
    public ResponseEntity<List<OperationResultDTO>> history(
            @RequestHeader("Authorization") String auth) {
        Long userId = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(lengthService.getHistory(userId));
    }

    @GetMapping("/history/{operationType}")
    public ResponseEntity<List<OperationResultDTO>> historyByOp(
            @RequestHeader("Authorization") String auth,
            @PathVariable String operationType) {
        Long userId = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(lengthService.getHistoryByOperation(userId, operationType));
    }
}
