package model;

import database.ConfigDB;

import entity.Cliente;
import interfaces.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelCliente implements CRUD {
    @Override
    public ArrayList<Object> list() {
        ArrayList<Object> listToClients = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM cliente";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult =  objPrepare.executeQuery();

            while (objResult.next()){
                Cliente objCliente = new Cliente();
                objCliente.setId(objResult.getInt("id_cliente"));
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));
                objCliente.setEmail(objResult.getString("email"));


                listToClients.add(objCliente);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listToClients;
    }
    @Override
    public Object create(Object obj) {
        Cliente objCliente = (Cliente) obj;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "INSERT INTO cliente (nombre, apellido, email) VALUES (?, ?, ?)";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objCliente.getNombre());
            objPrepare.setString(2, objCliente.getApellido());
            objPrepare.setString(3, objCliente.getEmail());


            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            if (objResult.next()){
                objCliente.setId(objResult.getInt(1));
            }


        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());

        }
        ConfigDB.closeConnection();

        return objCliente;
    }
    @Override
    public boolean update(Object obj) {
        Cliente objCliente = (Cliente) obj;
        Connection objConexion = ConfigDB.openConnection();
        boolean isUpdate = false;

        try {
            String sql = "UPDATE cliente SET nombre = ?, apellido = ?, email = ? WHERE id_cliente = ?;";
            PreparedStatement objPrepare =  objConexion.prepareStatement(sql);

            objPrepare.setString(1, objCliente.getNombre());
            objPrepare.setString(2, objCliente.getApellido());
            objPrepare.setString(3, objCliente.getEmail());
            objPrepare.setInt(4, objCliente.getId());

            int rowsAffected =  objPrepare.executeUpdate();

            if (rowsAffected > 0){
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

        Cliente objCliente = (Cliente) obj;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "DELETE FROM cliente WHERE id_cliente = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objCliente.getId());

            int rowsAffected = objPrepare.executeUpdate();

            if (rowsAffected > 0){
                isDeleted = true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }


        return isDeleted;

    }

    public Object findByID(int id){
        Cliente objCliente = new Cliente();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM cliente where id_cliente = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult =  objPrepare.executeQuery();

            while (objResult.next()){
                objCliente.setId(objResult.getInt("id_cliente"));
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));
                objCliente.setEmail(objResult.getString("email"));

            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objCliente;
    }
}

