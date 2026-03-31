package com.example.quantity_measurement.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.quantity_measurement.dto.QuantityDTO;
import com.example.quantity_measurement.dto.QuantityOperationResultDTO;
import com.example.quantity_measurement.service.IQuantityMeasurementService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = QuantityMeasurementController.class)
class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IQuantityMeasurementService quantityMeasurementService;

    @Test
    void shouldConvertQuantity() throws Exception {
        QuantityOperationResultDTO response = QuantityOperationResultDTO.builder()
                                .historyId("1")
                .operationType("CONVERT")
                .successful(Boolean.TRUE)
                .resultQuantity(QuantityDTO.builder()
                        .value(39.37007874)
                        .measurementType("LENGTH")
                        .unitName("INCH")
                        .build())
                .createdAt(LocalDateTime.now())
                .build();

        when(quantityMeasurementService.convert(any(QuantityDTO.class), eq("INCH"))).thenReturn(response);

        String request = """
                {
                  "sourceQuantity": {
                    "value": 1.0,
                    "measurementType": "LENGTH",
                    "unitName": "METER"
                  },
                  "targetUnit": "INCH"
                }
                """;

        mockMvc.perform(post("/api/quantity/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operationType").value("CONVERT"))
                .andExpect(jsonPath("$.successful").value(true))
                .andExpect(jsonPath("$.resultQuantity.unitName").value("INCH"));
    }

    @Test
    void shouldRejectInvalidRequest() throws Exception {
        String request = """
                {
                  "targetUnit": "INCH"
                }
                """;

        mockMvc.perform(post("/api/quantity/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
               .andExpect(status().isBadRequest());
    }
}
