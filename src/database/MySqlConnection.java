package database;

import utils.CustomLogger;
import java.sql.*;
import java.util.Map;

public class MySqlConnection {
    public static Connection getMySQLConnection() {
        Connection conn = null;
        CustomLogger logger = CustomLogger.getInstance();
        try {
            Map<String, String> env = System.getenv();
            Class.forName("com.mysql.jdbc.Driver");

            String dbHost = env.get("DB_HOST");
            String dbUser = env.get("DB_USER");
            String dbPassword = env.get("DB_PASSWORD");

            conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);

        } catch (ClassNotFoundException | SQLException e) {
            logger.info(e.getMessage());
        }
        return conn;
    }
}


