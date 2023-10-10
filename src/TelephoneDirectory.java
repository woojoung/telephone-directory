import java.util.HashMap;
import java.util.Scanner;

// 클래스 TelephoneDirectory
public class TelephoneDirectory {
//    static HashMap<String, String> contracts = new HashMap<String, String>();
    public static void main(String[] args) {
        String phone, name, value;
        boolean isExists;
        HashMap<String, String> contracts = new HashMap<String, String>();
        System.out.println(" Telephone Directory Management!");
        while(true){
            System.out.println(" [ADD: 1, DELETE: 2, SEARCH: 3, DISPLAY_LIST: 4, EXIT: 0]");
            System.out.print(" Enter The Menu >>> ");

            Scanner scan = new Scanner(System.in);
            int menu = scan.nextInt();
            // 1. 번호 추가
            // 2. 번호 삭제
            // 3. 번호 조회
            // 4. 목록 조회
            // 0. 종료
            switch (menu){
                case 1:
                    System.out.print(" Enter The Phone Number >>> ");
                    phone = scan.next();
                    isExists = contracts.containsKey(phone);
                    if (isExists) {
                        System.out.println(" Mobile Number Already Exists!!");
                        break;
                    };
                    System.out.print(" Enter The Name >>> ");
                    name = scan.next();
                    contracts.put(phone, name);
                    System.out.println(" Added Contract!!");
                    break;
                case 2:
                    System.out.print(" Enter The Phone Number >>> ");
                    phone = scan.next();
                    value = contracts.get(phone);
                    if (value == null) {
                        System.out.println(" Not Found Contract!!");
                        break;
                    };
                    contracts.remove(phone);
                    System.out.println(" Deleted Contract!!");
                    break;
                case 3:
                    System.out.print(" Enter The Phone Number >>> ");
                    phone = scan.next();
                    value = contracts.get(phone);
                    if (value == null) {
                        System.out.println(" Not Found Contract!!");
                        break;
                    };
                    System.out.println(value + "\t" + phone);
                    break;
                case 4:
                    System.out.println("=====================================");
                    System.out.println(" Name\tPhone ");
                    System.out.println("=====================================");
                    for (String key: contracts.keySet()) {
                        value = contracts.get(key);
                        System.out.println(value + "\t" + key);
                    }
                    break;
                case 0:
                    contracts.clear();
                    System.out.println(" Cleared Contract!!");
                    System.out.println(" Exit Program... ");
                    return;
                default:
                    System.out.println(" Invalid Menu! Please Try Again >>> ");
            }
        }
    }
}