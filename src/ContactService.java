import dto.Contact;

import java.util.List;

/*
 * Service Layer
 * Business Logic리 처리
 * Service 는 통일해도 되고 나눠도 된다.
 * Controller 로 전달
 * */
public class ContactService {
    ContactRepository contactRepository = new ContactRepository();

    public void insertContact(Contact contact) {
        // findOne
        String name = contact.getName();
        String phoneNumber = contact.getPhoneNumber();
        Contact findOneContact = this.contactRepository.findByPhoneNumber(phoneNumber);
        if (findOneContact != null) {
            return;
        }
        this.contactRepository.insert(name, phoneNumber);
    }

    public void updateContact(Contact contact) {
        // findOne
        String name = contact.getName();
        String phoneNumber = contact.getPhoneNumber();
        Contact findOneContact = this.contactRepository.findByPhoneNumber(phoneNumber);
        if (findOneContact == null) {
            return;
        }
        this.contactRepository.update(name, phoneNumber);
    }

    public void deleteContact(Contact contact) {
        String name = contact.getName();
        String phoneNumber = contact.getPhoneNumber();
        Contact findOneContact = this.contactRepository.findByPhoneNumber(phoneNumber);
        if (findOneContact == null) {
            return;
        }
        this.contactRepository.delete(name, phoneNumber);
    }

    public List<Contact> findAllContact() {
        return this.contactRepository.findAll();
    }

}
