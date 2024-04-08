package model;

import database.ConfigDB;

import entity.Passenger;
import interfaces.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelPassenger implements CRUD {
    @Override
    public ArrayList<Object> list() {
        ArrayList<Object> listaDePasajeros = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM passengers";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult =  objPrepare.executeQuery();

            while (objResult.next()){
                Passenger objPassenger = new Passenger();
                objPassenger.setId(objResult.getInt("id_passenger"));
                objPassenger.setName(objResult.getString("first_name"));
                objPassenger.setLastname(objResult.getString("last_name"));
                objPassenger.setId_document(objResult.getString("identity_document"));


                listaDePasajeros.add(objPassenger);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaDePasajeros;
    }
    @Override
    public Object create(Object obj) {
        Passenger objPassenger = (Passenger) obj;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "INSERT INTO passengers (first_name, last_name, identity_document) VALUES (?, ?, ?)";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objPassenger.getName());
            objPrepare.setString(2, objPassenger.getLastname());
            objPrepare.setString(3, objPassenger.getId_document());


            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            if (objResult.next()){
                objPassenger.setId(objResult.getInt(1));
            }


        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());

        }
        ConfigDB.closeConnection();

        return objPassenger;
    }
    @Override
    public boolean update(Object obj) {
        Passenger objPassenger = (Passenger) obj;
        Connection objConexion = ConfigDB.openConnection();
        boolean isUpdate = false;

        try {
            String sql = "UPDATE passengers SET first_name = ?, last_name = ?, identity_document = ? WHERE id_passenger = ?;";
            PreparedStatement objPrepare =  objConexion.prepareStatement(sql);

            objPrepare.setString(1, objPassenger.getName());
            objPrepare.setString(2, objPassenger.getLastname());
            objPrepare.setString(3, objPassenger.getId_document());
            objPrepare.setInt(4, objPassenger.getId());

            int filasAfectadas =  objPrepare.executeUpdate();

            if (filasAfectadas > 0){
                isUpdate = true;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return isUpdate;
    }
    @Override
    public boolean delete(Object obj) {
        boolean isDeleted = false;

        Passenger objPassenger = (Passenger) obj;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "DELETE FROM passengers WHERE id_passenger = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objPassenger.getId());

            int filasAfectadas = objPrepare.executeUpdate();

            if (filasAfectadas > 0){
                isDeleted = true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }


        return isDeleted;

    }

    public Object findByID(int id){
        Passenger objPassenger = new Passenger();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM passengers where id_passenger = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult =  objPrepare.executeQuery();

            while (objResult.next()){
                objPassenger.setId(objResult.getInt("id_passenger"));
                objPassenger.setName(objResult.getString("first_name"));
                objPassenger.setLastname(objResult.getString("last_name"));
                objPassenger.setId_document(objResult.getString("identity_document"));

            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objPassenger;
    }
}
