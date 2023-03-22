package com.sensor.weather.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class Sensor {

    @Id
    @NotNull
    private Long id;
    private String country;
    private String city;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CreationTimestamp
    private LocalDate createdDate;
    private String status;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "sensor", cascade=CascadeType.ALL)
    private Set<SensorRecord> sensorRecords;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(id, sensor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
