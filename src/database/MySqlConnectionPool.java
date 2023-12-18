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

    // 사용할 connection을 담을 공간
    // TODO. array list, list 시간 복잡도 확인
    private List<Connection> connections;
    // 사용된 connection을 담을 공간
    private List<Connection> usedConnections;

    private int initCons = 0;
    private int maxCons = 0;

    private static MySqlConnectionPool cp;

    public static MySqlConnectionPool getInstance() throws SQLException {
        if (cp == null) {
            cp = new MySqlConnectionPool();
        }
        return cp;
    }

    private MySqlConnectionPool() {
        this.initCons = 5;
        this.maxCons = 10;

        // TODO 구현체 변경 (queue, stack, 기타 등등)
        // 사용할, 사용된 connection 배열을 동일하게 생성하기.
        connections = new ArrayList<Connection>(this.initCons);
        usedConnections = new ArrayList<Connection>(this.initCons);

        while (getConnectionsSize() < initCons) {
            addConnection();
        }
    }
    public void returnConnection(Connection conn) {
        this.usedConnections.remove(conn);
        this.connections.add(conn);
    }

    // 사용할 connection 담기
    public Connection getConnection() {
        // TODO 로직 고민.
        if (this.connections.isEmpty()) {
            while (getConnectionsSize() < maxCons) {
                addConnection();
            }
        }
        // 맨 마지막 connection 가지고 오기
        Connection conn = this.connections.get(this.connections.size() - 1);
        // 사용했으니 connections 에서 제거 해주기.
        this.connections.remove(conn);
        // 재사용하기 위해 usedConnections 에 담기.
        this.usedConnections.add(conn);
        return conn;
    }

    private int getConnectionsSize() {
        return connections.size() + usedConnections.size();
    }

    // connection pool에 connection 담기
    private void addConnection() {
        connections.add(getNewConnection());
    }

    // 새로운 connection 생성
    private Connection getNewConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(this.dbHost, this.dbUser, this.dbPassword);

        } catch (ClassNotFoundException | SQLException e) {
            this.logger.info(e.getMessage());
        }
        return conn;
    }

    //
    private void releaseConnection(Connection _con) {
        connections.remove(_con);
    }
}
