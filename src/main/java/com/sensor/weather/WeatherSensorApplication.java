package com.sensor.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WeatherSensorApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherSensorApplication.class, args);
    }

}
