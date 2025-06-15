package com.example.miniprojet;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/blood_finder";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection connect() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}