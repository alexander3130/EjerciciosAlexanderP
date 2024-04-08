package model;

import database.ConfigDB;
import entity.*;
import interfaces.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ModelReserv implements CRUD {
    @Override
    public ArrayList<Object> list() {
        ArrayList<Object> listaDeReservaciones = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

    try {
        String sql = "SELECT reservations.*, passengers.*, flights.* " +
                "FROM reservations " +
                "INNER JOIN passengers ON passengers.id_passenger = reservations.id_passenger " +
                "INNER JOIN passengers ON passengers.id_passenger = reservations.id_passenger " +
                "INNER JOIN flights ON flights.id_flight = reservations.id_flight";

        PreparedStatement objPrepare = objConnection.prepareStatement(sql);


       ResultSet objResult =  objPrepare.executeQuery();


        while (objResult.next()){
            Reservation objReservation = new Reservation();
            objReservation.setId(objResult.getInt("id_reservation"));
            objReservation.setSeat(objResult.getString("seat"));

            Passenger objPassenger = new Passenger();
            objPassenger.setName(objResult.getString("first_name"));
            objPassenger.setLastname(objResult.getString("last_name"));
            objPassenger.setId_document(objResult.getString("identity_document"));

            Flight objFlight = new Flight();
            objFlight.setId(objResult.getInt("id_flight"));
            objFlight.setDestination(objResult.getString("destination"));

            String fechaSalidaString = objResult.getString("departure_date");
            LocalDate fechaSalida = LocalDate.parse(fechaSalidaString);
            objFlight.setStart(fechaSalida);


            String horaSalidaString = objResult.getString("departure_time");
            LocalTime horaSalida = LocalTime.parse(horaSalidaString);
            objFlight.setTime(horaSalida);


            ReservacionPasajeroVuelo objRPA = new ReservacionPasajeroVuelo(objReservation, objPassenger, objFlight);

            listaDeReservaciones.add(objRPA);

        }



    } catch (Exception e) {
        System.out.println("Error en model reservacion :" + e.getMessage());
    }
        ConfigDB.closeConnection();
        return listaDeReservaciones;
    }
    @Override
    public Object create(Object obj) {
        Reservation objReservation = (Reservation) obj;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "INSERT INTO reservations (id_passenger, id_flight, reservation_date, seat ) VALUES (?, ?, ?, ?)";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objReservation.getPassenger_id());
            objPrepare.setInt(2, objReservation.getFlight_id());


            LocalDateTime fecha_reservacion = objReservation.getFechaReservacion();
            java.sql.Timestamp fecha_reservacion_sql = java.sql.Timestamp.valueOf(fecha_reservacion);
            objPrepare.setTimestamp(3, fecha_reservacion_sql);
            objPrepare.setString(4, objReservation.getSeat());

            int filasAfectadas = objPrepare.executeUpdate();

            if (filasAfectadas > 0) {
                ResultSet objResult = objPrepare.getGeneratedKeys();
                if (objResult.next()) {
                    objReservation.setId(objResult.getInt(1));
                }
            } else {
                // Manejar el caso en el que no se insertó ninguna fila
                System.out.println("Error al insertar la reserva: no se insertaron filas");
                return null;
            }



            ResultSet objResult = objPrepare.getGeneratedKeys();

            if (objResult.next()){
                objReservation.setId(objResult.getInt(1));
            }


        }catch (SQLException e) {
            System.out.println("Error al insertar el vuelo: " + e.getMessage());
            return null;
        }

        ConfigDB.closeConnection();
        return objReservation;
    }
    @Override
    public boolean update(Object obj) {
        Reservation objReservation = (Reservation) obj;
        Connection objConexion = ConfigDB.openConnection();
        boolean isUpdate = false;

        try {
            String sql = "UPDATE reservations SET id_passenger = ?, id_flight = ?, reservation_date = ?, seat = ?  WHERE id_reservation = ?;";

            PreparedStatement objPrepare = objConexion.prepareStatement(sql);

            objPrepare.setInt(1, objReservation.getPassenger_id());
            objPrepare.setInt(2, objReservation.getFlight_id());


            LocalDateTime fecha_reservacion = objReservation.getFechaReservacion();
            java.sql.Timestamp fecha_reservacion_sql = java.sql.Timestamp.valueOf(fecha_reservacion);
            objPrepare.setTimestamp(3, fecha_reservacion_sql);

            objPrepare.setString(4, objReservation.getSeat());


            objPrepare.setInt(5, objReservation.getId());


            int filasAfectadas =  objPrepare.executeUpdate();

            if (filasAfectadas > 0){
                isUpdate = true;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        ConfigDB.closeConnection();
        return isUpdate;
    }
    public Object findByID(int id){
        Reservation objReservation;
        ReservacionPasajeroVuelo RPA = new ReservacionPasajeroVuelo();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT reservations.*, passengers.*, flights.* " +
                    "FROM reservations " +
                    "INNER JOIN passengers ON passenger.id_passenger = reservations.id_passenger " +
                    "INNER JOIN flights ON flights.id_flight = reservations.id_flight " +
                    "WHERE id_reservacion = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);



            ResultSet objResult =  objPrepare.executeQuery();

            if (objResult.next()){
                objReservation = new Reservation();
                objReservation.setId(objResult.getInt("id_reservation"));
                objReservation.setSeat(objResult.getString("seat"));
                objReservation.setPassenger_id(objResult.getInt("id_passenger"));
                objReservation.setFlight_id(objResult.getInt("id_flight_id"));


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String fechaReservaString = objResult.getString("reservation_date");
                LocalDateTime fechaReserva = LocalDateTime.parse(fechaReservaString, formatter);
                objReservation.setFechaReservacion(fechaReserva);





                Passenger objPassenger = new Passenger();
                objPassenger.setName(objResult.getString("first_name"));
                objPassenger.setLastname(objResult.getString("last_name"));
                objPassenger.setId_document(objResult.getString("identity_document"));

                Flight objFlight = new Flight();
                objFlight.setDestination(objResult.getString("destination"));


                String fechaSalidaString = objResult.getString("departure_date");
                LocalDate fechaSalida = LocalDate.parse(fechaSalidaString);
                objFlight.setStart(fechaSalida);


                String horaSalidaString = objResult.getString("departure_time");
                LocalTime horaSalida = LocalTime.parse(horaSalidaString);
                objFlight.setTime(horaSalida);


                 RPA = new ReservacionPasajeroVuelo(objReservation, objPassenger, objFlight);

            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return RPA;
    }
    @Override
    public boolean delete(Object obj) {
        boolean isDeleted = false;

        Reservation objReservation = (Reservation) obj;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "DELETE FROM reservations WHERE id_reservation = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objReservation.getId());

            int filasAfectadas = objPrepare.executeUpdate();
            System.out.println(filasAfectadas);

            if (filasAfectadas > 0){
                isDeleted = true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }


        return isDeleted;
    }




}
