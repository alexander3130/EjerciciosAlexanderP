package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    static Connection objConnection = null;

    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/_01_jdbc";
            String user = "root";
            String password = "Kloe040716*";
            objConnection = DriverManager.getConnection(url, user, password);
            System.out.println("Successful connection!");

        } catch (ClassNotFoundException e) {
            System.out.println("Error >> Driver not Installed");
        } catch (SQLException e) {
            System.out.println("Error >> A connection to the database could not be established");
        }
        return objConnection;
    }
    public static void closeConnection() {
        try {
            if (objConnection != null) objConnection.close();
            System.out.println("Connection Finished");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
