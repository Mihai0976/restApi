package com.example.demo;

import com.example.demo.model.SCPentity;
import com.example.demo.service.SCPservice;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;


@SpringBootTest
@AutoConfigureMockMvc
public class SCPControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SCPservice anomalyService;
    @Test
    public void testGetAllAnomalies() throws Exception {
        // Mock the behavior of the service method
        Page<SCPentity> emptyPage = new PageImpl<>(Collections.emptyList());
        Mockito.when(anomalyService.getAllAnomalies(Mockito.any())).thenReturn(emptyPage);

        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/anomalies"))
                // Validate the response status code
                .andExpect(MockMvcResultMatchers.status().isOk());
        // You can add more validations here
    }


}
