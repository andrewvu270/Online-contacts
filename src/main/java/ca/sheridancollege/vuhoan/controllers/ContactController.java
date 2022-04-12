package ca.sheridancollege.vuhoan.controllers;

import ca.sheridancollege.vuhoan.beans.Contact;
import ca.sheridancollege.vuhoan.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    DatabaseAccess da;

    @GetMapping
    public List<Contact> listContact(){
        return da.listALL();
    }

    @GetMapping("/{id}")
    public Contact getIndividualContact(@PathVariable Long id){
        return da.findContactById(id);
    }


    @PostMapping(consumes = "application/json")
    public void postContact(@RequestBody Contact contact){
        da.addContact(contact);
    }

    @PutMapping(value = "/edit{id}", consumes = "application/json")
    public void putIndividual(@PathVariable Long id, @RequestBody Contact contact){
        da.updateContact(contact);
    }

    @DeleteMapping("/delete{id}")
    public void deleteIndividual(@PathVariable Long id){
        da.deleteContactById(id);
    }

}
