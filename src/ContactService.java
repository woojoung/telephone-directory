import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/*
 * Service Layer
 * Business Logic리 처리
 * Service 는 통일해도 되고 나눠도 된다.
 * Controller 로 전달
 * */
public class ContactService {
    ContactRepository contactRepository = new ContactRepository();

    public void addContact(JSONArray list) {
        for (int i = 0; i < list.length(); i++) {
            JSONObject jsonObject = list.getJSONObject(i);
            String name = jsonObject.getString("name");
            String phoneNumber = jsonObject.getString("phoneNumber");
            Contact contact = new Contact(name, phoneNumber);
            this.contactRepository.insert(contact);
        }
    }

    public void updateContact(JSONArray list) {
        for (int i = 0; i < list.length(); i++) {
            JSONObject jsonObject = list.getJSONObject(i);
            String name = jsonObject.getString("name");
            String phoneNumber = jsonObject.getString("phoneNumber");
            Contact contact = new Contact(name, phoneNumber);
            this.contactRepository.update(contact);
        }
    }

    public void deleteContact(JSONArray list) {
        for (int i = 0; i < list.length(); i++) {
            JSONObject jsonObject = list.getJSONObject(i);
            String name = jsonObject.getString("name");
            String phoneNumber = jsonObject.getString("phoneNumber");
            Contact contact = new Contact(name, phoneNumber);
            this.contactRepository.delete(contact);
        }
    }

    public void findAllContact() {
        List list = this.contactRepository.findAll();
        // TODO. 수정하기
        for (Object item : list) {
            System.out.println(item);
        }
    }

}
