package angel.Contacts.Controller;

import angel.Contacts.Entity.Contact;
import angel.Contacts.Service.ContactService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactService contactService;

    @GetMapping("/")
    public String start(ModelMap model) {
        List<Contact> listContacts = contactService.listContacts();
        listContacts.forEach(contact -> logger.info(contact.toString()));
        model.put("contacts", listContacts);

        return "index";
    }

    @GetMapping("/newContact")
    public String showContactForm() {
        return "contactForm";
    }

    @PostMapping("/newContact")
    public String newContact(@ModelAttribute("contactForm") Contact contact) {

        try {
            if (contact != null) {
                contactService.saveContact(contact);
                logger.info("Contact was added: "+ contact);
            }
            return "redirect:/";

        } catch (RuntimeException e) {
            logger.info("An error ocurred: " + e.getMessage());
        }
        return "contactForm";

    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Integer id, ModelMap model){
        Contact contact= contactService.findContactById(id);
        model.put("contact", contact);
        return "updateContactForm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("contact") Contact contact) {

        try {
            if (contact != null) {
                contactService.saveContact(contact);
                logger.info("Contact was updated: "+ contact);
            }
            return "redirect:/";

        } catch (RuntimeException e) {
            logger.info("An error ocurred: " + e.getMessage());
        }
        return "updateContactForm";
    }
@GetMapping("delete/{id}")
    public String delete(@PathVariable("id")Integer idContact){
        try{
            Contact contact= contactService.findContactById(idContact);
            if(contact!=null)
                contactService.removeContact(contact);
            logger.info("Contact was remove: "+ contact);
            return "redirect:/";
        }catch (RuntimeException e){
            logger.info("An error ocurred: " + e.getMessage());
        }
        return "redirect:/";
}


}
