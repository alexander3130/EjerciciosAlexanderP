package controller;

import entity.*;
import model.ModelAirplane;
import model.ModelFlight;
import model.ModelReserv;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ControllerReserv {
    public static void createReserv() {
        ModelFlight objModelFlight = new ModelFlight();
        ModelAirplane objModelAirplane = new ModelAirplane();
        ModelReserv objModelReservacion = new ModelReserv();
        Reservation objReservation = new Reservation();
        int asientoNumero;
        int id_pasajero = Integer.parseInt(JOptionPane.showInputDialog(ControllerPassenger.listPassengerString() + "\n Ingrese el pasajero con quien está asociada la reservacion "));
        int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog(ControllerFlight.listFlightString() + "\n Ingrese el vuelo con quien está asociada la reservacion"));


        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            String fecha_de_reservacion = JOptionPane.showInputDialog("Ingresa la fecha de reservacion (yyyy-MM-dd)");
            LocalDate fecha = LocalDate.parse(fecha_de_reservacion, formatterDate);
            LocalDateTime fechaConHoraCero = fecha.atStartOfDay(); // Establece la hora y el minuto como cero
            objReservation.setFechaReservacion(fechaConHoraCero);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Error en el formato de la fecha, recuerda que debes usar el formato (yyyy-MM-dd)");
            return;
        }

        try {
            asientoNumero = Integer.parseInt((JOptionPane.showInputDialog("Ingrese el numero de asiento que desea reservar")));
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
            return;
        }


// Después de la solicitud y conversión del número de asiento
        boolean asientoReservado = false;

        for (Object reserva : objModelReservacion.list()) {
            ReservacionPasajeroVuelo reservaVuelo = (ReservacionPasajeroVuelo) reserva;
            if (asientoNumero == Integer.parseInt(reservaVuelo.getReservacion().getSeat())) {
                JOptionPane.showMessageDialog(null, "El asiento ya se encuentra reservado");
                asientoReservado = true;
                break; // Salir del bucle si se encuentra una reserva con el mismo asiento
            }
        }


        VueloConAvion objVuelo = (VueloConAvion) objModelFlight.findByID(id_vuelo);
        System.out.println(objVuelo);
        int id_avion = objVuelo.getVuelo().getAirplane_id();


// Verificar si el asiento excede la capacidad del avión o es inválido
        int capacidad = objModelAirplane.obtenerCapacidadDeAsientos(id_avion);
        if (asientoNumero > capacidad) {
            System.out.println(capacidad);
            System.out.println(id_avion);
            JOptionPane.showMessageDialog(null, "El asiento no es válido. Capacidad de este avion: " + capacidad);
            return;
        }

        if (asientoNumero <= 0){
            JOptionPane.showMessageDialog(null, "No se acepta 0 ni numeros negativos ⚠");
            return;
        }


        if (asientoReservado) {
                return;
            } else {
                objReservation.setSeat(Integer.toString(asientoNumero));
            }

            objReservation.setPassenger_id(id_pasajero);
            objReservation.setFlight_id(id_vuelo);
            Reservation objReservationString = (Reservation) objModelReservacion.create(objReservation);
            JOptionPane.showMessageDialog(null, "Reservación creada con exito: " + objReservationString);
    };
    public static void listReserv(){
        ModelReserv objReservacion = new ModelReserv();

        String listaDeReservas = "LISTA DE RESERVAS \n";
        Passenger objPassenger;

        for (Object reserva: objReservacion.list()){
            listaDeReservas += (ReservacionPasajeroVuelo) reserva + "\n";
        }

        JOptionPane.showMessageDialog(null, listaDeReservas);
    }
    public static String listReservString(){
        ModelReserv objReservacion = new ModelReserv();

        String listaDeReservas = "LISTA DE RESERVAS \n";

        for (Object reserva: objReservacion.list()){
            listaDeReservas += (ReservacionPasajeroVuelo) reserva + "\n";
        }

        return  listaDeReservas;
    }
    public static void updateReserv(){
        ModelFlight objModelFlight = new ModelFlight();
        ModelAirplane objModelAirplane = new ModelAirplane();
        ModelReserv objModelReservacion = new ModelReserv();
        Reservation objReservation = new Reservation();
        int asientoNumero;


        int idReservacion =  Integer.parseInt(JOptionPane.showInputDialog(listReservString() + "\n Ingrese el id de la reservacion que desea editar"));


/*        ReservacionPasajeroVuelo objReservacionEspecifica =  (ReservacionPasajeroVuelo) objModelReservacion.findByID(idReservacion);
        System.out.println(objReservacionEspecifica.getReservacion().getId_reservacion());*/

        ReservacionPasajeroVuelo objReservacionEspecifica = (ReservacionPasajeroVuelo) objModelReservacion.findByID(idReservacion);




        int id_pasajero = Integer.parseInt(JOptionPane.showInputDialog(ControllerPassenger.listPassengerString() + "\n Ingrese el pasajero con quien está asociada la reservacion", objReservacionEspecifica.getReservacion().getPassenger_id() ));

        int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog(ControllerFlight.listFlightString() + "\n Ingrese el vuelo con quien está asociada la reservacion", objReservacionEspecifica.getReservacion().getFlight_id()));


        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            String fecha_de_reservacion = JOptionPane.showInputDialog("Ingresa la fecha de reservacion (yyyy-MM-dd)", objReservacionEspecifica.getReservacion().getFechaReservacion().toString().substring(0,  10));
            LocalDate fecha = LocalDate.parse(fecha_de_reservacion, formatterDate);
            LocalDateTime fechaConHoraCero = fecha.atStartOfDay(); // Establece la hora y el minuto como cero
            objReservation.setFechaReservacion(fechaConHoraCero);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Error en el formato de la fecha, recuerda que debes usar el formato (yyyy-MM-dd)");
            return;
        }

        try {
            asientoNumero = Integer.parseInt((JOptionPane.showInputDialog("Ingrese el numero de asiento que desea reservar", objReservacionEspecifica.getReservacion().getSeat())));
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
            return;
        }


// Después de la solicitud y conversión del número de asiento
        boolean asientoReservado = false;

        for (Object reserva : objModelReservacion.list()) {
            ReservacionPasajeroVuelo reservaVuelo = (ReservacionPasajeroVuelo) reserva;
            if (asientoNumero == Integer.parseInt(reservaVuelo.getReservacion().getSeat())) {
                JOptionPane.showMessageDialog(null, "El asiento ya se encuentra reservado");
                asientoReservado = true;
                break; // Salir del bucle si se encuentra una reserva con el mismo asiento
            }
        }


        VueloConAvion objVuelo = (VueloConAvion) objModelFlight.findByID(id_vuelo);
        System.out.println(objVuelo);
        int id_avion = objVuelo.getVuelo().getAirplane_id();


// Verificar si el asiento excede la capacidad del avión o es inválido
        int capacidad = objModelAirplane.obtenerCapacidadDeAsientos(id_avion);
        if (asientoNumero > capacidad) {
            System.out.println(capacidad);
            System.out.println(id_avion);
            JOptionPane.showMessageDialog(null, "El asiento no es válido. Capacidad de este avion: " + capacidad);
            return;
        }

        if (asientoNumero <= 0){
            JOptionPane.showMessageDialog(null, "No se acepta 0 ni numeros negativos ⚠");
            return;
        }


        if (asientoReservado) {
            return;
        } else {
            objReservation.setSeat(Integer.toString(asientoNumero));
        }

        objReservation.setId(idReservacion);
        objReservation.setPassenger_id(id_pasajero);
        objReservation.setFlight_id(id_vuelo);
        Boolean objReservacionString = (boolean) objModelReservacion.update(objReservation);
        JOptionPane.showMessageDialog(null, "Reservación actualizada con exito");

    }
    public static void deleteReserv(){
        ModelReserv objModelReservacion = new ModelReserv();
        Reservation objReservation = new Reservation();

        int id = Integer.parseInt(JOptionPane.showInputDialog(listReservString() + "\n Ingrese el id del avion que desea eliminar"));

        objReservation.setId(id);


        boolean validacion = objModelReservacion.delete(objReservation);;

        if (validacion){
            JOptionPane.showMessageDialog(null, "Reservacion  eliminada con éxito" );
        }

    }

}
