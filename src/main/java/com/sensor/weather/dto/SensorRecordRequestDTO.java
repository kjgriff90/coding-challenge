package com.sensor.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SensorRecordRequestDTO {

    @NotNull
    @Min(value = 1, message = "Sensor ID must be a whole number greater than 0")
    private Long sensorId;
    @Valid
    @NotNull
    private SensorRecordDataRequestDTO sensorRecord;
}
