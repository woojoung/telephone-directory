import dto.Contact;
import utils.CustomLogger;

import java.io.*;
import java.util.*;
/*
 * Persistence Layer
 * DB나 File 같은 외부 I/O 작업을 처리
 * Service 로 전달
 * */
public class ContactRepository {
    private final String FILE_NAME = "phonebook.txt";
    Map<String, String> contactMap = new HashMap<>();
    CustomLogger logger;
    // Repository 클래스 생성자
    public ContactRepository() {
        // load file
        this.contactMap = readFile();
        this.logger = CustomLogger.getInstance();
    }

    public void insert(String name, String phoneNumber) {
        // put
        this.contactMap.put(phoneNumber, name);
        // save
        saveFile(this.contactMap);
    }

    public void delete(String name, String phoneNumber) {
        // remove
        this.contactMap.remove(phoneNumber, name);
        // save
        saveFile(this.contactMap);
    }

    public void update(String name, String phoneNumber) {
        // replace
        this.contactMap.replace(phoneNumber, name);
        // save
        saveFile(this.contactMap);
    }

    public Contact findByPhoneNumber(String phoneNumber) {
        if (this.contactMap.containsKey(phoneNumber)){
            String name = this.contactMap.get(phoneNumber);
            Contact contact = new Contact(name, phoneNumber);
            return contact;
        }
        return null;
    }

    public Map<String, String> readFile() {
        File file = new File(FILE_NAME);
        try {
            if(!file.exists()) {
                FileWriter writer = new FileWriter(file);
                writer.close();
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Map<String, String> newContacts = new HashMap<>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String phoneNumber = parts[0].trim();
                String name = parts[1].trim();
                newContacts.put(phoneNumber, name);
            }
            br.close();
            return newContacts;
        } catch (IOException e) {
            this.logger.error(e.getMessage());
        }
        return null;
    }

    public void saveFile(Map<String, String> contacts){
        try {
            FileWriter writer = new FileWriter(FILE_NAME, false);
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                writer.write(key + "," + value + "\n");
            }
            writer.close();
        } catch (IOException e) {
            this.logger.error(e.getMessage());
        }
    }
}
