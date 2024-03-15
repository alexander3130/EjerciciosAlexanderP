package Ejercicio1;

import javax.swing.*;
import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> listaProductos;

    public Inventario(){
        this.listaProductos = new ArrayList<>();
    }

    //  Metodos
    public void agregarProducto(Producto producto){
        this.listaProductos.add(producto);
    }
    public boolean eliminarProducto(int id){
        return listaProductos.removeIf(producto -> producto.getId() == id);
    }
    public void listarProductos(){
        String listaParaImprimir = "";
        for (Producto producto : this.listaProductos){
            listaParaImprimir +=
                    "\n============================\n" +
                    "ID: "+ producto.getId()
                    +"\nNombre: "+ producto.getNombre()
                    +"\nPrecio: "+ producto.getPrecio();
        }
        JOptionPane.showMessageDialog(null, listaParaImprimir);
    }

    public Producto buscarPorNombre(String nombreBuscar) {
        for (Producto objProducto : this.listaProductos) {
            if (objProducto.getNombre().equalsIgnoreCase(nombreBuscar)) {
                JOptionPane.showMessageDialog(null,"Productos que cumplen con la condición: "+
                        "\n_________________________________________"+
                        "\n============================\n" +
                        "ID: "+ objProducto.getId()
                        +"\nNombre: "+ objProducto.getNombre()
                        +"\nPrecio: "+ objProducto.getPrecio());
            }
        }
        return null;
    }
}
