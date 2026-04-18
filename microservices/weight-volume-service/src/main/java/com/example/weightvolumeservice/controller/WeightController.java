package com.example.weightvolumeservice.controller;

import com.example.weightvolumeservice.dto.MeasurementDTOs.*;
import com.example.weightvolumeservice.security.AuthClient;
import com.example.weightvolumeservice.service.WeightVolumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weight")
@RequiredArgsConstructor
public class WeightController {

    private final WeightVolumeService service;
    private final AuthClient authClient;

    @PostMapping("/convert")
    public ResponseEntity<OperationResultDTO> convert(@RequestHeader("Authorization") String auth,
            @Valid @RequestBody ConversionRequestDTO req) {
        Long uid = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(service.convertWeight(req.getSourceQuantity(), req.getTargetUnit(), uid));
    }

    @PostMapping("/compare")
    public ResponseEntity<OperationResultDTO> compare(@RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO req) {
        Long uid = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(service.compareWeight(req.getFirstQuantity(), req.getSecondQuantity(), uid));
    }

    @PostMapping("/add")
    public ResponseEntity<OperationResultDTO> add(@RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO req) {
        Long uid = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(service.addWeight(req.getFirstQuantity(), req.getSecondQuantity(), req.getResultUnit(), uid));
    }

    @PostMapping("/subtract")
    public ResponseEntity<OperationResultDTO> subtract(@RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO req) {
        Long uid = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(service.subtractWeight(req.getFirstQuantity(), req.getSecondQuantity(), req.getResultUnit(), uid));
    }

    @PostMapping("/multiply")
    public ResponseEntity<OperationResultDTO> multiply(@RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO req) {
        Long uid = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(service.multiplyWeight(req.getFirstQuantity(), req.getSecondQuantity(), req.getResultUnit(), uid));
    }

    @PostMapping("/divide")
    public ResponseEntity<OperationResultDTO> divide(@RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO req) {
        Long uid = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(service.divideWeight(req.getFirstQuantity(), req.getSecondQuantity(), req.getResultUnit(), uid));
    }

    @GetMapping("/history")
    public ResponseEntity<List<OperationResultDTO>> history(@RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(service.getWeightHistory(authClient.validateAndExtractUserId(auth)));
    }

    @GetMapping("/history/{op}")
    public ResponseEntity<List<OperationResultDTO>> historyByOp(@RequestHeader("Authorization") String auth,
            @PathVariable String op) {
        return ResponseEntity.ok(service.getWeightHistoryByOp(authClient.validateAndExtractUserId(auth), op));
    }
}
