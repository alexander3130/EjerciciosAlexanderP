package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuthorModel implements CRUD {


    @Override
    public Object insert(Object obj) {
        //1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. Castear el objeto a Author para poder acceder a los metodos

        Author objAuthor = (Author) obj;
        try {
            //3. Crear sentencia SQL
            String sql = "INSERT INTO author(name,nationality) values(?,?) ;";
            //4. Preparar el Statement
            //Adicional le pasamos como parametro la generacion de la keys
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Asignar los values "?"
            objPrepared.setString(1, objAuthor.getName());
            objPrepared.setString(2, objAuthor.getNationality());

            //6. Ejecutamos el Query
            objPrepared.execute();

            //7. obtener el resultado del Query
            ResultSet objResult = objPrepared.getGeneratedKeys();

            //Recorremos la especie de lista que nos trae el Result y le generamos un nuevo id

            while (objResult.next()) {
                objAuthor.setId(objResult.getInt(1));
            }

            //8. Cerramos el preparedStatement
            objPrepared.close();
            JOptionPane.showMessageDialog(null, "Author insert successful");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //9. Cerramos la conexion
        ConfigDB.closeConneciton();

        //PREGUNTAR PARA QUE SE RETORNA EL AUTHOR

        return objAuthor;
    }

    @Override
    public boolean update(Object obj) {

        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Creamos bandera para ver si se actualizó correctamente
        boolean isUpdate = false;

        //3.Casteamos el objeto para poder acceder a los metodos
        Author objAuthor = (Author) obj;

        try {
            //4. Creamos la sentencia SQL
            String sql = "UPDATE author SET name = ? , nation WHERE id = ?;";
            //5. Preparamos el statement, el stetemen sale de la connexion
            /**
             * El metodo prepareStatement recibe dos parametro
             * @sql : Es la sentencia sql
             * @PreparedStatement : Sive para que me muestre las llaves o ids, por si de pronto se le generó una nueva
             */

            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //6. Le damos valor a los signos de interrogacion o " Parametros de Query "
            objPrepared.setString(1, objAuthor.getName());
            objPrepared.setString(2, objAuthor.getNationality());

            //7. Ejecutamos el query
            //Y execute nos devuelve si se afecto y cuantas filas se actualizaron
            int rowAffect = objPrepared.executeUpdate();
            if (rowAffect > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Se actualizo");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //8. Cerramos  la conexion
        ConfigDB.closeConneciton();


        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Convertir el objeto a la entidad
        Author objAuthor = (Author) obj;
        //2. Variable booleana para medir el estado de la eliminación
        boolean isDelete = false;
        //3. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //try-catch
        try {

            //4. Escribir la sentencia SQL
            String sql = "DELETE FROM author WHERE id = ?; ";
            //5. Preparamos el statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            //6. Asignamos el valor al ?
            objPrepared.setInt(1, objAuthor.getId());
            //7. ExecuteUpdate devuelve la cantidad filas afectadas por la sentencia SQL ejecutada.
            int numRows = objPrepared.executeUpdate();
            // Si el numero de columnas afectadas es mayor a 0 cambiamos el valor de la bandera a true
            if (numRows > 0) {
                isDelete = true;
                JOptionPane.showMessageDialog(null, "Delete successful");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //8. Cerramos la conexion
        ConfigDB.closeConneciton();

        return false;
    }

    @Override
    public List<Object> findAll() {
        //1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. Inicializar la lista donde se guardaran los registros que devuelve la BD
        List<Object> listAuthors = new ArrayList<>();
        //Todo puede fallar entonces usar try-catch
        try {

            //3. Escribir la sentencia SQL
            String sql = "SELECT * FROM author ORDER BY author.id ASC;";
            //4. Utilizar PrepareStatement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            //5. Ejecutar el Query o el prepare
            ///1. Se usa resultSet porque es la clase predifina de JDBC para guardar la respuesta de la base datos
            ResultSet objResult = objPrepared.executeQuery();

            //6. Obtener los resultados
            // Resulset no es una lista, para saber si vienen registros o datos de usa el metodo NEXT, mientras next, mientras haya un siguiente el va a hacer una logica. solo se usa para listar
            //[Juan,camilo,Ken]
            while (objResult.next()) {
                Author objAuthor = new Author();
//Llenamos nuestro objeto coder con lo que devuelve la base de datos(ResulSet)
                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));
                //Finalmente agregamos el coder que llenamos con lo que trajo el resulset a la lista de coders
                listAuthors.add(objAuthor);
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //Cerramos la conexion
        ConfigDB.closeConneciton();

        //Retornamos la lista de autores
        return listAuthors;
    }

    @Override
    public Object findByID(int id) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //Instanciamos un autor en null para luego darle el valor con la consulta
        Author objAuthor = null;
        //try-catch
        try{

            //2. Sentencia SQL
            String  sql = "SELECT * FROM author WHERE id = ?;";
            //3. Preparar el statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            //4. Damos valor al: ?
            objPrepared.setInt(1,id);
            //5. Ejecutamos el query ExecuteQuery = todos los registros que trajo la base de datos
            ResultSet objResult = objPrepared.executeQuery();
            //6. Mientras haya un registro siguiente entonces WHILE
            while(objResult.next()){

                //instanciamos el author creado anteriormente
                objAuthor = new Author();
                //Llenamos ese coder con los datos del objPrepared
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));
            }
        }catch(Exception e ){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        //7. Cerrar la conexión
        ConfigDB.closeConneciton();
        //Retornamos en objAuthor que nos trajo la consulta
        return objAuthor;
    }
}
