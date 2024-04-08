package controller;

import entity.Passenger;
import model.ModelPassenger;

import javax.swing.*;

public class ControllerPassenger {
    public static void createPassenger(){
        ModelPassenger objModelPassenger = new ModelPassenger();
        Passenger objPassenger = new Passenger();

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del pasajero");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del pasajero");
        String documento_identidad = JOptionPane.showInputDialog("Ingrese el documento_identidad del pasajero");

        objPassenger.setName(nombre);
        objPassenger.setLastname(apellido);
        objPassenger.setId_document(documento_identidad);



        Passenger passenger = (Passenger) objModelPassenger.create(objPassenger);

        if (passenger != null){
            JOptionPane.showMessageDialog(null, "El usuario se ha creado: \n" + passenger);
        }

    }
    public static void listPassenger(){
        ModelPassenger objModelPassenger = new ModelPassenger();
        String listaDePasajero  = "Lista pasajero\n";


        for (Object objPasajero: objModelPassenger.list()){
            listaDePasajero += (Passenger) objPasajero + "\n";
        }

        JOptionPane.showMessageDialog(null, listaDePasajero);
    }
    public static String listPassengerString(){
        ModelPassenger objModelPassenger = new ModelPassenger();
        String listaDePasajero  = "Lista pasajero\n";


        for (Object objPasajero: objModelPassenger.list()){
            listaDePasajero += (Passenger) objPasajero + "\n";
        }

        return listaDePasajero;
    }
    public static void updatePassenger(){
        ModelPassenger objModelPassenger = new ModelPassenger();
        Passenger objPassenger = new Passenger();

        int id = Integer.parseInt(JOptionPane.showInputDialog( listPassengerString() + "\nIngresa el id del pasajero que deseas actualizar"));

        Passenger passenger =  (Passenger) objModelPassenger.findByID(id);

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del pasajero", passenger.getName());
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del pasajero", passenger.getLastname());
        String documento_identidad = JOptionPane.showInputDialog("Ingrese el documento_identidad del pasajero", passenger.getId_document());

        objPassenger.setName(nombre);
        objPassenger.setLastname(apellido);
        objPassenger.setId_document(documento_identidad);
        objPassenger.setId(id);


        boolean validacion = objModelPassenger.update(objPassenger);

        if (validacion){
            JOptionPane.showMessageDialog(null, "Pasajero actualizado con exito" );
        }

    }
    public static void deletePassenger(){
        ModelPassenger objModelPassenger = new ModelPassenger();
        Passenger objPassenger = new Passenger();

        int id = Integer.parseInt(JOptionPane.showInputDialog(listPassengerString() + "\n Ingrese el id del pasajero que desea eliminar"));

        objPassenger.setId(id);


        boolean validacion = objModelPassenger.delete(objPassenger);;

        if (validacion){
            JOptionPane.showMessageDialog(null, "Pasajero eliminado con exito" );
        }


    }

}
