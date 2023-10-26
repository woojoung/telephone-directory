public class TelephoneDirectory {
    // Launcher 로 클래스명 변경
    public static void main(String[] args) {
        // 파일을 읽어서, 파일에 있는 행위/데이터를 읽어서 controller 에 순차적으로 요청해주도록

        ContactManager contactManager = new ContactManager();
        MenuViewer menuViewer = new MenuViewer();
        System.out.println(" Telephone Directory Management!");
        while(true){
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