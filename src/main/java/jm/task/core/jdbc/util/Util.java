package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

public class Util {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/task112base?useSSL=false";

    private static final String LOGIN = "root";
    private static final String PASSWORD = "Developer$0803";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties dbSettings = new Properties();
                dbSettings.put(Environment.URL, DATABASE_URL);
                dbSettings.put(Environment.USER, LOGIN);
                dbSettings.put(Environment.PASS, PASSWORD);
                dbSettings.put(Environment.DRIVER, JDBC_DRIVER);
                dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                dbSettings.put(Environment.SHOW_SQL, "true");
                dbSettings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                dbSettings.put(Environment.HBM2DDL_AUTO, "create-drop");
                dbSettings.put(Environment.AUTOCOMMIT, "true");

                configuration.setProperties(dbSettings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Session factory creating error");
            }
        }
        return sessionFactory;
    }

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
