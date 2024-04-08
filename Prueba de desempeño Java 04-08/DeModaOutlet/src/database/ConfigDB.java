package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    public static Connection objConnection = null;
    public static Connection openConnection(){
        try {
            String url = "jdbc:mysql://127.0.0.1/demoda_outlet";
            String user = "root";
            String password = "";

            objConnection = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado perfectamente");
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }

        return objConnection;
    }
    public static void closeConnection(){
        try {
            objConnection.close();
            System.out.println("Conexión cerrada correctamente");
        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
    }


}
