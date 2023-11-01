import java.io.*;
import java.util.*;
/*
 * Persistence Layer
 * DB나 File 같은 외부 I/O 작업을 처리
 * Service 로 전달
 * */
public class ContactRepository {
    private final String FILE_NAME = "phonebook.txt";
    HashMap<String, String> contactMap = new HashMap<>();
    //  private final Scanner scan = new Scanner(System.in);
    // TODO. Scanner 도 동시성문제가 있진 않을까?

    public void insert(Contact contact) {
        this.contactMap = readFile();
        String name = contact.getName();
        String phoneNumber = contact.getPhoneNumber();
        // find
        if (!this.contactMap.containsKey(phoneNumber)) {
            // insert
            this.contactMap.put(phoneNumber, name);
            // save
            saveFile(this.contactMap);
        } else {
            System.out.println("Already Exists : " + phoneNumber);
        }


    }

    public void delete(Contact contact) {
        this.contactMap = readFile();
        String phoneNumber = contact.getPhoneNumber();
        // find
        if (this.contactMap.containsKey(phoneNumber)) {
            // remove
            this.contactMap.remove(phoneNumber);
            // save
            saveFile(this.contactMap);
        } else {
            System.out.println("Not Found : " + phoneNumber);
        }
    }

    public void update(Contact contact) {
        this.contactMap = readFile();
        String phoneNumber = contact.getPhoneNumber();
        String name = contact.getName();
        // find
        if (this.contactMap.containsKey(phoneNumber)) {
            // remove
            this.contactMap.replace(phoneNumber, name);
            // save
            saveFile(this.contactMap);
        } else {
            System.out.println("Not Found : " + phoneNumber);
        }
    }

    public Contact findByName(String name) {
        this.contactMap = readFile();
        for (Map.Entry<String, String> entry : this.contactMap.entrySet()) {
            if (Objects.equals(entry.getValue(), name)) {
                String phoneNumber = entry.getKey();
                Contact contact = new Contact(name, phoneNumber);
                return contact;
            }
        }
        return null;
    }

    public Contact findByPhoneNumber(String phoneNumber) {
        this.contactMap = readFile();
        for (Map.Entry<String, String> entry : this.contactMap.entrySet()) {
            if (Objects.equals(entry.getKey(), phoneNumber)) {
                String name = entry.getValue();
                Contact contact = new Contact(name, phoneNumber);
                return contact;
            }
        }
        return null;
    }

    public List<Contact> findAll() {
        this.contactMap = readFile();
        ArrayList list = new ArrayList<>();
        for (Map.Entry<String, String> entry : this.contactMap.entrySet()) {
            String name = entry.getValue();
            String phoneNumber = entry.getKey();
            Contact contact = new Contact(name, phoneNumber);
            list.add(contact);
            return list;
        }
        return null;
    }

    public HashMap<String, String> readFile() {
        File file = new File(FILE_NAME);
        try {
            if(!file.exists()) {
                FileWriter writer = new FileWriter(file);
                writer.close();
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            HashMap<String, String> newContacts = new HashMap<>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String phoneNumber = parts[0].trim();
                String name = parts[1].trim();
                newContacts.put(phoneNumber, name);
            }
            br.close();
            return newContacts;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
            throw new RuntimeException(e);
        }
    }
}
