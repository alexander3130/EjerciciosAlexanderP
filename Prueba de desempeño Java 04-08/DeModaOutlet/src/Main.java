import controller.ControllerCliente;
import controller.ControllerCompra;
import controller.ControllerProducto;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int option = 0;
        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    ________________________________________
                    |                                                                              |
                    |                       DE MODA OUTLET                     |
                    |_______________________________________|
                    | Por favor lea el menu y elija una opción: 
                    |
                    | 1. Administrar Clientes.
                    | 2. Administrar Productos.
                    | 3. Administrar Compras.
                    | 4. Salir.
                    """));
            switch (option){
                case 1:
                    option = 0;
                    do {
                        option = Integer.parseInt(JOptionPane.showInputDialog("""
                    ________________________________________
                    |                                                                              |
                    |            DE MODA OUTLET - CLIENTE             |
                    |_______________________________________|
                    | Por favor lea el menu y elija una opción: 
                    |                  |                       |
                    |                  v                     v
                    | 1. Ver Clientes.
                    | 2. Crear Clientes.
                    | 3. Actualizar Clientes.
                    | 4. Eliminar Clientes.
                    | 5. Volver
                    """));
                        switch (option){
                            case 1:
                                ControllerCliente.listCliente();
                                break;
                            case 2:
                                ControllerCliente.createCliente();
                                break;
                            case 3:
                                ControllerCliente.updateCliente();
                                break;
                            case 4:
                                ControllerCliente.deleteCliente();
                                break;
                            case 5:
                                break;
                        }
                    }while(option !=5);
                    break;
                case 2:
                    option = 0;
                    do {
                        option = Integer.parseInt(JOptionPane.showInputDialog("""
                    ________________________________________
                    |                                                                              |
                    |           DE MODA OUTLET - PRODUCTO            |
                    |_______________________________________|
                    | Por favor lea el menu y elija una opción: 
                    |                  |                       |
                    |                  v                     v
                    | 1. Ver Productos.
                    | 2. Crear Productos.
                    | 3. Actualizar Productos.
                    | 4. Eliminar Productos.
                    | 5. Volver
                    """));
                        switch (option){
                            case 1:
                                ControllerProducto.listProduct();
                                break;
                            case 2:
                                ControllerProducto.createProduct();
                                break;
                            case 3:
                                ControllerProducto.updateProduct();
                                break;
                            case 4:
                                ControllerProducto.deleteProduct();
                                break;
                            case 5:
                                break;
                        }
                    }while(option !=5);
                    break;
                case 3:
                    option = 0;
                    do {
                        option = Integer.parseInt(JOptionPane.showInputDialog("""
                    ________________________________________
                    |                                                                              |
                    |            DE MODA OUTLET - COMPRAS             |
                    |_______________________________________|
                    | Por favor lea el menu y elija una opción: 
                    |                  |                       |
                    |                  v                     v
                    | 1. Ver Compras.
                    | 2. Crear Compras.
                    | 3. Actualizar Compras.
                    | 4. Eliminar Compras.
                    | 5. Volver
                    """));
                        switch (option){
                            case 1:
                                ControllerCompra.listCompra();
                                break;
                            case 2:
                                ControllerCompra.createCompra();
                                break;
                            case 3:
                                ControllerCompra.updateCompra();
                                break;
                            case 4:
                                ControllerCompra.deleteCompra();
                                break;
                            case 5:
                                break;
                        }
                    }while(option !=5);
                    break;
                case 4:
                    break;
            }
        }while (option!=4);
    }
}