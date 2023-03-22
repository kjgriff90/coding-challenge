package com.sensor.weather.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SensorMetricAveragesDTO {

    private Long sensorId;
    private Double averageTemperature;
    private Double averageHumidity;
    private Double averageWindSpeed;
}
