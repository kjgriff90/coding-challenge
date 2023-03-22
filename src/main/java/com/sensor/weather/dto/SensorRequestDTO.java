package com.sensor.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SensorRequestDTO {

    @NotNull
    @Min(value = 1, message = "ID value must be a whole number greater than 0")
    private Long id;
    private String country;
    private String city;
    private String status;
}
