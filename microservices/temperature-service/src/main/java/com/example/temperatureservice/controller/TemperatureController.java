package com.example.temperatureservice.controller;

import com.example.temperatureservice.dto.TemperatureDTOs.*;
import com.example.temperatureservice.security.AuthClient;
import com.example.temperatureservice.service.TemperatureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temperature")
@RequiredArgsConstructor
public class TemperatureController {

    private final TemperatureService temperatureService;
    private final AuthClient authClient;

    @PostMapping("/convert")
    public ResponseEntity<OperationResultDTO> convert(
            @RequestHeader("Authorization") String auth,
            @Valid @RequestBody ConversionRequestDTO request) {
        Long userId = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(
                temperatureService.convert(request.getSourceQuantity(), request.getTargetUnit(), userId));
    }

    @PostMapping("/compare")
    public ResponseEntity<OperationResultDTO> compare(
            @RequestHeader("Authorization") String auth,
            @Valid @RequestBody CompareRequestDTO request) {
        Long userId = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(
                temperatureService.compare(request.getFirstQuantity(), request.getSecondQuantity(), userId));
    }

    @GetMapping("/history")
    public ResponseEntity<List<OperationResultDTO>> history(
            @RequestHeader("Authorization") String auth) {
        Long userId = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(temperatureService.getHistory(userId));
    }

    @GetMapping("/history/{operationType}")
    public ResponseEntity<List<OperationResultDTO>> historyByOp(
            @RequestHeader("Authorization") String auth,
            @PathVariable String operationType) {
        Long userId = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(temperatureService.getHistoryByOperation(userId, operationType));
    }
}
