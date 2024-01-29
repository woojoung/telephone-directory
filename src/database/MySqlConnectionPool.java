package database;

import utils.CustomLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySqlConnectionPool {
    CustomLogger logger = CustomLogger.getInstance();
    Map<String, String> env = System.getenv();
    private final String dbHost = env.get("DB_HOST");
    private final String dbUser = env.get("DB_USER");
    private final String dbPassword = env.get("DB_PASSWORD");

    // 사용할 connection 을 담을 공간
    private List<Connection> connectionPool;
    // 최대 생성 connection 수
    private int MAX_POOL_SIZE = 10;

    // TODO. Singleton pattern 으로 구현해보기
    private static MySqlConnectionPool cp;

    public static MySqlConnectionPool getInstance() throws SQLException {
        if (cp == null) {
            cp = new MySqlConnectionPool();
        }
        return cp;
    }

    // TODO. 구현체 변경 해보기 (queue, stack, 기타 등등)
    private MySqlConnectionPool() {
        // 사용할 connection pool 생성
        connectionPool = new ArrayList<Connection>(MAX_POOL_SIZE);
        while (connectionPool.size() >= MAX_POOL_SIZE) {
            addToConnectionPool();
        }
    }

    // connection 사용 후 connection pool 에 반납
    public void returnToConnectionPool(Connection conn) {
        if (conn != null) {
            this.connectionPool.add(conn);
        }
        this.connectionPool.remove(conn);
    }

    // 사용할 connection 가져오기
    public Connection getConnectionFromPool() {
        // TODO 로직 고민. synchronized??
        // 있으면 conn 리턴,
        // 없으면??  생성 후 리턴?
        Connection conn = null;
        if (!connectionPool.isEmpty()) {
            conn = connectionPool.remove(connectionPool.size() - 1);
        }
        return conn;
    }

    // connection pool에 connection 담기
    private void addToConnectionPool() {
        connectionPool.add(createNewConnection());
    }

    // 새로운 connection 생성
    private Connection createNewConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(this.dbHost, this.dbUser, this.dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            this.logger.info(e.getMessage());
        }
        return conn;
    }

    private void closeConnections() throws SQLException {
        for (Connection connection : connectionPool) {
            connection.close();
        }
        connectionPool.clear();
    }

}
