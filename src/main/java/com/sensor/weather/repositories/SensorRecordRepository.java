package com.sensor.weather.repositories;

import com.sensor.weather.domain.SensorRecord;
import com.sensor.weather.dto.SensorRecordAveragesDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SensorRecordRepository extends CrudRepository<SensorRecord, Long> {

    @Query("SELECT AVG(sr.temperature) as averageTemperature, " +
            "AVG(sr.humidity) as averageHumidity, " +
            "AVG(sr.windSpeed) as averageWindSpeed," +
            "sr.sensor.id as sensorId " +
            "FROM SensorRecord as sr " +
            "WHERE sr.sensor.id IN (:ids) " +
            "AND sr.createdDate >= :fromDate GROUP BY sr.sensor.id")
    List<SensorRecordAveragesDTO> findAveragesByIdsInDateRange(@Param("ids") List<Long> ids, @Param("fromDate") LocalDate fromDate);

    @Query("SELECT AVG(sr.temperature) as averageTemperature, " +
            "AVG(sr.humidity) as averageHumidity, " +
            "AVG(sr.windSpeed) as averageWindSpeed," +
            "sr.sensor.id as sensorId " +
            "FROM SensorRecord as sr " +
            "WHERE sr.createdDate >= :fromDate GROUP BY sr.sensor.id")
    List<SensorRecordAveragesDTO> findAveragesByAllInDateRange(@Param("fromDate") LocalDate fromDate);

}
