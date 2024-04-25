package com.cafe.hopecafe;

import java.sql.Connection;
import java.lang.Class;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection  {
    public Connection dataseBaseLink;


    public Connection getConnection() {
        String databaseName = "hopecafe";
        String databaseUser = "root";
        String databasePassword = "12345";
        String url = "jdbc:mysql://127.0.0.1:3306/" + databaseName;

        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Establish the database connection
        try {
            dataseBaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dataseBaseLink;
    }




}
