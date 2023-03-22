package com.sensor.weather.repositories;

import com.sensor.weather.domain.Sensor;
import org.springframework.data.repository.CrudRepository;

public interface SensorRepository extends CrudRepository<Sensor, Long> {

}
