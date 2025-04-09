package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnUtil {   // <-- Add 'public' here
    private static final String URL = "jdbc:mysql://localhost:3306/jobboard";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Jhansi@2708";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

