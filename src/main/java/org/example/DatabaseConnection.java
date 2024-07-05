package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection(String dbUrl, String dbName, String user, String password) throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = dbUrl + dbName;
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
