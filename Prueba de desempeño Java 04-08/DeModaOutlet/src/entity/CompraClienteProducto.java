package entity;

public class CompraClienteProducto {
    Compra compra;
    Cliente cliente;
    Producto producto;
    Tienda tienda;
    private int total;

    //      --
    //      Constructores
    //      --
    public CompraClienteProducto() {
    }

    public CompraClienteProducto(Compra compra, Cliente cliente, Producto producto, Tienda tienda) {
        this.compra = compra;
        this.cliente = cliente;
        this.producto = producto;
        this.tienda = tienda;
    }
    //      --
    //      Getters and Setters
    //      --
    public Compra getCompra() {
        return compra;
    }
    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Tienda getTienda() {
        return tienda;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    //      --
    //      toString
    //      --
    @Override
    public String toString() {
        String separator = "----------------------------------------------------------"; // Línea de separación


        String message = "\n" +
                "###################################\n"+
                "###            FACTURA:" + compra.getId()+"           ###\n"+
                "###         Tienda: " + tienda.getNombre() + "              ###\n" +
                "###        Ubicacion: " + tienda.getUbicacion() + "               ###\n" +
                "###      ID de compra: " + compra.getId() + "               ###\n" +
                "###################################\n" +
                "\n" +"\n" +
                "    Nombre del cliente: " + cliente.getNombre() + " " + cliente.getApellido() + "\n" +
                "    Correo del cliente: " + cliente.getEmail() + "\n " +
                "    Producto: " + producto.getNombre() + "\n" +
                "    Precio UND: " + producto.getPrecio() + "\n" +
                "    Cantidad: " + compra.getCantidad() + "\n" +
                "\n" +
                "\n" +
                "    Total: " + total +"\n"+
                "                    ¡GRACIAS POR SU COMPRA! \n" +
                separator + "\n";

        return message;



    }
}
