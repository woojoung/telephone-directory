import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManager {
    private List<Contact> contacts = new ArrayList<>();
    String fileName, phone, name;
    Scanner scan = new Scanner(System.in);

    public ContactManager(String fileName){
        this.fileName = fileName;
        File file = new File(fileName);
        if(!file.exists()) {
            saveContacts();
        } else {
            loadContacts();
        }

    }

    public void addContact() {
        System.out.print(" Enter The Phone Number >>> ");
        this.phone = scan.next();
        System.out.print(" Enter The Name >>> ");
        this.name = scan.next();
        Contact contact = new Contact(name, phone);
        boolean isExist = false;
        for (Contact c : this.contacts) {
            if (c.getPhone().equals(contact.getPhone())) {
                isExist = true;
                break;
            }
        }
        if (isExist) {
            System.out.println(contact.getPhone() + " : Already Exists.");
        } else {
            this.contacts.add(contact);
            saveContacts();
            System.out.println(" Added Contract!!");
        }

    }

    public void removeContact() {
        System.out.print(" Enter The Phone Number >>> ");
        this.phone = scan.next();
        Contact remove = null;
        for (Contact c : this.contacts) {
            if (c.getPhone().equals(this.phone)) {
                remove = c;
                break;
            }
        }
        if (remove != null) {
            this.contacts.remove(remove);
            saveContacts();
            System.out.println(" Deleted Contract!!");
        } else {
            System.out.println(this.phone + " : Not Found.");
        }
    }

    public void getAllContacts() {
        System.out.println("=====================================");
        System.out.println(" Name\tPhone ");
        System.out.println("=====================================");
        for (Contact c : this.contacts) {
            System.out.println(" " + c.getName() + "\t" + c.getPhone());
        }
    }

    public void getOneContact() {
        System.out.print(" Enter The Phone Number >>> ");
        this.phone = scan.next();
        Contact findOne = null;
        for (Contact c : this.contacts) {
            if (c.getPhone().equals(phone)) {
                findOne = c;
                break;
            }
        }
        if (findOne == null) {
            System.out.println(this.phone + " : Not Found.");
        } else {
            System.out.println("=====================================");
            System.out.println(" Name\tPhone ");
            System.out.println("=====================================");
            System.out.println(" " + findOne.getName() + "\t" + findOne.getPhone());
        }
    }

    public void loadContacts(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(fileName)));
            this.contacts = (List<Contact>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveContacts(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(this.contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        System.out.println(" Exit Program... ");
        System.exit(0);
    }
}
