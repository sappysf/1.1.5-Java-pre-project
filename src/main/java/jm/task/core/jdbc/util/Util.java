package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class Util {
    private Util() {
    }

    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        Logger logger = Logger.getLogger(Util.class.getName());
        Connection connection;
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fileInputStream);
            final String URL = properties.getProperty("URL");
            final String USERNAME = properties.getProperty("USERNAME");
            final String PASSWORD = properties.getProperty("PASSWORD");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                logger.info("connection is correct");
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
