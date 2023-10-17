import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private List<Contact> contacts = new ArrayList<>();
    String fileName;

    public ContactManager(String fileName){
        this.fileName = fileName;
        File file = new File(fileName);
        if(!file.exists()) {
            saveContacts();
        } else {
            loadContacts();
        }

    }

    public void addContact(Contact contact) {
        Contact addOne = null;
        for (Contact c : getAllContacts()) {
            if (c.getPhone().equals(contact.getPhone())) {
                addOne = c;
                break;
            }
        }
        if (addOne != null) {
            System.out.println(contact.getPhone() + " : Already Exists.");
        } else {
            contacts.add(contact);
            saveContacts();
        }

    }

    public void removeContact(String phone) {
        Contact remove = null;
        for (Contact c : getAllContacts()) {
            if (c.getPhone().equals(phone)) {
                remove = c;
                break;
            }
        }
        if (remove != null) {
            contacts.remove(remove);
            saveContacts();
        } else {
            System.out.println(phone + " : Not Found.");
        }
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public Contact getOneContact(String phone) {
        Contact one = null;
        for (Contact c : getAllContacts()) {
            if (c.getPhone().equals(phone)) {
                one = c;
                break;
            }
        }
        if (one == null) {
            System.out.println(phone + " : Not Found.");
        }
        return one;
    }

    public void loadContacts(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(fileName)));
            contacts = (List<Contact>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveContacts(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
