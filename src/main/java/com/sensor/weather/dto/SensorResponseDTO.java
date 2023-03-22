package com.sensor.weather.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SensorResponseDTO {

    private Long id;
    private String country;
    private String city;
    private LocalDate createdDate;
    private String status;
    private Set<SensorRecordResponseDTO> sensorRecords;
}
