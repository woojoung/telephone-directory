package database;

import utils.CustomLogger;

import java.util.Map;

public class MySqlConnectionPool {
    CustomLogger logger = CustomLogger.getInstance();
    Map<String, String> env = System.getenv();
    String dbHost = env.get("DB_HOST");
    String dbUser = env.get("DB_USER");
    String dbPassword = env.get("DB_PASSWORD");
}
