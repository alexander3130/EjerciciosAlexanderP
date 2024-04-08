package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
    private int id;
    private String destination;
    private LocalDate start;
    private LocalTime time;
    private int airplane_id;


    public Flight(int id, String destination, LocalDate start, LocalTime time, int airplane_id) {
        this.id = id;
        this.destination = destination;
        this.start = start;
        this.time = time;
        this.airplane_id = airplane_id;
    }

    public Flight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getAirplane_id() {
        return airplane_id;
    }

    public void setAirplane_id(int airplane_id) {
        this.airplane_id = airplane_id;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "id_vuelo=" + id +
                ", destino='" + destination + '\'' +
                ", fecha_salida=" + start +
                ", hora_salida=" + time +
                ", id_avion=" + airplane_id +
                '}';
    }
}
