import org.json.JSONArray;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.IOException;

public class Launcher {
    public static void main(String[] args) {
        try {
            // json 파일 읽기
            FileReader fileReader = new FileReader("request.json");
            JSONTokener tokener = new JSONTokener(fileReader);
            JSONArray jsonArray = new JSONArray(tokener);

            // json 파일 내용 controller 전달
            ContactController controller = new ContactController();
            controller.processData(jsonArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}