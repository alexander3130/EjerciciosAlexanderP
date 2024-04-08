package entity;

public class VueloConAvion {
    private Flight flight;
    private Airplane airplane;

    public VueloConAvion() {
    }

    public VueloConAvion(Flight flight, Airplane airplane) {
        this.flight = flight;
        this.airplane = airplane;
    }

    // Getters y setters (según sea necesario)

    public Flight getVuelo() {
        return flight;
    }

    public void setVuelo(Flight flight) {
        this.flight = flight;
    }

    public Airplane getAvion() {
        return airplane;
    }

    public void setAvion(Airplane airplane) {
        this.airplane = airplane;
    }

    @Override
    public String toString() {
        return "Datos generales: " +
                "ID - VUELO: "+ flight.getId() +
                " DESTINO: " + flight.getDestination() +
                " FECHA: " + flight.getStart() +
                " HORA - SALIDA: " + flight.getTime() +
                " MODELO - AVION:  " + airplane.getModel() +
                " CAPACIDAD: "  + airplane.getCapacity()
                 ;
    }
}
