package model;

import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD {
    public Object insert(Object obj) {
        //Establecer Conexión con la base de datos
        Connection objConnection = ConfigDB.openConnection();
        //Se cambia la clase del objeto recibido
        Medico objM = (Medico) obj;
        //Estructuración y ejecución comando SQL
        try {
            String sql = "insert into medico(nombre, apellidos, id_especialidad) values(?, ?, ?);";
            PreparedStatement objPS = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPS.setString(1, objM.getNombre());
            objPS.setString(2, objM.getApellidos());
            objPS.setInt(3, objM.getId_especialidad());
            objPS.execute();
            ResultSet objResult = objPS.getGeneratedKeys();
            while (objResult.next()) {
                objM.setId_especialidad(objResult.getInt(1));
            }
            objPS.close();
            JOptionPane.showMessageDialog(null, "Medico guardado correctamente!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objM;
    }

    public boolean update(Object obj) {
        boolean isUpdate = false;
        Medico objM = (Medico) obj;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "UPDATE medico SET nombre = ?, apellidos = ?, id_especialidad = ? WHERE id_medico = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setString(1, objM.getNombre());
            objPS.setString(2, objM.getApellidos());
            objPS.setInt(3, objM.getId_especialidad());
            objPS.setInt(4, objM.getId_medico());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Medico actualizado correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    public boolean delete(Object obj) {
        boolean isDelete = false;
        Medico objM = (Medico) obj;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "DELETE FROM medico WHERE id_medico=?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, objM.getId_medico());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isDelete = true;
                JOptionPane.showMessageDialog(null, "Medico eliminado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }

    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listMedicos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM medico INNER JOIN especialidad on medico.id_especialidad = especialidad.id_especialidad ORDER BY medico.id_medico ASC;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                Medico objM = new Medico();
                Especialidad objE = new Especialidad();
                objM.setId_medico(objResult.getInt("medico.id_medico"));
                objM.setNombre(objResult.getString("medico.nombre"));
                objM.setApellidos(objResult.getString("medico.apellidos"));
                objM.setId_especialidad(objResult.getInt("medico.id_especialidad"));
                objE.setId_especialidad(objResult.getInt("especialidad.id_especialidad"));
                objE.setNombre(objResult.getString("especialidad.nombre"));
                objE.setDescripcion(objResult.getString("especialidad.descripcion"));

                objM.setObjE(objE);
                listMedicos.add(objM);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listMedicos;
    }

    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico =null;
        try {
            String sql = "SELECT * FROM medico INNER JOIN especialidad on medico.id_especialidad = especialidad.id_especialidad WHERE id_medico = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, id);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                objMedico = new Medico();
                Especialidad objE = new Especialidad();
                objMedico.setId_medico(objResult.getInt("medico.id_medico"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellidos(objResult.getString("medico.apellidos"));
                objMedico.setId_especialidad(objResult.getInt("medico.id_especialidad"));

                objE.setId_especialidad(objResult.getInt("especialidad.id_especialidad"));
                objE.setNombre(objResult.getString("especialidad.nombre"));
                objE.setDescripcion(objResult.getString("especialidad.descripcion"));

                objMedico.setObjE(objE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objMedico;
    }

    public List<Object> findyEsp(int id) {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listM = new ArrayList<>();

        try {
            String sql = "SELECT * FROM medico INNER JOIN especialidad on medico.id_especialidad = especialidad.id_especialidad WHERE medico.id_especialidad = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, id);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                Medico objMedico = new Medico();
                Especialidad objE = new Especialidad();
                objMedico.setId_medico(objResult.getInt("medico.id_medico"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellidos(objResult.getString("medico.apellidos"));
                objMedico.setId_especialidad(objResult.getInt("medico.id_especialidad"));

                objE.setId_especialidad(objResult.getInt("especialidad.id_especialidad"));
                objE.setNombre(objResult.getString("especialidad.nombre"));
                objE.setDescripcion(objResult.getString("especialidad.descripcion"));

                objMedico.setObjE(objE);
                listM.add(objMedico);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listM;
    }
}
