import org.json.JSONArray;
import org.json.JSONObject;

public class ContactController {
    // Presentation Layer
    // Request, Response 처리
    ContactService contactService = new ContactService();
    public void processData(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String method = jsonObject.getString("method");
            JSONObject body = jsonObject.getJSONObject("body");

            System.out.println("Method: " + method);
            System.out.println("Body: " + body);

            switch (method) {
                case "INSERT":
                    addContact(body);
                    break;
                case "UPDATE":
                    updateContact(body);
                    break;
                case "DELETE":
                    deleteContact(body);
                    break;
                default:
                    System.out.println("Invalid method: " + method);
            }
            System.out.println("========================================");
        }
    }
    public void addContact(JSONObject body) {
        JSONArray list = body.getJSONArray("list");
        this.contactService.addContact(list);
    }

    public void updateContact(JSONObject body) {
        JSONArray list = body.getJSONArray("list");
        this.contactService.updateContact(list);
    }

    public void deleteContact(JSONObject body) {
        JSONArray list = body.getJSONArray("list");
        this.contactService.deleteContact(list);
    }
}
