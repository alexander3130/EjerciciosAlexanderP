package model;

import database.ConfigDB;
import entity.Airplane;
import interfaces.CRUD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelAirplane implements CRUD {
    @Override
    public ArrayList<Object> list() {
        ArrayList<Object> listaDeAviones = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM airplane";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult =  objPrepare.executeQuery();

            while (objResult.next()){
                Airplane objAirplane = new Airplane();
                objAirplane.setId(objResult.getInt("id_airplane"));
                objAirplane.setCapacity(objResult.getInt("capacity"));
                objAirplane.setModel(objResult.getString("model"));


                listaDeAviones.add(objAirplane);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaDeAviones;
    }
    @Override
    public Object create(Object obj) {
        Airplane objAirplane = (Airplane) obj;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "INSERT INTO airplane (model, capacity) VALUES (?, ?)";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objAirplane.getModel());
            objPrepare.setInt(2, objAirplane.getCapacity());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            if (objResult.next()){
                objAirplane.setId(objResult.getInt(1));
            }


        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());

        }
        ConfigDB.closeConnection();
        return objAirplane;
    }
    @Override
    public boolean update(Object obj) {
        Airplane objAirplane = (Airplane) obj;
        Connection objConexion = ConfigDB.openConnection();
        boolean isUpdate = false;

        try {
            String sql = "UPDATE airplane SET model = ?, capacity = ? WHERE id_airplane = ?;";
            PreparedStatement objPrepare =  objConexion.prepareStatement(sql);

            objPrepare.setString(1, objAirplane.getModel());
            objPrepare.setInt(2, objAirplane.getCapacity());
            objPrepare.setInt(3, objAirplane.getId());

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

        Airplane objAirplane = (Airplane) obj;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "DELETE FROM airplane WHERE id_airplane = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objAirplane.getId());

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
        Airplane objAirplane = new Airplane();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM airplane where id_airplane = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult =  objPrepare.executeQuery();

            while (objResult.next()){
                objAirplane.setId(objResult.getInt("id_airplane"));
                objAirplane.setCapacity(objResult.getInt("capacity"));
                objAirplane.setModel(objResult.getString("model"));


            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objAirplane;
    }
    public int obtenerCapacidadDeAsientos(int id){
        Connection objConnection = ConfigDB.openConnection();
        int capacidadEspecifica = 0;

        try {
            String sql = "SELECT * FROM airplane where id_airplane = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                 capacidadEspecifica = objResult.getInt("capacity");

            };



        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en model avion obtenerCapacidadDeAsientos " + e.getMessage());
        }

        return capacidadEspecifica;

    };

}
