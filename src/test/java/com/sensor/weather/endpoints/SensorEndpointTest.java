package com.sensor.weather.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensor.weather.dto.SensorRequestDTO;
import com.sensor.weather.services.SensorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SensorEndpoint.class)
class SensorEndpointTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private SensorService sensorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenSensorIdMissing_Return400() throws Exception {
        mvc.perform(post("/weather-sensors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenSensorIdNegativeNumber_Return400() throws Exception {
        SensorRequestDTO sensorRequestDTO = new SensorRequestDTO();
        sensorRequestDTO.setId(-12L);
        mvc.perform(post("/weather-sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sensorRequestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenSensorIdValid_Return201() throws Exception {
        SensorRequestDTO sensorRequestDTO = new SensorRequestDTO();
        sensorRequestDTO.setId(5L);
        mvc.perform(post("/weather-sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sensorRequestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenSensorRetrievedByInvalidId_Return400() throws Exception {
        mvc.perform(get("/weather-sensors/{id}", "not a number")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenSensorRetrievedByValidId_Return200() throws Exception {
        mvc.perform(get("/weather-sensors/{id}", 5L, 6L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @TestConfiguration
    @AutoConfigureDataJpa
    static class TestConfig {
    }

}
