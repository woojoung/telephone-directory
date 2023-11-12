package database;

import utils.CustomLogger;
import java.sql.*;
import java.util.Map;

public class MySqlConnector{
    CustomLogger logger = CustomLogger.getInstance();

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

    public static void close(Connection conn) {
        CustomLogger logger = CustomLogger.getInstance();
        if(conn!= null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }
    }
    public static void close(Statement stmt) {
        CustomLogger logger = CustomLogger.getInstance();
        if(stmt!= null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }
    }
    public static void close(PreparedStatement pstmt) {
        CustomLogger logger = CustomLogger.getInstance();
        if(pstmt!= null) {
            try { pstmt.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }
    }
    public static void close(ResultSet rs) {
        CustomLogger logger = CustomLogger.getInstance();
        if(rs!= null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }
    }
}


