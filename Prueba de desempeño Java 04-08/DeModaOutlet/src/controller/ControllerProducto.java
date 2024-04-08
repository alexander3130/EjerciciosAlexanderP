package controller;

import entity.Producto;
import entity.ProductoToCliente;
import model.ModelProducto;
import javax.swing.*;

public class ControllerProducto {
    public static void createProduct(){
        ModelProducto objModelProducto = new ModelProducto();
        Producto objProducto = new Producto();

        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del producto: ");
        double precio = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el precio del producto: "));
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el stock inicial del producto: "));
        int id_tienda = Integer.parseInt(JOptionPane.showInputDialog(ControllerTienda.listTiendaString() + "\nIngresa el id de la tienda a la que pertenece el producto:"));

        objProducto.setNombre(nombre);
        objProducto.setPrecio(precio);
        objProducto.setStock(stock);
        objProducto.setFk_tienda(id_tienda);


        Producto producto = (Producto) objModelProducto.create(objProducto);

        if (producto != null){
            JOptionPane.showMessageDialog(null, "El producto se creó con éxito!: \n" + producto);
        }

    }
    public static void listProduct(){
        ModelProducto objModelProducto = new ModelProducto();
        String listToProduct  = "Lista de productos: \n";


        for (Object objProduct: objModelProducto.list()){
            listToProduct += (ProductoToCliente) objProduct + "\n";
        }
        JOptionPane.showMessageDialog(null, listToProduct);
    }
    public static String listProductString(){
        ModelProducto objModelProducto = new ModelProducto();
        String listToProduct  = "Lista de productos: \n";


        for (Object objProduct: objModelProducto.list()){
            listToProduct += (ProductoToCliente) objProduct + "\n";
        }
        return listToProduct;
    }
    public static void updateProduct(){
        ModelProducto objModelProducto = new ModelProducto();
        Producto objProducto = new Producto();

        int id = Integer.parseInt(JOptionPane.showInputDialog(listProductString() + "\n Ingresa el id del producto que deseas actualizar"));


        ProductoToCliente productoById = (ProductoToCliente) objModelProducto.findByID(id);
        if (productoById == null || productoById.getProducto() == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el ID proporcionado.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del producto", productoById.getProducto().getNombre());
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el precio del producto", productoById.getProducto().getPrecio()));
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el stock del producto", productoById.getProducto().getStock()));
        int id_tienda = Integer.parseInt(JOptionPane.showInputDialog(ControllerTienda.listTiendaString() + "\nIngresa el id de la tienda establecida para el producto: ", productoById.getProducto().getFk_tienda()));


        objProducto.setNombre(nombre);
        objProducto.setPrecio(precio);
        objProducto.setStock(stock);
        objProducto.setFk_tienda(id_tienda);
        objProducto.setId(id);

        // Actualizar el vuelo existente en la base de datos
        boolean updated = objModelProducto.update(objProducto);

        if (updated) {
            JOptionPane.showMessageDialog(null, "El producto se ha actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto.");
        }
    }
    public static void deleteProduct(){
        ModelProducto objModelProducto = new ModelProducto();
        Producto objProducto = new Producto();

        int id = Integer.parseInt(JOptionPane.showInputDialog(listProductString()+ "\n Ingrese el id del producto que desea eliminar"));

        objProducto.setId(id);

        boolean validation = objModelProducto.delete(objProducto);


        if (validation){
            JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente!." );
        }
    }




}

