package com.sensor.weather.services;

import com.sensor.weather.domain.Sensor;
import com.sensor.weather.dto.SensorRequestDTO;
import com.sensor.weather.dto.SensorResponseDTO;
import com.sensor.weather.repositories.SensorRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SensorServiceTest {

    @Autowired
    private SensorService sensorService;
    @Autowired
    private ModelMapper modelMapper;
    @MockBean
    private SensorRepository sensorRepository;

    @Test
    void whenSensorAlreadyExistsOnCreation_ReturnError() {
        when(sensorRepository.existsById(5L)).thenReturn(true);
        SensorRequestDTO sensorRequestDTO = new SensorRequestDTO();
        sensorRequestDTO.setId(5L);
        Exception exception = assertThrows(ResponseStatusException.class, () ->
                sensorService.createSensor(sensorRequestDTO));

        assertTrue(exception.getMessage().contains("Sensor already exists."));
    }

    @Test
    void whenSensorIdValidOnCreation_CreateDatabaseRecord() {
        when(sensorRepository.save(any())).thenReturn(new Sensor());
        SensorRequestDTO sensorRequestDTO = new SensorRequestDTO();
        sensorRequestDTO.setId(5L);
        sensorService.createSensor(sensorRequestDTO);
        verify(sensorRepository, times(1)).save(any());
    }

    @Test
    void whenSensorDoesNotExistByIdSearch_ReturnError() {
        Exception exception = assertThrows(ResponseStatusException.class, () ->
                sensorService.findById(5L));

        assertTrue(exception.getMessage().contains("Sensor(s) not found with provided ID(s)"));
    }

    @Test
    void whenSensorExistsByIdSearch_ReturnData() {
        Sensor sensor = new Sensor();
        sensor.setId(5L);
        sensor.setStatus("Active");
        when(sensorRepository.findById(5L)).thenReturn(Optional.of(sensor));
        SensorResponseDTO sensorResponseDTO = sensorService.findById(5L);
        assertEquals("Active", sensorResponseDTO.getStatus());
    }

}