package controller;

import entity.*;
import model.ModelTienda;
import model.ModelProducto;
import model.ModelCompra;
import model.ModelCliente;

import javax.swing.*;

public class ControllerCompra {
    public static void createCompra() {
        ModelProducto objModelProducto = new ModelProducto();
        ModelTienda objModelTienda = new ModelTienda();
        ModelCompra objModelCompra = new ModelCompra();
        ModelCliente objModelCliente = new ModelCliente();
        Compra objCompra = new Compra();
        int cantidad;
        int id_cliente = Integer.parseInt(JOptionPane.showInputDialog(ControllerCliente.listClienteString() + "\n Ingrese el cliente con quien está asociada la Compra: "));
        int id_producto = Integer.parseInt(JOptionPane.showInputDialog(ControllerProducto.listProductString() + "\n Ingrese el producto con el que está asociada la Compra: "));
        String fecha_de_compra = JOptionPane.showInputDialog("Ingresa la fecha de compra (yyyy-MM-dd)");
        try {
            cantidad = Integer.parseInt((JOptionPane.showInputDialog("Ingrese la cantidad de productos que desea comprar")));
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
            return;
        }



        ProductoToCliente objProducto = (ProductoToCliente) objModelProducto.findByID(id_producto);
        System.out.println(objProducto);
        int id_tienda = objProducto.getProducto().getFk_tienda();


// Verificar si el asiento excede la capacidad del avión o es inválido
        int stock = objModelProducto.getStockCapacity(id_producto);
        if (cantidad > stock) {
            System.out.println(stock);
            System.out.println(id_producto);
            JOptionPane.showMessageDialog(null, "La cantidad no es valida, stock actual: " + stock);
            return;
        }

        if (cantidad <= 0){
            JOptionPane.showMessageDialog(null, "No se acepta 0 ni numeros negativos ⚠");
            return;
        }

        objCompra.setFk_cliente(id_cliente);
        objCompra.setFk_producto(id_producto);
        objCompra.setCantidad(cantidad);
        objCompra.setFecha_compra(fecha_de_compra);
        Compra objCompraString = (Compra) objModelCompra.create(objCompra);
        JOptionPane.showMessageDialog(null, "Compra guardada con exito. " + objCompraString);
    };
    public static void listCompra(){
        ModelCompra objCompra = new ModelCompra();

        String listToCompra = "LISTA DE COMPRAS \n";
        Cliente objCliente;

        for (Object compra: objCompra.list()){
            listToCompra += (CompraClienteProducto) compra + "\n";
        }

        JOptionPane.showMessageDialog(null, listToCompra);
    }
    public static String listCompraString(){
        ModelCompra objCompra = new ModelCompra();

        String listaToCompra = "LISTA DE COMPRAS \n";

        for (Object compra: objCompra.list()){
            listaToCompra += (CompraClienteProducto) compra + "\n";
        }

        return  listaToCompra;
    }
    public static void updateCompra(){

    }
    public static void deleteCompra(){
        ModelCompra objModelCompra = new ModelCompra();
        Compra objCompra = new Compra();

        int id = Integer.parseInt(JOptionPane.showInputDialog(listCompraString() + "\n Ingrese el id de la compra que desea eliminar"));

        objCompra.setId(id);


        boolean validation = objModelCompra.delete(objCompra);;

        if (validation){
            JOptionPane.showMessageDialog(null, "Compra  eliminada con éxito" );
        }

    }

}
