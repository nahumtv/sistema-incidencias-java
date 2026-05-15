package config;

import java.sql.*;

public class ConnectionDB {
    private static final String DB_HOST = "localhost";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "admin";
    private static final String DB_NAME = "sgidb";
    private static final String DB_PORT = "5432";
    
    private static final String URL =
            "jdbc:postgresql://" +
            DB_HOST + ":" +
            DB_PORT + "/" +
            DB_NAME;

    public static Connection establecerConexion()throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }
} 
