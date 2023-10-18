import java.util.List;
import java.util.Scanner;

public class MenuViewer {
    private static Scanner scan = new Scanner(System.in);
    public MenuViewer() {}

    public static Scanner inputKeyboard() {
        return scan;
    }

    // 번호 선택
    public static int selectMenu() {
        System.out.print(" Enter The Menu >>> ");
        return scan.nextInt();
    }

    // 메뉴 보여주기
    public static void showMenu() {
        System.out.println(" [ADD: 1, DELETE: 2, SEARCH: 3, DISPLAY_LIST: 4, EXIT: 0]");
    }
}
