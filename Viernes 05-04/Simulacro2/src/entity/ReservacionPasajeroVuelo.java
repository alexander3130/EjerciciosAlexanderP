package entity;

public class ReservacionPasajeroVuelo {
    Reservation reservation;
    Passenger passenger;
    Flight flight;


    public ReservacionPasajeroVuelo() {
    }

    public ReservacionPasajeroVuelo(Reservation reservation, Passenger passenger, Flight flight) {
        this.reservation = reservation;
        this.passenger = passenger;
        this.flight = flight;
    }

    public Reservation getReservacion() {
        return reservation;
    }

    public void setReservacion(Reservation reservation) {
        this.reservation = reservation;
    }

    public Passenger getPasajero() {
        return passenger;
    }

    public void setPasajero(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getVuelo() {
        return flight;
    }

    public void setVuelo(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        String separador = "----------------------------------------------------------"; // Línea de separación


        String mensaje = "Datos generales:\n" +
                "    ID de reservación: " + reservation.getId() + "\n" +
                "    Nombre del pasajero: " + passenger.getName() + " " + passenger.getLastname() + "\n" +
                "    Destino: " + flight.getDestination() + " " +
                "    Fecha de salida: " + flight.getStart().toString() + " " +
                "    Hora de salida: " + flight.getTime() + "\n" +
                "    Asiento: " + reservation.getSeat() + " " +
                "    Documento de identidad: " + passenger.getId_document() + "\n" +
                separador + "\n"; // Línea de separación y salto de línea al final

        return mensaje;



    }
}
