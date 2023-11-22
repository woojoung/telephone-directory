import dto.Contact;
import utils.CustomLogger;

import java.util.List;

/*
 * Presentation Layer
 * Request, Response 처리
 * */
public class ContactController {
    private final ContactService contactService = new ContactService();
    CustomLogger logger = CustomLogger.getInstance();

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
        List<Contact> contactList = contactService.findAllContact();
        for (Contact contact: contactList) {
            this.logger.debug("Name: " + contact.getName());
            this.logger.debug("PhoneNumber: " + contact.getPhoneNumber());
        }
    }
}
