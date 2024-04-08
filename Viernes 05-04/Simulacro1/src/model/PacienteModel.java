package model;

import database.CRUD;
import database.ConfigDB;
import entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteModel implements CRUD {
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objP = (Paciente) obj;
        try {
            String sql = "INSERT INTO paciente(nombre, apellidos, fecha_nacimiento, documento_identidad) values(?,?,?,?);";
            PreparedStatement objPS = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPS.setString(1, objP.getNombre());
            objPS.setString(2, objP.getApellidos());
            objPS.setDate(3, objP.getFecha_nacimiento());
            objPS.setString(4, objP.getDocumento_identidad());
            objPS.execute();
            ResultSet objResult = objPS.getGeneratedKeys();
            while (objResult.next()) {
                objP.setId_paciente(objResult.getInt(1));
            }
            objPS.close();
            JOptionPane.showMessageDialog(null, "Paciente guardado correctamente!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objP;
    }

    public boolean update(Object obj) {
        boolean isUpdate = false;
        Paciente objP = (Paciente) obj;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "UPDATE paciente SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, documento_identidad = ? WHERE id_paciente = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setString(1, objP.getNombre());
            objPS.setString(2, objP.getApellidos());
            objPS.setDate(3, (Date) objP.getFecha_nacimiento());
            objPS.setString(4, objP.getDocumento_identidad());
            objPS.setInt(5, objP.getId_paciente());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Paciente actualizado correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    public boolean delete(Object obj) {
        boolean isDelete = false;
        Paciente objP = (Paciente) obj;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "DELETE FROM paciente WHERE id_paciente = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, objP.getId_paciente());
            int totalAffected = objPS.executeUpdate();
            if (totalAffected > 0) {
                isDelete = true;
                JOptionPane.showMessageDialog(null, "Paciente eliminado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }

    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listPacientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM paciente ORDER BY paciente.id_paciente ASC;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                Paciente objP = new Paciente();
                objP.setId_paciente(objResult.getInt("id_paciente"));
                objP.setNombre(objResult.getString("nombre"));
                objP.setApellidos(objResult.getString("apellidos"));
                objP.setFecha_nacimiento(objResult.getDate("fecha_nacimiento"));
                objP.setDocumento_identidad(objResult.getString("fecha_nacimiento"));
                listPacientes.add(objP);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPacientes;
    }

    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = null;
        try {
            String sql = "SELECT * FROM paciente WHERE id_paciente = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setInt(1, id);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                objPaciente = new Paciente();
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getDate("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }
    public Object findByCC(String cc) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = new Paciente();
        try {
            String sql = "SELECT * FROM paciente WHERE documento_identidad = ?;";
            PreparedStatement objPS = objConnection.prepareStatement(sql);
            objPS.setString(1, cc);
            ResultSet objResult = objPS.executeQuery();
            while (objResult.next()) {
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getDate("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }

}
