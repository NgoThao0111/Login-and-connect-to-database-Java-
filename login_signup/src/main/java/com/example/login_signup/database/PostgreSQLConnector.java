package com.example.login_signup.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnector {
    private static PostgreSQLConnector instance; //bien tinh (mo hinh Singleton -> chi co 1 ket noi tai
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");
    private Connection connection;

    private PostgreSQLConnector() {
        initializePostgresqlDatabase();
    }

    // Making sure there is only one instance at one time (Singleton Model)
    public static PostgreSQLConnector getInstance() {
        if (instance == null) {
            synchronized (PostgreSQLConnector.class) { // If multiple thread calling getInstance at one time
                if (instance == null) {
                    instance = new PostgreSQLConnector();
                }
            }
        }
        return instance;
    }

    private void initializePostgresqlDatabase() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to PostgreSQL database successfully.");
        } catch (SQLException ex) {
            System.err.println("Database connection failed: " + ex.getMessage());
            throw new RuntimeException("Failed to connect to database", ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException ex) {
                System.err.println("Error closing connection: " + ex.getMessage());
            }
        }
    }
}
