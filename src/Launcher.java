import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;

public class Launcher {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("request.json");
            JSONTokener tokener = new JSONTokener(fileReader);
            JSONArray jsonArray = new JSONArray(tokener);

            ContactController controller = new ContactController();
            controller.processData(jsonArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}