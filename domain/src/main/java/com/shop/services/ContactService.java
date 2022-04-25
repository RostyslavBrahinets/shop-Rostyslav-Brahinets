package com.shop.services;

import com.shop.exceptions.NotFoundException;
import com.shop.models.Contact;
import com.shop.repositories.ContactRepository;
import com.shop.validators.ContactValidator;

import java.util.List;
import java.util.Optional;

public class ContactService {
    private final ContactRepository contactRepository;
    private final ContactValidator validator;

    public ContactService(
        ContactRepository contactRepository,
        ContactValidator validator
    ) {
        this.contactRepository = contactRepository;
        this.validator = validator;
    }

    public List<Contact> getContacts() {
        return contactRepository.getContacts();
    }

    public Contact addContact(Contact contact, int personId) {
        validator.validate(contact);
        contactRepository.addContact(contact, personId);
        return contact;
    }

    public void deleteContact(int id) {
        validator.validate(id);
        contactRepository.deleteContact(id);
    }

    public Contact getContact(int id) {
        validator.validate(id);
        Optional<Contact> contact = contactRepository.getContact(id);
        if (contact.isEmpty()) {
            throw new NotFoundException("Contact not found");
        } else {
            return contact.get();
        }
    }
}
