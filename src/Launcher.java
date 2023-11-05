import dto.Contact;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;

public class Launcher {
    /* TODO. 병렬 처리를 위해 개선을 한다고 가정
    * 전화번호부 operation 10000건 시도.
    * main key는 전화번호부 --> synchronized 블록을 통해 병렬 처리 실험.
    **/
    public static void main(String[] args) {
        try {
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
                // System.out.println("Method: " + method);
                // System.out.println("Name: " + name);
                // System.out.println("PhoneNumber: " + phoneNumber);
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
                // System.out.println("========================================");
            }
        } catch (IOException e) {
            throw new RuntimeException(e); // logger err/info/warn
        }
    }

}