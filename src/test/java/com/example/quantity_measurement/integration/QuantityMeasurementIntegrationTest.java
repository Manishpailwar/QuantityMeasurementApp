package com.example.quantity_measurement.integration;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.quantity_measurement.repository.IQuantityMeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class QuantityMeasurementIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IQuantityMeasurementRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void shouldAddQuantitiesAndStoreHistory() throws Exception {
        String request = """
                {
                  "firstQuantity": {
                    "value": 2.0,
                    "measurementType": "WEIGHT",
                    "unitName": "KILOGRAM"
                  },
                  "secondQuantity": {
                    "value": 500.0,
                    "measurementType": "WEIGHT",
                    "unitName": "GRAM"
                  },
                  "resultUnit": "KILOGRAM"
                }
                """;

        mockMvc.perform(post("/api/quantity/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operationType").value("ADD"))
                .andExpect(jsonPath("$.successful").value(true))
                .andExpect(jsonPath("$.resultQuantity.unitName").value("KILOGRAM"))
                .andExpect(jsonPath("$.resultQuantity.value").value(2.5));

        mockMvc.perform(get("/api/quantity/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].operationType").value("ADD"));
    }

    @Test
    void shouldSupportGenericOperateEndpoint() throws Exception {
        String request = """
                {
                  "operationType": "CONVERT",
                  "firstQuantity": {
                    "value": 1.0,
                    "measurementType": "LENGTH",
                    "unitName": "METER"
                  },
                  "targetUnit": "CENTIMETER"
                }
                """;

        mockMvc.perform(post("/api/quantity/operate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operationType").value("CONVERT"))
                .andExpect(jsonPath("$.successful").value(true))
                .andExpect(jsonPath("$.resultQuantity.value").value(100.0))
                .andExpect(jsonPath("$.resultQuantity.unitName").value("CENTIMETER"));
    }
}
