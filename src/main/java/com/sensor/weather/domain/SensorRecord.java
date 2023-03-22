package com.sensor.weather.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor
public class SensorRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Sensor sensor;
    private Double temperature;
    private Double humidity;
    private Double windSpeed;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CreationTimestamp
    private LocalDate createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorRecord that = (SensorRecord) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
