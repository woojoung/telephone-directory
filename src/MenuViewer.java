import java.util.List;
import java.util.Scanner;

public class MenuViewer {
    private static Scanner scan = new Scanner(System.in);
    public MenuViewer() {}

    // 번호 선택
    public int selectMenu() {
        System.out.print(" Enter The Menu >>> ");
        return scan.nextInt();
    }

    // 메뉴 보여주기
    public void showMenu() {
        System.out.println(" [ADD: 1, DELETE: 2, SEARCH: 3, DISPLAY_LIST: 4, EXIT: 0]");
    }
}
