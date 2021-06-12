package Agriculshare.Model.DBManagement;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection databaseLink;

    public static Connection getConnection() {
        String dbName = "AgriculshareDB";
        String dbUser = "root";
        String dbPassword = "Agriculshare";
        String url = "jdbc:mysql://localhost/" + dbName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return databaseLink;
    }


}
