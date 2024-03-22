package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    //Variable que va a contener el estado de la conexión

    static Connection objConnection = null;

    //Metodo para abrir la conexión entre Java y la base de datos
    public static Connection openConnection() {

        try {
//Class forname permite obtener o implementar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Creamos variables con nuestras credenciales de la base de datos
            String url = "jdbc:mysql://srv1243.hstgr.io/u942879228_riwi_book";
            String user = "u942879228_riwi_book";
            String password = "Apr123456*";

            //Establezco la conexión
            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Me conecté perfectamente");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no instalado");
        } catch (SQLException e){
            System.out.println("NO SE PUEDO ESTABLECER CONEXION CON LA DB");
        }
return  objConnection ;
    }
    public  static  void closeConneciton (){
        try{
            //Si hay una conexion activa la cerramos
            if (objConnection != null) objConnection.close();
        }catch (SQLException e){
            System.out.println("Fallo al cerrar la conexion " + e.getMessage());
        }
    }
}
