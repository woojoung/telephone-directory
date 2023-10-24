public class TelephoneDirectory {
    public static void main(String[] args) {
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