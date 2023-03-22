package com.sensor.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SensorRecordDataRequestDTO {

    @NotNull
    private Double temperature;
    @NotNull
    @Min(value = 0, message = "Humidity must be 0 or greater")
    private Double humidity;
    @NotNull
    @Min(value = 0, message = "Wind speed must be 0 or greater")
    private Double windSpeed;
}
