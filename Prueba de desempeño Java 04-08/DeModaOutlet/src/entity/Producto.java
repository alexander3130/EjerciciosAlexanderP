package entity;

public class Producto {
    //      --
    //      Atributos
    //      --
    private int id;
    private String nombre;
    private double precio;
    private int fk_tienda;
    private int stock;

    //      --
    //      Constructores
    //      --
    public Producto() {
    }
    public Producto(int id, String nombre, double precio, int fk_tienda, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fk_tienda = fk_tienda;
        this.stock = stock;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getFk_tienda() {
        return fk_tienda;
    }

    public void setFk_tienda(int fk_tienda) {
        this.fk_tienda = fk_tienda;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    //      --
    //      toString
    //      --
    @Override
    public String toString() {
        return "Producto: " +
                "id: " + id +
                ", nombre: '" + nombre + '\'' +
                ", precio: " + precio +
                ", fk_tienda: " + fk_tienda;
    }
}
