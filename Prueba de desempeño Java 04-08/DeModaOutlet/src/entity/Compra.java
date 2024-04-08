package entity;

public class Compra {
    //      --
    //      Atributos
    //      --
    private int id;
    private int fk_cliente;
    private int fk_producto;
    private String fecha_compra;
    private int cantidad;

    //      --
    //      Constructores
    //      --
    public Compra() {
    }

    public Compra(int id, int fk_cliente, int fk_producto, String fecha_compra, int cantidad) {
        this.id = id;
        this.fk_cliente = fk_cliente;
        this.fk_producto = fk_producto;
        this.fecha_compra = fecha_compra;
        this.cantidad = cantidad;
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

    public int getFk_cliente() {
        return fk_cliente;
    }

    public void setFk_cliente(int fk_cliente) {
        this.fk_cliente = fk_cliente;
    }

    public int getFk_producto() {
        return fk_producto;
    }

    public void setFk_producto(int fk_producto) {
        this.fk_producto = fk_producto;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //      --
    //      toString
    //      --

    @Override
    public String toString() {
        return "Compra | " +
                "id: " + id +
                ", cliente: " + fk_cliente +
                ", producto: " + fk_producto +
                ", fecha_compra: '" + fecha_compra + '\'' +
                ", cantidad: " + cantidad;
    }
}
