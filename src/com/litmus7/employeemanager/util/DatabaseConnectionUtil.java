package com.litmus7.employeemanager.util;

import java.sql.Connection;
import java.sql.DriverManager;

import com.litmus7.employeemanager.constants.Constants;

public class DatabaseConnectionUtil {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
