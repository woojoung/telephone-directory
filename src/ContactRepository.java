import database.MySqlConnector;
import dto.Contact;
import utils.CustomLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Persistence Layer
 * DB나 File 같은 외부 I/O 작업을 처리
 * Service 로 전달
 * */
public class ContactRepository {
    CustomLogger logger;
    // Repository 클래스 생성자
    public ContactRepository() {
        this.logger = CustomLogger.getInstance();
    }

    public void insert(String name, String phoneNumber) {
        Connection conn = MySqlConnector.getMySQLConnection();
        try {
            String sql = "INSERT INTO Contacts(phoneNumber, name) VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phoneNumber);
            ps.setString(2, name);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            this.logger.error(e.getMessage());
        } finally {
            MySqlConnector.close(conn);
        }
    }

    public void delete(String name, String phoneNumber) {
        Connection conn = MySqlConnector.getMySQLConnection();
        try {
            String sql = "DELETE FROM Contacts WHERE phoneNumber = ? AND name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phoneNumber);
            ps.setString(2, name);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            this.logger.error(e.getMessage());
        } finally {
            MySqlConnector.close(conn);
        }
    }

    public void update(String name, String phoneNumber) {
        Connection conn = MySqlConnector.getMySQLConnection();
        try {
            String sql = "UPDATE Contacts SET phoneNumber = ?, name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phoneNumber);
            ps.setString(2, name);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            this.logger.error(e.getMessage());
        } finally {
            MySqlConnector.close(conn);
        }
    }

    public Contact findByPhoneNumber(String phoneNumber) {
        Connection conn = MySqlConnector.getMySQLConnection();
        try {
            String sql = "SELECT * FROM Contacts WHERE phoneNumber = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phoneNumber);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Contact contact = new Contact(rs.getString("name"), rs.getString("phoneNumber"));

            rs.close();
            ps.close();

            return contact;
        } catch (SQLException e) {
            this.logger.error(e.getMessage());
        } finally {
            MySqlConnector.close(conn);
        }
        return null;
    }

    public List<Contact> findAll() {
        Connection conn = MySqlConnector.getMySQLConnection();
        try {
            String sql = "SELECT * FROM Contacts";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<Contact> contactList = new ArrayList<>();

            while (rs.next()){
                Contact contact = new Contact(rs.getString("name"), rs.getString("phoneNumber"));
                contactList.add(contact);
            }

            rs.close();
            ps.close();

            return contactList;
        } catch (SQLException e) {
            this.logger.error(e.getMessage());
        } finally {
            MySqlConnector.close(conn);
        }
        return null;
    }

}
