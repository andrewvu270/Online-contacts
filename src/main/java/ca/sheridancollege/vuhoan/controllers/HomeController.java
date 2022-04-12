package ca.sheridancollege.vuhoan.controllers;

import ca.sheridancollege.vuhoan.beans.Contact;
import ca.sheridancollege.vuhoan.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Controller
public class HomeController {

    @Autowired
    @Lazy
    private DatabaseAccess da;

    @GetMapping("/")
    public String processIndex() {
        return "index";
    }



    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }


    @GetMapping("/accessdenied")
    public String noPermission() {

        return "/error/accessdenied";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam String username, @RequestParam String password,
                               @RequestParam String[] role){
        da.registerUser(username, password);
        Long userId = da.findUserAccount(username).getUserId();
        for(String r : role)
            da.addRole(userId, Long.parseLong(r));
        return "redirect:/";
    }


    @GetMapping("/secure/listContact")
    public String listContacts(Model model, RestTemplate
            restTemplate) {
        ResponseEntity<Contact[]> responseEntity =
                restTemplate.getForEntity
                        ("http://localhost:8080/contacts", Contact[].class);
        model.addAttribute("contactList", responseEntity.getBody());
        return "/secure/listContact";
    }

    @GetMapping("/secure/addContact")
    public String addContact(Model model){
        model.addAttribute("contact", new Contact());
        return "/secure/addContact";
    }

    @PostMapping("/addContact")
    public String addContacts(Model model, RestTemplate restTemplate, @ModelAttribute Contact contact) {
        ResponseEntity<Contact> responseEntity =
                restTemplate.postForEntity("http://localhost:8080/contacts", contact, Contact.class);
        ResponseEntity<Contact[]> responseEntity2 =
                restTemplate.getForEntity
                        ("http://localhost:8080/contacts", Contact[].class);
        model.addAttribute("contactList", responseEntity2.getBody());
        return "/secure/listContact";

    }

    @GetMapping("/deleteContactById/{id}")
    public String deleteContactById(Model model, @PathVariable Long id, RestTemplate restTemplate){
        restTemplate.delete("http://localhost:8080/contacts/delete" + id);
        ResponseEntity<Contact[]> responseEntity2 =
                restTemplate.getForEntity
                        ("http://localhost:8080/contacts", Contact[].class);
        model.addAttribute("contactList", responseEntity2.getBody());
        return "/secure/listContact";
    }

    @GetMapping("/editContactById/{id}")
    public String editContactById(Model model, @PathVariable Long id, RestTemplate restTemplate){
        ResponseEntity<Contact> responseEntity =
                restTemplate.getForEntity
                        ("http://localhost:8080/contacts/" + id, Contact.class);
        model.addAttribute("contact", responseEntity.getBody());
        return "/secure/edit";
    }

    @PostMapping("/updateContact")
    public String modifyContact(Model model, @ModelAttribute Contact contact, RestTemplate restTemplate){
        restTemplate.put("http://localhost:8080/contacts/edit" + contact.getId(), contact);
        ResponseEntity<Contact[]> responseEntity2 =
                restTemplate.getForEntity
                        ("http://localhost:8080/contacts", Contact[].class);
        model.addAttribute("contactList", responseEntity2.getBody());
        return "/secure/listContact";
    }

}