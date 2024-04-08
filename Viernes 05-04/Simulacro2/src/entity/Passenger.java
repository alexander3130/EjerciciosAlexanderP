package entity;

public class Passenger {
    private int id;
    private String name;
    private String lastname;
    private String id_document;

    public Passenger(int id, String name, String lastname, String id_document) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.id_document = id_document;
    }

    public Passenger() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getId_document() {
        return id_document;
    }

    public void setId_document(String id_document) {
        this.id_document = id_document;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                "id=" + id +
                ", nombre_pasajero='" + name + '\'' +
                ", apellido_pasajero='" + lastname + '\'' +
                ", documento_identidad='" + id_document + '\'' +
                '}';
    }
}
