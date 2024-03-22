package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;
import entity.Book;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. Castear el objeto

        Book objBook = (Book) obj;
        try {
            //3. Crear el SQL
            String sql = "INSERT INTO book(title, publication_year,price,id_author) values (?,?,?,?);";
            //4. Preparar el statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. Asignar los ?
            objPrepared.setString(1, objBook.getTitle());
            objPrepared.setString(2, objBook.getYearPublication());
            objPrepared.setDouble(3, objBook.getPrice());
            objPrepared.setInt(4, objBook.getIdAuthor());

            //6. Ejecutamos el Query
            objPrepared.execute();
            //7. Obtener el resultado
            ResultSet objResult = objPrepared.getGeneratedKeys();

            while (objResult.next()){
                objBook.setId(objResult.getInt(1));
            }
            //8. Cerramos el prepareStatement
            objPrepared.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding Book " + e.getMessage());
        }
        //9. Cerramos la conexión
        ConfigDB.closeConneciton();


        return objBook;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el objeto, se convierte porque object puede ser cualquier cosa y se convierte a BOOK para
        //poder acceder a los métodos de BOOK

        Book objBook = (Book) obj;

        //3. Variable bandera para saber si se actualizó y esto es lo que nos va a retornar el método
        boolean isUpdated = false;

        try {
            //4. Creamos la sentencia SQL
            // Esta es la manera de actualizar un tabla en base de datos
            // ?- representa que va a tomar el valor de una variable
            String sql = "UPDATE book SET title = ? , publication_year = ? , price = ? , id_author = ? WHERE id  = ? ;";

            //5. Preparamos el statement, el statement sale de la connexion
            /**
             * El metodo prepareStatement recibe dos parametro
             * @sql : Es la sentencia sql
             * @PreparedStatement : Sirve para que me muestre las llaves o ids, por si de pronto se le generó una nueva
             */
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //6. Le damos valor a los signos de interrogacion o " Parametros de Query "

            objPrepared.setString(1, objBook.getTitle());
            objPrepared.setString(2, objBook.getYearPublication());
            objPrepared.setDouble(3, objBook.getPrice());
            objPrepared.setInt(4, objBook.getIdAuthor());
            objPrepared.setInt(5, objBook.getId());

            //7. Ejecutamos el query
            //Y execute nos devuelve si se afecto y cuantas filas se actualizaron
            int rowAffected = objPrepared.executeUpdate();
            if (rowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Se actualizo");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //8. Cerramos la conexion
        ConfigDB.closeConneciton();
        //Para saber si se actualizó o no
        return isUpdated;
    }


    @Override
    public boolean delete(Object obj) {
        //1. Convertir el objeto a la entidad
        Book objBook = (Book) obj;
        //2. Variable booleana para medir el estado de la eliminación
        boolean isDeleted = false;

        //3. Abrir la conexión

        Connection objConnection = ConfigDB.openConnection();
        //TRY CATCH
        try {
            //4. Escribir la sentencia SQL
            String sql = "DELETE FROM book WHERE id = ?;";
            //5. Preparamos el statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            //6. Asignamos el valor al ?

            objPrepared.setInt(1, objBook.getId());
            //7. ExecuteUpdate devuelve la cantidad filas afectadas por la sentencia SQL ejecutada.
            int numFilas = objPrepared.executeUpdate();
            // Si el numero de columnas afectadas es mayor a 0 cambiamos el valor de la bandera a true
            if (numFilas > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Coder is DELETE");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //8. Cerramos la conexion
        ConfigDB.closeConneciton();
        return isDeleted;
    }

    @Override
    public List<Object> findAll() {

        //1. abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //Declarar la lista
        List<Object> listBooks = new ArrayList<>();

        try {
            //Esta es la sentencia sql para relacionar libros con autores
            String sql = "SELECT * FROM book \n" +
                    "INNER JOIN author ON author.id = book.id_author;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //Ejecutar el query

            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {
                Book objBook = new Book();
                Author objAuthor = new Author();
                //TENGO QUE ESPECIFICAR QUE ID PORQUE ESTOY HACIENDO UN JOIN
                objBook.setId(objResult.getInt("book.id"));
                objBook.setIdAuthor(objResult.getInt("book.id_author"));
                objBook.setPrice(objResult.getDouble("book.price"));
                objBook.setTitle(objResult.getString("book.title"));
                objBook.setYearPublication(objResult.getString("book.publication_year"));
                //LLENAMOS EL AUTOR

                objAuthor.setNationality(objResult.getString("author.nationality"));
                objAuthor.setName(objResult.getString("author.name"));
                objAuthor.setId(objResult.getInt("author.id"));

                //METEMOS EL AUTOR DENTRO DEL LIBRO
                objBook.setObjAuthor(objAuthor);

                listBooks.add(objBook);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listBooks;
    }

    @Override
    public Object findByID(int id) {
        //1. Abrir la conexión
        Connection objConnecton = ConfigDB.openConnection();
        //Instanciamos un book en null para luego darle el valor con la consulta
        Book objBook = null;
        //try-catch
        try {

            //2. Sentencia SQL
            String sql = "SELECT * FROM book WHERE id = ?;";
            //3. Preparar el statement
            PreparedStatement objPrepared = objConnecton.prepareStatement(sql);
            //4. Damos valor al: ?
            objPrepared.setInt(1, id);
            //5. Ejecutamos el query ExecuteQuery = todos los registros que trajo la base de datos
            ResultSet objResult = objPrepared.executeQuery();
            //6. Mientras haya un registro siguiente entonces WHILE
            while (objResult.next()) {
                //instanciamos un book
                objBook = new Book();

                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setYearPublication(objResult.getString("publication_year"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setIdAuthor(objResult.getInt("id_author"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //7. Cerrar la conexión
        ConfigDB.closeConneciton();
        return objBook;
    }
}
