package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
public static Connection databaseConnector()  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","system");
            System.out.println("Connection Established");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }   catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}