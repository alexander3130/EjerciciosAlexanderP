package controller;

import entity.Tienda;
import model.ModelTienda;

import javax.swing.*;

public class ControllerTienda {
    public static void listTienda(){
        ModelTienda objModelTienda = new ModelTienda();
        String listToTiendas = "Lista de tiendas: \n";


        for (Object objTienda: objModelTienda.list()){
            listToTiendas += (Tienda) objTienda + "\n";
        }

        JOptionPane.showMessageDialog(null, listToTiendas);
    }
    public static String listTiendaString(){
        ModelTienda objModelTienda = new ModelTienda();
        String listToTienda = "Lista de tiendas: \n";


        for (Object objTienda: objModelTienda.list()){
            listToTienda += (Tienda) objTienda + "\n";
        }

        return listToTienda;
    }
}
