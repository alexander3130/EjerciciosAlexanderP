package Ejercicio1;

import javax.swing.*;
import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args){
        Inventario objInventario = new Inventario();

        int option = 0;
        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opción que desea elegir:\n" +
                    "1. Agregar producto.\n" +
                    "2. Eliminar producto.\n" +
                    "3. Ver lista de productos.\n" +
                    "4. Buscar producto por nombre.\n" +
                    "5. Salir.\n"));

            switch (option){
                case 1:
                    Producto objProducto = new Producto(0,"",0);
                    int productId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id para el producto (Ej: 1): "));
                    objProducto.setId(productId);
                    String productNombre = JOptionPane.showInputDialog("Ingrese el nombre para el producto (Ej: Papel): ");
                    objProducto.setNombre(productNombre.toLowerCase());
                    double productPrecio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio para el producto (Ej: 1000): "));
                    objProducto.setPrecio(productPrecio);

                    objInventario.agregarProducto(objProducto);
                    break;
                case 2:
                    productId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id para el producto (Ej: 1): "));
                    if (objInventario.eliminarProducto(productId)){
                        JOptionPane.showMessageDialog(null,"Producto eliminado correctamente. ENTER PARA CONTINUAR...");
                        objInventario.listarProductos();
                    }else{
                        JOptionPane.showMessageDialog(null, "El producto no pudo ser eliminado, por favor corrobore el id ingresado.");
                    }
                    break;
                case 3:
                    objInventario.listarProductos();
                    break;
                case 4:
                    String nombreBuscar = JOptionPane.showInputDialog("Ingrese el nombre del producto que desea buscar (Ej: Papel): ");
                    objInventario.buscarPorNombre(nombreBuscar.toLowerCase());

                    break;
            }
        }while(option !=5 );
    }
}
