package com.revature.repository;

import java.sql.Connection;
import org.postgresql.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionsFactory {

    public static Connection createConnection() throws SQLException {
        //1. Register the postgres driver with the DriverManager class
        Driver postgresDriver = new Driver();
        DriverManager.registerDriver(postgresDriver);

        String url = "jdbc:postgresql://127.0.0.1:5432/Project1";

        String username = System.getenv("database_username");
        String password = System.getenv("database_password");

        Connection connectionObject = DriverManager.getConnection(url, username, password);

        return connectionObject;
    }
}
