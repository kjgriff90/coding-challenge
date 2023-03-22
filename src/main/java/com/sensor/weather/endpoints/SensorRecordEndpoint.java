package com.sensor.weather.endpoints;

import com.sensor.weather.dto.SensorRecordRequestDTO;
import com.sensor.weather.dto.SensorResponseDTO;
import com.sensor.weather.services.SensorRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("weather-sensors/records")
@AllArgsConstructor
public class SensorRecordEndpoint {

    private SensorRecordService sensorRecordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorResponseDTO createSensorRecord(@Valid @RequestBody SensorRecordRequestDTO sensorRecordRequestDTO) {
        return sensorRecordService.createSensorRecord(sensorRecordRequestDTO);
    }
}
