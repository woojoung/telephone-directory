import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactRepository {
    // Persistence Layer
    // DB나 파일 같은 외부 I/O 작업을 처리
    // @Service 로 전달
    private final String FILE_NAME = "phonebook.txt";
    private final Scanner scan = new Scanner(System.in); // TODO. Scanner 도 동시성문제가 있진 않을까요?

    public void insert(Contact contact) {

    }

    public void delete(Contact contact) {

    }

    public void update(Contact contact) {

    }

    public Contact findByName(String name) {
        return null;
    }

    public Contact findByPhoneNumber(String phoneNumber) {
        return null;
    }

    public List<Contact> findAll() {
        return new ArrayList<>();
    }
}
