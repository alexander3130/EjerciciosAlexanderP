package entity;

public class ProductoToCliente {
    //      --
    //      Atributos
    //      --
    private Producto producto;
    private Tienda tienda;

    //      --
    //      Constructores
    //      --
    public ProductoToCliente() {
    }
    public ProductoToCliente(Producto producto, Tienda tienda) {
        this.producto = producto;
        this.tienda = tienda;
    }
    //      --
    //      Getters and Setters
    //      --
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Tienda getTienda() {
        return tienda;
    }
    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    //      --
    //      toString
    //      --
    @Override
    public String toString() {
        return "Datos del producto: " +
                "ID-PRODUCTO:: " + producto.getId()+", " +
                " Nombre: " + producto.getNombre()+", " +
                " Precio: " + producto.getPrecio()+", " +
                " Stock: " + producto.getStock()+", " +
                "Nombre Tienda::" + tienda.getNombre()+", "+
                " Ubicación Tienda:" + tienda.getUbicacion()+".";
    }
}
