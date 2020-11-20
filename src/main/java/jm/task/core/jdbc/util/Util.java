package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/task112base?useSSL=false";

    private static final String LOGIN = "root";
    private static final String PASSWORD = "Developer$0803";

    public Connection getConnection() {
        Connection connection = null;
        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DATABASE_URL, LOGIN, PASSWORD);
            System.out.println("Connection established.");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }


}
