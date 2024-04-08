package controller;

import entity.Cliente;
import model.ModelCliente;

import javax.swing.*;

public class ControllerCliente {
    public static void createCliente(){
        ModelCliente objModelCliente = new ModelCliente();
        Cliente objCliente = new Cliente();

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente: ");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del cliente: ");
        String email = JOptionPane.showInputDialog("Ingrese el email del cliente: ");

        objCliente.setNombre(nombre);
        objCliente.setApellido(apellido);
        objCliente.setEmail(email);



        Cliente cliente = (Cliente) objModelCliente.create(objCliente);

        if (cliente != null){
            JOptionPane.showMessageDialog(null, "El usuario se ha creado correctamente: \n" + cliente);
        }

    }
    public static void listCliente(){
        ModelCliente objModelCLient = new ModelCliente();
        String listToClients  = "Lista de clientes: \n";


        for (Object objClient: objModelCLient.list()){
            listToClients += (Cliente) objClient + "\n";
        }

        JOptionPane.showMessageDialog(null, listToClients);
    }
    public static String listClienteString(){
        ModelCliente objModelClient = new ModelCliente();
        String listToClient  = "Lista de clientes: \n";


        for (Object objClient: objModelClient.list()){
            listToClient += (Cliente) objClient + "\n";
        }

        return listToClient;
    }
    public static void updateCliente(){
        ModelCliente objModelCliente = new ModelCliente();
        Cliente objCliente = new Cliente();

        int id = Integer.parseInt(JOptionPane.showInputDialog( listClienteString() + "\n| Ingresa el id del cliente que deseas actualizar: "));

        Cliente cliente =  (Cliente) objModelCliente.findByID(id);

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del Cliente", cliente.getNombre());
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del Cliente", cliente.getApellido());
        String documento_identidad = JOptionPane.showInputDialog("Ingrese el correo del Cliente", cliente.getEmail());

        objCliente.setNombre(nombre);
        objCliente.setApellido(apellido);
        objCliente.setEmail(documento_identidad);
        objCliente.setId(id);


        boolean validacion = objModelCliente.update(objCliente);

        if (validacion){
            JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente!" );
        }

    }
    public static void deleteCliente(){
        ModelCliente objModelCliente = new ModelCliente();
        Cliente objCliente = new Cliente();

        int id = Integer.parseInt(JOptionPane.showInputDialog(listClienteString() + "\n Ingrese el id del Cliente que desea eliminar"));

        objCliente.setId(id);


        boolean validation = objModelCliente.delete(objCliente);;

        if (validation){
            JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente!" );
        }


    }

}
