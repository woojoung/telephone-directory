import java.util.List;
import java.util.Scanner;

public class TelephoneDirectory {
    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager("telephone_directory.txt");
        Scanner scan = new Scanner(System.in);

        System.out.println(" Telephone Directory Management!");
        while(true){
            System.out.println(" [ADD: 1, DELETE: 2, SEARCH: 3, DISPLAY_LIST: 4, EXIT: 0]");
            System.out.print(" Enter The Menu >>> ");
            // 1. 번호 추가
            // 2. 번호 삭제
            // 3. 번호 조회
            // 4. 목록 조회
            // 0. 종료
            int menu = scan.nextInt(); // 지역 변수
            scan.nextLine();

            switch (menu){
                case 1:
                    System.out.print(" Enter The Phone Number >>> ");
                    String phone = scan.next();
                    System.out.print(" Enter The Name >>> ");
                    String name = scan.next();
                    Contact contact = new Contact(name, phone);
                    contactManager.addContact(contact);
                    System.out.println(" Added Contract!!");
                    break;
                case 2:
                    System.out.print(" Enter The Phone Number >>> ");
                    String phoneNumber = scan.next();
                    contactManager.removeContact(phoneNumber);
                    System.out.println(" Deleted Contract!!");
                    break;
                case 3:
                    System.out.print(" Enter The Phone Number >>> ");
                    String phoneNum = scan.next();
                    Contact findOne = contactManager.getOneContact(phoneNum);
                    if (findOne == null) {
                        break;
                    }
                    System.out.println("=====================================");
                    System.out.println(" Name\tPhone ");
                    System.out.println("=====================================");
                    System.out.println(" " + findOne.getName() + "\t" + findOne.getPhone());
                    break;
                case 4:
                    System.out.println("=====================================");
                    System.out.println(" Name\tPhone ");
                    System.out.println("=====================================");
                    List<Contact> allContacts = contactManager.getAllContacts();
                    for (Contact c : allContacts) {
                        System.out.println(" " + c.getName() + "\t" + c.getPhone());
                    }
                    break;
                case 0:
                    System.out.println(" Exit Program... ");
                    System.exit(0);
                    return;
                default:
                    System.out.println(" Invalid Menu! Please Try Again >>> ");
            }
        }
    }
}