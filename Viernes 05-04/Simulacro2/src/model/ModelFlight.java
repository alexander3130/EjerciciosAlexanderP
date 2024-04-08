package model;

import database.ConfigDB;
import entity.Airplane;
import entity.Flight;
import entity.VueloConAvion;
import interfaces.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ModelFlight implements CRUD {
    @Override
    public ArrayList<Object> list() {
        ArrayList<Object> listaDeVuelos = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT flights.*, airplane.model, airplane.capacity FROM flights INNER JOIN airplane ON flights.id_airplane = airplane.id_airplane;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult =  objPrepare.executeQuery();

            while (objResult.next()){
                Flight objFlight = new Flight();
                objFlight.setId(objResult.getInt("id_flight"));
                objFlight.setDestination(objResult.getString("destination"));

                String fechaSalidaStr = objResult.getString("departure_date");
                LocalDate fechaSalida = LocalDate.parse(fechaSalidaStr);
                objFlight.setStart(fechaSalida);

                String horaSalidaStr = objResult.getString("departure_time");
                LocalTime horaSalida = LocalTime.parse(horaSalidaStr);
                objFlight.setTime(horaSalida);

                Airplane objAirplane = new Airplane();
                objAirplane.setModel(objResult.getString("model"));
                objAirplane.setCapacity(objResult.getInt("capacity"));

                VueloConAvion vueloConAvion = new VueloConAvion(objFlight, objAirplane);

                listaDeVuelos.add(vueloConAvion);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaDeVuelos;
    }
    @Override
    public Object create(Object obj) {
        Flight objFlight = (Flight) obj;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "INSERT INTO flights (destination, departure_date, departure_time, id_airplane ) VALUES (?, ?, ?, ?)";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objFlight.getDestination());

            LocalDate fechaSalida = objFlight.getStart();
            java.sql.Date fechaSalidaSQL = java.sql.Date.valueOf(fechaSalida);
            objPrepare.setDate(2, fechaSalidaSQL);


            LocalTime horaSalida = objFlight.getTime();
            java.sql.Time horaSalidaSQL = java.sql.Time.valueOf(horaSalida);
            objPrepare.setTime(3, horaSalidaSQL);

            objPrepare.setInt(4, objFlight.getAirplane_id());


            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            if (objResult.next()){
                objFlight.setId(objResult.getInt(1));
            }


        } catch (SQLException e) {
            System.out.println("Error al insertar el vuelo: " + e.getMessage());
            return null;
        }
        ConfigDB.closeConnection();
        return objFlight;
    }
    @Override
    public boolean update(Object obj) {
        Flight objFlight = (Flight) obj;
        Connection objConexion = ConfigDB.openConnection();
        boolean isUpdate = false;



        try {
            String sql = "UPDATE flights SET destination = ?, departure_date = ?, departure_time = ?, id_airplane = ?  WHERE id_flight = ?;";
            PreparedStatement objPrepare =  objConexion.prepareStatement(sql);



            objPrepare.setString(1, objFlight.getDestination());

            LocalDate fechaSalida = objFlight.getStart();
            java.sql.Date fechaSalidaSQL = java.sql.Date.valueOf(fechaSalida);
            objPrepare.setDate(2, fechaSalidaSQL);


            LocalTime horaSalida = objFlight.getTime();
            java.sql.Time horaSalidaSQL = java.sql.Time.valueOf(horaSalida);
            objPrepare.setTime(3, horaSalidaSQL);

            objPrepare.setInt(4, objFlight.getAirplane_id());
            objPrepare.setInt(5, objFlight.getId());

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
        Flight objFlight;
        VueloConAvion vueloConAvion = new VueloConAvion();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT flights.*, airplane.model, airplane.capacity FROM flights INNER JOIN airplane ON flights.id_airplane = airplane.id_airplane WHERE flights.id_flight = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);


            objPrepare.setInt(1, id);

            ResultSet objResult =  objPrepare.executeQuery();

            if (objResult.next()){
                objFlight = new Flight();
                objFlight.setId(objResult.getInt("id_flight"));
                objFlight.setDestination(objResult.getString("destination"));
                objFlight.setAirplane_id(objResult.getInt("id_airplane"));

                String fechaSalidaStr = objResult.getString("departure_date");
                LocalDate fechaSalida = LocalDate.parse(fechaSalidaStr);
                objFlight.setStart(fechaSalida);

                String horaSalidaStr = objResult.getString("departure_time");
                LocalTime horaSalida = LocalTime.parse(horaSalidaStr);
                objFlight.setTime(horaSalida);

                Airplane objAirplane = new Airplane();
                objAirplane.setModel(objResult.getString("model"));
                objAirplane.setCapacity(objResult.getInt("capacity"));


                vueloConAvion.setAvion(objAirplane);
                vueloConAvion.setVuelo(objFlight);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return vueloConAvion;
    }
    @Override
    public boolean delete(Object obj) {
        boolean isDeleted = false;

        Flight objFlight = (Flight) obj;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "DELETE FROM flights WHERE id_flight = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objFlight.getId());

            int filasAfectadas = objPrepare.executeUpdate();

            if (filasAfectadas > 0){
                isDeleted = true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }



        return isDeleted;
    }
}
