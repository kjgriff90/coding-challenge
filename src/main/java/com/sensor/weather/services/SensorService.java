package com.sensor.weather.services;

import com.sensor.weather.domain.Sensor;
import com.sensor.weather.dto.SensorRequestDTO;
import com.sensor.weather.dto.SensorResponseDTO;
import com.sensor.weather.repositories.SensorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public SensorResponseDTO createSensor(SensorRequestDTO sensorRequestDTO) {
        if (sensorRepository.existsById(sensorRequestDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Sensor already exists.");
        }
        Sensor sensor = modelMapper.map(sensorRequestDTO, Sensor.class);
        sensor = sensorRepository.save(sensor);

        SensorResponseDTO sensorResponseDTO = new SensorResponseDTO();
        sensorResponseDTO.setId(sensor.getId());
        return sensorResponseDTO;
    }

    public SensorResponseDTO findById(Long id) {
        Optional<Sensor> sensor = sensorRepository.findById(id);
        if (sensor.isPresent()) {
            return modelMapper.map(sensor.get(), SensorResponseDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor(s) not found with provided ID(s)");
        }
    }
}
