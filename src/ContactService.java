import org.json.JSONArray;
import org.json.JSONObject;

public class ContactService {
    // Service Layer
    // Business Logic리 처리
    // Repository 에서 얻은 정보로 자바 문법으로 가공하여
    // 다시 @Controller에게 정보를 보낸다.
    // service 는 통일해도 되고 나눠도 된다.
    // 예를들어 add contact service
    // update contact service
    ContactRepository contactRepository = new ContactRepository();

    public void addContact(JSONArray list) {
        for (int i = 0; i < list.length(); i++) {
            JSONObject jsonObject = list.getJSONObject(i);
            String name = jsonObject.getString("name");
            String phone = jsonObject.getString("phone");
            Contact contact = new Contact(name, phone);
            this.contactRepository.insert(contact);
        }
    }

    public void updateContact(JSONArray list) {
        for (int i = 0; i < list.length(); i++) {
            JSONObject jsonObject = list.getJSONObject(i);
            String name = jsonObject.getString("name");
            String phone = jsonObject.getString("phone");
            Contact contact = new Contact(name, phone);
            this.contactRepository.update(contact);
        }
    }

    public void deleteContact(JSONArray list) {
        for (int i = 0; i < list.length(); i++) {
            JSONObject jsonObject = list.getJSONObject(i);
            String name = jsonObject.getString("name");
            String phone = jsonObject.getString("phone");
            Contact contact = new Contact(name, phone);
            this.contactRepository.delete(contact);
        }
    }

}
