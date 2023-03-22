package com.sensor.weather.endpoints;

import com.sensor.weather.dto.SensorMetricRequestDTO;
import com.sensor.weather.dto.SensorMetricResponseDTO;
import com.sensor.weather.services.SensorMetricService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("weather-sensors/metrics")
@AllArgsConstructor
public class SensorMetricEndpoint {

    private SensorMetricService sensorMetricService;

    @GetMapping
    public SensorMetricResponseDTO findAll(@Valid SensorMetricRequestDTO sensorMetricRequestDTO) {
        return sensorMetricService.findMultiple(sensorMetricRequestDTO);
    }
}
