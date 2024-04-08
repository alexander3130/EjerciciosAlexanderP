package controller;

import entity.Airplane;
import model.ModelAirplane;

import javax.swing.*;

public class ControllerAirplane {

    public static void createAirplane(){
        ModelAirplane objModelAirplane = new ModelAirplane();
        Airplane objAirplane = new Airplane();

        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del avion");
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad del avion"));


        objAirplane.setModel(modelo);
        objAirplane.setCapacity(capacidad);


        Airplane airplane = (Airplane) objModelAirplane.create(objAirplane);

        if (airplane != null){
            JOptionPane.showMessageDialog(null, "El usuario se ha creado: \n" + airplane);
        }

    }
    public static void listAirplane(){
        ModelAirplane objModelAirplane = new ModelAirplane();
        String listaDeAviones = "Lista aviones \n";


        for (Object objAvion: objModelAirplane.list()){
            listaDeAviones += (Airplane) objAvion + "\n";
        }

        JOptionPane.showMessageDialog(null, listaDeAviones);
    }
    public static String listAirplaneString(){
        ModelAirplane objModelAirplane = new ModelAirplane();
        String listaDeAviones = "Lista aviones \n";


        for (Object objAvion: objModelAirplane.list()){
            listaDeAviones += (Airplane) objAvion + "\n";
        }

        return listaDeAviones;
    }
    public static void updateAirplane(){
        ModelAirplane objModelAirplane = new ModelAirplane();
        Airplane objAirplane = new Airplane();

        int id = Integer.parseInt(JOptionPane.showInputDialog( listAirplaneString() + "\nIngresa el id del avion que deseas actualizar"));


        Airplane airplane =  (Airplane) objModelAirplane.findByID(id);
        String modelo =JOptionPane.showInputDialog("ingresa el nombre del modelo", airplane.getModel());
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la capacidad del avion", airplane.getCapacity()));

        objAirplane.setId(id);
        objAirplane.setModel(modelo);
        objAirplane.setCapacity(capacidad);


        boolean validacion = objModelAirplane.update(objAirplane);

        if (validacion){
            JOptionPane.showMessageDialog(null, "Avion actualizado con exito" );
        }

    }
    public static void deleteAirplane(){
        ModelAirplane objModelAirplane = new ModelAirplane();
        Airplane objAirplane = new Airplane();

        int id = Integer.parseInt(JOptionPane.showInputDialog(listAirplaneString() + "\n Ingrese el id del avion que desea eliminar"));

        objAirplane.setId(id);


        boolean validacion = objModelAirplane.delete(objAirplane);

        if (validacion){
            JOptionPane.showMessageDialog(null, "Avion eliminado con exito" );
        }


    }


}
