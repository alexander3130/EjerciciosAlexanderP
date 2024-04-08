package model;

import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModel implements CRUD {
    public Object insert(Object obj) {
        //Establecer Conexión con la base de datos
        Connection objConnection = ConfigDB.openConnection();
        //Se cambia la clase del objeto recibido
        Especialidad objE = (Especialidad) obj;
        //Estructuración y ejecución comando SQL
        try {
            String sql = "insert into especialidad(nombre,descripcion) values(?,?);";
            PreparedStatement objPS = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPS.setString(1, objE.getNombre());
            objPS.setString(2, objE.getDescripcion());
            objPS.execute();
            ResultSet objResult = objPS.getGeneratedKeys();
            while (objResult.next()) {
                objE.setId_especialidad(objResult.getInt(1));
            }
            objPS.close();
            JOptionPane.showMessageDialog(null, "Especialidad guardada correctamente!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objE;
    }

    public boolean update(Object obj) {
        boolean isUpdate = false;
        Especialidad objE = (Especialidad) obj;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "UPDATE especialidad SET nombre = ?, descripcion = ? WHERE id_especialidad = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setString(1, objE.getNombre());
            objPS.setString(2, objE.getDescripcion());
            objPS.setInt(3, objE.getId_especialidad());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Especialidad actualizada correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    public boolean delete(Object obj) {
        boolean isDelete = false;
        Especialidad objE = (Especialidad) obj;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "DELETE FROM especialidad WHERE id_especialidad = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, objE.getId_especialidad());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isDelete = true;
                JOptionPane.showMessageDialog(null, "Especialidad eliminada");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }

    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listEspecilidades = new ArrayList<>();
        try {
            String sql = "SELECT * FROM especialidad ORDER BY especialidad.id_especialidad ASC;";
            /*"SELECT * FROM cita INNER JOIN medico on cita.id_medico = medico.id_medico"*/
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()){
                Especialidad objE = new Especialidad();
                objE.setId_especialidad(objResult.getInt("id_especialidad"));
                objE.setNombre(objResult.getString("nombre"));
                objE.setDescripcion(objResult.getString("descripcion"));
                listEspecilidades.add(objE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listEspecilidades;
    }

    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = null;
        try {
            String sql = "SELECT * FROM especialidad WHERE id_especialidad = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, id);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                objEspecialidad = new Especialidad();
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objEspecialidad;
    }

}
