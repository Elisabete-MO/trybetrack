package com.betrybe.trybetrack.models.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "bus_lines")
public class BusLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    @OneToMany(mappedBy = "busLine", fetch = FetchType.EAGER)
    private List<Schedule> schedules;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "station_bus_lines",
            joinColumns = @JoinColumn(name = "bus_line_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id")
    )
    private List<Station> stations;

    public BusLine() {
    }

    public BusLine(Long id, String code, String name, List<Schedule> schedules, List<Station> stations) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.schedules = schedules;
        this.stations = stations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
