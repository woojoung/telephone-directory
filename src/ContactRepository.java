import database.MySqlConnection;
import dto.Contact;
import utils.CustomLogger;

import java.sql.*;
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
        Connection conn = MySqlConnection.getMySQLConnection();
        try {
            String sql = "INSERT INTO Contacts(phoneNumber, name, insertTime, updateTime) VALUES(?, ?, NOW(), NOW())";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phoneNumber);
            ps.setString(2, name);

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            this.logger.error(e.getMessage());
        }
    }

    public void delete(String name, String phoneNumber) {
        Connection conn = MySqlConnection.getMySQLConnection();
        try {
            String sql = "DELETE FROM Contacts WHERE phoneNumber = ? AND name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phoneNumber);
            ps.setString(2, name);

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            this.logger.error(e.getMessage());
        }
    }

    public void update(String name, String phoneNumber) {
        Connection conn = MySqlConnection.getMySQLConnection();
        try {
            String sql = "UPDATE Contacts SET phoneNumber = ?, name = ?, updateTime = NOW()";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phoneNumber);
            ps.setString(2, name);

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            this.logger.error(e.getMessage());
        }
    }

    public Contact findByPhoneNumber(String phoneNumber) {
        Connection conn = MySqlConnection.getMySQLConnection();
        try {
            String sql = "SELECT * FROM Contacts WHERE deleteTime = 0 AND phoneNumber = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phoneNumber);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Contact contact = new Contact(rs.getString("name"), rs.getString("phoneNumber"));

            rs.close();
            ps.close();
            conn.close();

            return contact;
        } catch (SQLException e) {
            this.logger.error(e.getMessage());
        }
        return null;
    }

    public List<Contact> findAll() {
        Connection conn = MySqlConnection.getMySQLConnection();
        try {
            String sql = "SELECT * FROM Contacts WHERE deleteTime = 0";
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Contact> contactList = new ArrayList<>();

            while (rs.next()){
                Contact contact = new Contact(rs.getString("name"), rs.getString("phoneNumber"));
                contactList.add(contact);
            }

            rs.close();
            st.close();
            conn.close();

            return contactList;
        } catch (SQLException e) {
            this.logger.error(e.getMessage());
        }
        return null;
    }

}
