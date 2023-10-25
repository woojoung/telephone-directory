import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContactManager {

    private Map<String, String> contacts = new HashMap<>();
    private String phoneBookFileName = "phonebook.txt";
    Scanner scan = new Scanner(System.in);

    public ContactManager(){
        loadContacts();
    }

    public void addContact() {
        System.out.print(" Enter The File Name >>> ");
        String fileName = scan.next();
        File file = new File(fileName);
        if(!file.exists()) {
            System.out.println(fileName + " : Not Found.");
            return;
        }
        Map<String, String> newContacts = readFile(file);
        insertContacts(newContacts);
    }

    public void removeContact() {
        System.out.print(" Enter The Phone Number >>> ");
        String phone = scan.next();
        if (!this.contacts.containsKey(phone)) {
            System.out.println(phone + " : Not Found.");
        } else {
            this.contacts.remove(phone);
            System.out.println(" Deleted Contract : " + phone);
        }
        updateContacts(this.contacts);
    }

    public void getAllContacts() {
        System.out.println("=====================================");
        System.out.println(" Name\tPhone ");
        System.out.println("=====================================");
        for (Map.Entry<String, String> entry : this.contacts.entrySet()) {
            System.out.println(" " + entry.getValue() + "\t" + entry.getKey());
        }
    }

    public void getOneContact() {
        System.out.print(" Enter The Phone Number >>> ");
        String phone = scan.next();
        if (!this.contacts.containsKey(phone)) {
            System.out.println(phone + " : Not Found.");
        } else {
            System.out.println("=====================================");
            System.out.println(" Name\tPhone ");
            System.out.println("=====================================");
            System.out.println(" " + this.contacts.get(phone) + "\t" + phone);
        }
    }

    public void loadContacts() {
        File file = new File(phoneBookFileName);
        if(!file.exists()) {
            return;
        }
        this.contacts.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String phoneNumber = parts[0].trim();
                String name = parts[1].trim();
                this.contacts.put(phoneNumber, name);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertContacts(Map<String, String> contacts){
        try {
            FileWriter writer = new FileWriter(phoneBookFileName, true);
            if (contacts != null && !contacts.isEmpty()) {
                for (Map.Entry<String, String> entry : contacts.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    writer.write(key + "," + value + "\n");
                    System.out.println(" Added Contract :" + "\t" + entry.getKey());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            loadContacts();
        }
    }

    public void updateContacts(Map<String, String> contacts){
        try {
            FileWriter writer = new FileWriter(phoneBookFileName, false);
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                writer.write(key + "," + value + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            loadContacts();
        }
    }

    public Map<String, String> readFile(File file) {
        Map<String, String> newContacts = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            newContacts = new HashMap<>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String phoneNumber = parts[0].trim();
                String name = parts[1].trim();
                if (!this.contacts.containsKey(phoneNumber)) {
                    newContacts.put(phoneNumber, name);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newContacts;
    }

    public void exit() {
        System.out.println(" Exit Program... ");
        System.exit(0);
    }
}