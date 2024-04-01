package angel.Contacts.Service;

import angel.Contacts.Entity.Contact;
import angel.Contacts.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements IContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public Contact findContactById(Integer idContact) {
        Contact contact= contactRepository.findById(idContact).orElse(null);
        return contact;
    }

    @Override
    public List<Contact> listContacts() {
        return contactRepository.findAll();
    }

    @Override
    public void removeContact(Contact contact) {
        contactRepository.delete(contact);
  }
}
