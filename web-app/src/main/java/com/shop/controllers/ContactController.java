package com.shop.controllers;

import com.shop.models.Contact;
import com.shop.services.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ContactController.CONTACTS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactController {
    public static final String CONTACTS_URL = "/web-api/contacts";
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> findAllContact() {
        return contactService.getContacts();
    }

    @GetMapping("/{id}")
    public Contact findByIdContact(@PathVariable int id) {
        return contactService.getContact(id);
    }

    @PostMapping
    public Contact saveContact(
        @RequestBody Contact contact,
        @RequestBody int personId
    ) {
        return contactService.addContact(contact, personId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable int id) {
        contactService.deleteContact(id);
    }
}
