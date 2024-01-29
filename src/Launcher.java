import database.MySqlConnection;
import dto.Contact;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import utils.CustomLogger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Launcher {
    /* TODO. 병렬 처리를 위해 개선을 한다고 가정
    * 전화번호부 operation 10000건 시도.
    * main key는 전화번호부 --> synchronized 블록을 통해 병렬 처리 실험.
    **/
    public static void main(String[] args) {
        CustomLogger logger = CustomLogger.getInstance();
        try {
            // MySQL 연결
            // TODO. sql 파일로 처리.
            Connection conn = MySqlConnection.getMySQLConnection();
            Statement st = conn.createStatement();
            String createDatabase = "CREATE DATABASE IF NOT EXISTS `database1` DEFAULT CHARACTER SET utf8mb4";
            st.executeUpdate(createDatabase);
            String createTable = "CREATE TABLE IF NOT EXISTS `Contacts` (" +
                    "`contactId` BIGINT(20) NOT NULL AUTO_INCREMENT, " +
                    "`deleteTime` BIGINT(20) NOT NULL DEFAULT 0, " +
                    "`phoneNumber` VARCHAR(20) NOT NULL DEFAULT '', " +
                    "`name` VARCHAR(45) NOT NULL DEFAULT '', " +
                    "`insertTime` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00', " +
                    "`updateTime` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00', " +
                    "PRIMARY KEY (`contactId`)" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
            st.executeUpdate(createTable);
            st.close();
            conn.close();

            // json 파일 읽기
            FileReader fileReader = new FileReader("request.json");
            JSONTokener tokener = new JSONTokener(fileReader);
            JSONArray jsonArray = new JSONArray(tokener);

            // json 파일 내용 controller 전달
            ContactController controller = new ContactController();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String method = jsonObject.getString("method");
                String name = jsonObject.getString("name");
                String phoneNumber = jsonObject.getString("phoneNumber");

                // Object to DTO(dto.Contact)
                Contact contact = new Contact(name, phoneNumber);

                // call controller
                switch (method) {
                    case "SELECT":
                        controller.findContact();
                        break;
                    case "INSERT":
                        controller.insertContact(contact);
                        break;
                    case "UPDATE":
                        controller.updateContact(contact);
                        break;
                    case "DELETE":
                        controller.deleteContact(contact);
                        break;
                    default:
                        System.out.println("Invalid method: " + method);
                }
            }
        } catch (IOException | SQLException e) {
            logger.info(e.getMessage());
        }
    }

}