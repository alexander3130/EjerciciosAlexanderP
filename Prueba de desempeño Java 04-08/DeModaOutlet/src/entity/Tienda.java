package entity;

public class Tienda {

    //      --
    //      Atributos
    //      --
    private int id;
    private String nombre;
    private String ubicacion;

    public Tienda() {
    }

    //      --
    //      Constructores
    //      --
    public Tienda(int id, String nombre, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    //      --
    //      Getters and Setters
    //      --
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    //      --
    //      toString
    //      --

    @Override
    public String toString() {
        return "Tienda: " +
                "id: " + id +
                ", nombre: '" + nombre + '\'' +
                ", ubicacion: '" + ubicacion + '\'' +
                '.';
    }
}
