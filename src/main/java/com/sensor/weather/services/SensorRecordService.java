package com.sensor.weather.services;

import com.sensor.weather.domain.Sensor;
import com.sensor.weather.domain.SensorRecord;
import com.sensor.weather.dto.SensorRecordRequestDTO;
import com.sensor.weather.dto.SensorRecordResponseDTO;
import com.sensor.weather.dto.SensorResponseDTO;
import com.sensor.weather.repositories.SensorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SensorRecordService {

    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public SensorResponseDTO createSensorRecord(SensorRecordRequestDTO sensorRecordRequestDTO) {
        Optional<Sensor> sensor = sensorRepository.findById(sensorRecordRequestDTO.getSensorId());
        if (sensor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor(s) with provided ID(s) does not exist");
        }
        SensorRecord sensorRecord = modelMapper.map(sensorRecordRequestDTO.getSensorRecord(), SensorRecord.class);

        Sensor sensorToBeUpdated = sensor.get();
        sensorRecord.setSensor(sensorToBeUpdated);
        sensorToBeUpdated.setSensorRecords(Collections.singleton(sensorRecord));

        SensorRecordResponseDTO sensorRecordResponseDTO = modelMapper.map(sensorRecord, SensorRecordResponseDTO.class);
        SensorResponseDTO sensorResponseDTO = modelMapper.map(sensorToBeUpdated, SensorResponseDTO.class);
        sensorResponseDTO.setSensorRecords(Collections.singleton(sensorRecordResponseDTO));
        return sensorResponseDTO;
    }
}
