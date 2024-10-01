package com.juliobro.crudready.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static final String USERNAME = System.getenv("MYSQL_USER");
    public static final String PASSWORD = System.getenv("MYSQL_PASS");
    public static final String DATABASE = "crud_ready";
    public static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;

    public static Connection getConnection() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cn;
    }
}
