import controller.ControllerAirplane;
import controller.ControllerFlight;
import controller.ControllerPassenger;
import controller.ControllerReserv;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int option = 0, optionAirplane = 0, optionPassenger = 0, optionReserv = 0 , optionFlight = 0;

        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    Ingrese una opción
                    1 -  AVION
                    2 -  PASAJERO
                    3 -  RESERVACION
                    4 -  VUELO
                    """));

            switch (option){
                case 1:
                    do {
                        optionAirplane = Integer.parseInt(JOptionPane.showInputDialog("""
                                1 - Crear avión
                                2 - Eliminar avión 
                                3 - Actualizar avión
                                4 - Listar avión
                                5 - Salir 
                                """));

                        switch (optionAirplane){
                            case 1:
                                ControllerAirplane.createAirplane();
                                break;
                            case 2:
                                ControllerAirplane.deleteAirplane();
                                break;
                            case 3:
                                ControllerAirplane.updateAirplane();
                                break;
                            case 4:
                                ControllerAirplane.listAirplane();
                                break;
                        }
                    }while (optionAirplane != 5);

                    break;

                case 2:
                    do {
                        optionPassenger = Integer.parseInt(JOptionPane.showInputDialog("""
                                1 - Crear passenger
                                2 - Eliminar passenger 
                                3 - Actualizar passenger
                                4 - Listar passenger
                                5 - Salir 
                                """));

                        switch (optionPassenger){
                            case 1:
                                ControllerPassenger.createPassenger();
                                break;
                            case 2:
                                ControllerPassenger.deletePassenger();
                                break;
                            case 3:
                                ControllerPassenger.updatePassenger();
                                break;
                            case 4:
                                ControllerPassenger.listPassenger();
                                break;
                        }
                    }while (optionPassenger != 5);
                    break;

                case 3:
                    do {
                        optionReserv = Integer.parseInt(JOptionPane.showInputDialog("""
                                1 - Crear reservation
                                2 - Eliminar reservation 
                                3 - Actualizar reservation
                                4 - Listar reservation
                                5 - Salir 
                                 """));

                        switch (optionReserv){
                            case 1:
                                ControllerReserv.createReserv();
                                break;
                            case 2:
                                ControllerReserv.deleteReserv();
                                break;
                            case 3:
                                ControllerReserv.updateReserv();
                                break;
                            case 4:
                                ControllerReserv.listReserv();
                                break;
                        }

                    }while (optionReserv != 5);
                    break;

                case 4:
                    do {
                        optionFlight = Integer.parseInt(JOptionPane.showInputDialog("""
                                1 - Crear vuelo
                                2 - Eliminar vuelo 
                                3 - Actualizar vueloflight 
                                4 - Listar vuelo 
                                5 - Salir
                                
                                """));

                        switch (optionFlight){
                            case 1:
                                ControllerFlight.createFlight();
                                break;
                            case 2:
                                ControllerFlight.deleteFlight();
                                break;
                            case 3:
                                ControllerFlight.updateFlight();
                                break;
                            case 4:
                                ControllerFlight.listFlight();
                                break;
                        }
                    }while (optionFlight != 5);
                    break;
            }


        }while (option != 5);


    }
}