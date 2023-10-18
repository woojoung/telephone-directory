public class TelephoneDirectory {
    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager("telephone_directory.txt");
        MenuViewer menuViewer = new MenuViewer();
        System.out.println(" Telephone Directory Management!");
        while(true){
            // 1. 번호 추가
            // 2. 번호 삭제
            // 3. 번호 조회
            // 4. 목록 조회
            // 0. 종료
            menuViewer.showMenu();
            int menu = menuViewer.selectMenu();
            switch (menu){
                case 1:
                    contactManager.addContact();
                    break;
                case 2:
                    contactManager.removeContact();
                    break;
                case 3:
                    contactManager.getOneContact();
                    break;
                case 4:
                    contactManager.getAllContacts();
                    break;
                case 0:
                    contactManager.exit();
                    return;
                default:
                    System.out.println(" Invalid Menu! Please Try Again >>> ");
            }
        }
    }
}