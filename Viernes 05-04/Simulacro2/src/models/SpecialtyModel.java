package models;

import database.CRUD;
import database.ConfigDB;
import entities.Specialty;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Specialty objSpecialty =  (Specialty) obj;

        try{
            //Se prepara el sql string con la sentencia
            String sql = "INSERT INTO specialty(specialty_name,description) VALUES (?,?)";
            //En un objeto tipo prepareStatement preparamos el objeto desde el metodo de la conexion
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Asignamos los ? desde el objeto prepare con el metodo .setString o setInt etc
            objPrepare.setString(1, objSpecialty.getSpecialty_name());
            objPrepare.setString(2, objSpecialty.getDescription());

            // Desde el objeto de prepare lanzamos el metodo execute
            objPrepare.execute();

            // En un objeto tipo RestultSet guardamos el objPrepare con el metodo para obtener las Keys generadas: getGeneratedKeys
            ResultSet objResult = objPrepare.getGeneratedKeys();
            // Hacemos el while que revisa el resultado y le setea al objeto base del modelo el id que se genero

            while(objResult.next()){
                objSpecialty.setId_specialty(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "La especialidad fue guardada con exito!");
        }catch (SQLException e){
            System.out.println("ERROR >>> "+e.getMessage());
        }
        ConfigDB.closeConneciton();
        return objSpecialty;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();

        List<Object> listSpecialties = new ArrayList<>();

        try{
            String sql = "SELECT * FROM specialty";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Specialty objSpecialty = new Specialty();

                objSpecialty.setId_specialty(objResult.getInt("id_specialty"));
                objSpecialty.setSpecialty_name(objResult.getString("specialty_name"));
                objSpecialty.setDescription(objResult.getString("description"));

                listSpecialties.add(objSpecialty);
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> "+e.getMessage());
        }
        ConfigDB.closeConneciton();
        return listSpecialties;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Specialty objSpecialty = (Specialty) obj;

        boolean isUpdated = false;
        String sql = "UPDATE specialty SET specialty_name=?, description=? WHERE id_specialty = ?;";

        try{
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objSpecialty.getId_specialty());
            objPrepare.setString(2, objSpecialty.getSpecialty_name());
            objPrepare.setString(3, objSpecialty.getDescription());
            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "El registro fue actualizado correctamente. ");
            }


        }catch(SQLException e){
            System.out.println("ERROR >>> "+ e.getMessage());
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        boolean isDeleted = false;
        Connection objConnection = ConfigDB.openConnection();
        Specialty objSpecialty = (Specialty) obj;

        String sql = "DELETE FROM specialty WHERE id_specialty = ?;";

        try{
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objSpecialty.getId_specialty());
            int numRows = objPrepare.executeUpdate();
            if (numRows>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "La especialidad fue eliminada.");
            }

        }catch(SQLException e){
            System.out.println("ERROR >>> "+ e.getMessage());
        }
        ConfigDB.closeConneciton();
        return isDeleted;
    }
}
