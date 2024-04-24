package com.cafe.hopecafe;

import java.sql.Connection;
import java.lang.Class;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection  {
    public Connection dataseBaseLink;


    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String databaseName = "HopeCafeDB";
        String databaseUser = "your_username";
        String databasePassword = "your_password";
        String url = "jdbc:mysql://127.0.0.1:3306/" + databaseName;

        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish the database connection
        dataseBaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

        return dataseBaseLink;
    }


}
