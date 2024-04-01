package angel.Contacts.Service;

import angel.Contacts.Entity.Contact;

import java.util.List;

public interface IContactService {

    public void saveContact(Contact contact);
    public Contact findContactById(Integer idContact);
    public List<Contact> listContacts();
    public void removeContact(Contact contact);

}
