package com.sensor.weather.endpoints;

import com.sensor.weather.dto.SensorRequestDTO;
import com.sensor.weather.dto.SensorResponseDTO;
import com.sensor.weather.services.SensorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("weather-sensors")
@AllArgsConstructor
public class SensorEndpoint {

    private SensorService sensorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorResponseDTO createSensor(@Valid @RequestBody SensorRequestDTO sensorRequestDTO) {
        return sensorService.createSensor(sensorRequestDTO);
    }

    @GetMapping(value = "/{id}")
    public SensorResponseDTO findById(@PathVariable("id") Long id) {
        return sensorService.findById(id);
    }
}
