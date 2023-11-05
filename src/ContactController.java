import dto.Contact;

/*
 * Presentation Layer
 * Request, Response 처리
 * */
public class ContactController {
    private final ContactService contactService = new ContactService();

    public void insertContact(Contact contact) {
        contactService.insertContact(contact);
    }

    public void updateContact(Contact contact) {
        contactService.updateContact(contact);
    }

    public void deleteContact(Contact contact) {
        contactService.deleteContact(contact);
    }

    public void findContact() {
        contactService.findAllContact();
    }
}
