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
@RequestMapping("/api/volume")
@RequiredArgsConstructor
public class VolumeController {

    private final WeightVolumeService service;
    private final AuthClient authClient;

    @PostMapping("/convert")
    public ResponseEntity<OperationResultDTO> convert(@RequestHeader("Authorization") String auth,
            @Valid @RequestBody ConversionRequestDTO req) {
        Long uid = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(service.convertVolume(req.getSourceQuantity(), req.getTargetUnit(), uid));
    }

    @PostMapping("/compare")
    public ResponseEntity<OperationResultDTO> compare(@RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO req) {
        Long uid = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(service.compareVolume(req.getFirstQuantity(), req.getSecondQuantity(), uid));
    }

    @PostMapping("/add")
    public ResponseEntity<OperationResultDTO> add(@RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO req) {
        Long uid = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(service.addVolume(req.getFirstQuantity(), req.getSecondQuantity(), req.getResultUnit(), uid));
    }

    @PostMapping("/subtract")
    public ResponseEntity<OperationResultDTO> subtract(@RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO req) {
        Long uid = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(service.subtractVolume(req.getFirstQuantity(), req.getSecondQuantity(), req.getResultUnit(), uid));
    }

    @PostMapping("/multiply")
    public ResponseEntity<OperationResultDTO> multiply(@RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO req) {
        Long uid = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(service.multiplyVolume(req.getFirstQuantity(), req.getSecondQuantity(), req.getResultUnit(), uid));
    }

    @PostMapping("/divide")
    public ResponseEntity<OperationResultDTO> divide(@RequestHeader("Authorization") String auth,
            @Valid @RequestBody BinaryOperationRequestDTO req) {
        Long uid = authClient.validateAndExtractUserId(auth);
        return ResponseEntity.ok(service.divideVolume(req.getFirstQuantity(), req.getSecondQuantity(), req.getResultUnit(), uid));
    }

    @GetMapping("/history")
    public ResponseEntity<List<OperationResultDTO>> history(@RequestHeader("Authorization") String auth) {
        return ResponseEntity.ok(service.getVolumeHistory(authClient.validateAndExtractUserId(auth)));
    }

    @GetMapping("/history/{op}")
    public ResponseEntity<List<OperationResultDTO>> historyByOp(@RequestHeader("Authorization") String auth,
            @PathVariable String op) {
        return ResponseEntity.ok(service.getVolumeHistoryByOp(authClient.validateAndExtractUserId(auth), op));
    }
}
