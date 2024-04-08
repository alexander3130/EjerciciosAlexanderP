package model;

import database.ConfigDB;
import entity.Tienda;
import interfaces.CRUD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelTienda implements CRUD {
    @Override
    public ArrayList<Object> list() {
        ArrayList<Object> listToTienda = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM tienda";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult =  objPrepare.executeQuery();

            while (objResult.next()){
                Tienda objTienda = new Tienda();
                objTienda.setId(objResult.getInt("id_tienda"));
                objTienda.setNombre(objResult.getString("nombre"));
                objTienda.setUbicacion(objResult.getString("ubicacion"));


                listToTienda.add(objTienda);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listToTienda;
    }

    @Override
    public Object create(Object obj) {
        return null;
    }
    @Override
    public boolean update(Object obj) {
        return false;
    }
    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
