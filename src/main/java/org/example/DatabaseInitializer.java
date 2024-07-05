package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private String dbUrl;
    private String dbName;
    private String user;
    private String password;

    public DatabaseInitializer(String dbUrl, String dbName, String user, String password) {
        this.dbUrl = dbUrl;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
    }

    public void initializeDatabase() throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbUrl, user, password)) {
            try (Statement stmt = connection.createStatement()) {
                String createDatabaseQuery = "CREATE DATABASE " + dbName;
                stmt.executeUpdate(createDatabaseQuery);
            } catch (SQLException e) {
                if (!e.getSQLState().equals("42P04")) {
                    throw e;
                }
            }
        }
    }
}
