package com.sensor.weather.services;

import com.sensor.weather.dto.SensorMetricAveragesDTO;
import com.sensor.weather.dto.SensorMetricRequestDTO;
import com.sensor.weather.dto.SensorMetricResponseDTO;
import com.sensor.weather.dto.SensorRecordAveragesDTO;
import com.sensor.weather.repositories.SensorRecordRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SensorMetricService {

    private final SensorRecordRepository sensorRecordRepository;
    private final ModelMapper modelMapper;

    public SensorMetricResponseDTO findMultiple(SensorMetricRequestDTO sensorMetricRequestDTO) {
        List<SensorRecordAveragesDTO> sensorRecordAverages;

        LocalDate fromDate = sensorMetricRequestDTO.getFromDate();
        fromDate = validateDateWithinLastMonth(fromDate);

        List<Long> ids = sensorMetricRequestDTO.getIds();
        if (CollectionUtils.isEmpty(ids)) {
            sensorRecordAverages = sensorRecordRepository.findAveragesByAllInDateRange(fromDate);
        } else {
            sensorRecordAverages = sensorRecordRepository.findAveragesByIdsInDateRange(ids, fromDate);
        }
        if (CollectionUtils.isEmpty(sensorRecordAverages)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor(s) not found with provided ID(s)");
        }
        List<SensorMetricAveragesDTO> sensorMetricAveragesDTOS = mapProjectedDataToDTO(sensorRecordAverages);
        filterMetrics(sensorMetricAveragesDTOS, sensorMetricRequestDTO.getMetrics());
        return new SensorMetricResponseDTO(sensorMetricAveragesDTOS);
    }

    private List<SensorMetricAveragesDTO> mapProjectedDataToDTO(List<SensorRecordAveragesDTO> sensorRecordAverages) {
        return sensorRecordAverages
                .stream()
                .map(sensorRecordAveragesDTO -> modelMapper.map(sensorRecordAveragesDTO, SensorMetricAveragesDTO.class))
                .collect(Collectors.toList());
    }

    private LocalDate validateDateWithinLastMonth(LocalDate fromDate) {
        LocalDate today = LocalDate.now();
        if (fromDate == null) {
            return today;
        } else if (fromDate.isBefore(today.minusMonths(1))
        || fromDate.isAfter(today)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date provided must be within the last month");
        }
        return fromDate;

    }
    private void filterMetrics(List<SensorMetricAveragesDTO> sensorMetricAveragesDTOs, List<String> metrics) {
        if (CollectionUtils.isEmpty(metrics)) {
            return;
        }
        sensorMetricAveragesDTOs.forEach(sensorRecordAveragesDTO -> {
            if (metrics.stream().noneMatch("temperature"::equalsIgnoreCase)) {
                sensorRecordAveragesDTO.setAverageTemperature(null);
            }
            if (metrics.stream().noneMatch("humidity"::equalsIgnoreCase)) {
                sensorRecordAveragesDTO.setAverageHumidity(null);
            }
            if (metrics.stream().noneMatch("windSpeed"::equalsIgnoreCase)) {
                sensorRecordAveragesDTO.setAverageWindSpeed(null);
            }
        });
    }
}
