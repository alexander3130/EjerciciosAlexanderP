package entity;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private int passenger_id;
    private int flight_id;
    private LocalDateTime reservation_date;
    private String seat;

    public Reservation(int id, int passenger_id, int flight_id, LocalDateTime fechaReservacion, String seat) {
        this.id = id;
        this.passenger_id = passenger_id;
        this.flight_id = flight_id;
        this.reservation_date = fechaReservacion;
        this.seat = seat;
    }

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public LocalDateTime getFechaReservacion() {
        return reservation_date;
    }

    public void setFechaReservacion(LocalDateTime fechaReservacion) {
        this.reservation_date = fechaReservacion;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Reservacion{" +
                "id_reservacion=" + id +
                ", id_pasajero=" + passenger_id +
                ", id_vuelo=" + flight_id +
                ", fechaReservacion=" + reservation_date +
                ", asiento='" + seat + '\'' +
                '}';
    }
}
