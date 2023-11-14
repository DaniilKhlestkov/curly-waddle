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
    private static SessionFactory sessionFactory;
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pfxtvgfhjkm";

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = createProperties();
                Configuration configuration = new Configuration();

                configuration.addAnnotatedClass(User.class).
                        setProperties(properties);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    private static Properties createProperties() {
        Properties setting = new Properties();
        setting.put(Environment.DRIVER, DRIVER);
        setting.put(Environment.URL, URL + "?useSSL=false");
        setting.put(Environment.USER, USERNAME);
        setting.put(Environment.PASS, PASSWORD);
        setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        setting.put(Environment.SHOW_SQL, "true");
        setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        setting.put(Environment.HBM2DDL_AUTO, "create");
        return setting;
    }


    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Нет контакта");
        }
        return connection;
    }


}
