package com.shop.integration.repositories.contextconfiguration;

import com.shop.dao.ContactDao;
import com.shop.models.Contact;
import com.shop.repositories.ContactRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
    classes = {
        ContactRepository.class,
        ContactRepositoryContextConfigurationTest.TestContextConfig.class
    }
)
public class ContactRepositoryContextConfigurationTest {
    @Autowired
    private ContactDao contactDao;
    @Autowired
    private ContactRepository contactRepository;

    @Test
    @DisplayName("Get all contacts")
    void get_all_contacts() {
        contactRepository.findAll();

        verify(contactDao, atLeast(1)).findAll();
    }

    @Test
    @DisplayName("Get contact by id")
    void get_contact_by_id() {
        long id = 1;

        contactRepository.findById(id);

        verify(contactDao).findById(id);
    }

    @Test
    @DisplayName("Get contact by person")
    void get_contact_by_person() {
        long personId = 1;

        contactRepository.findByPerson(personId);

        verify(contactDao).findByPerson(personId);
    }

    @Test
    @DisplayName("Save contact")
    void save_contact() {
        long personId = 1;
        String email = "test@email.com";
        String phone = "+380000000000";
        String password = "password";

        contactRepository.save(email, phone, password, personId);

        verify(contactDao).save(email, phone, password, personId);
    }

    @Test
    @DisplayName("Update contact")
    void update_contact() {
        long id = 1;
        String phone = "+3801111111111";

        contactRepository.update(id, phone);

        verify(contactDao).update(id, phone);
    }

    @Test
    @DisplayName("Delete contact")
    void delete_contact() {
        long id = 1;

        contactRepository.delete(id);

        verify(contactDao).delete(id);
    }

    @Test
    @DisplayName("Count contacts")
    void count_contacts() {
        contactRepository.count();

        verify(contactDao, atLeast(1)).findAll();
    }

    @TestConfiguration
    static class TestContextConfig {
        @Bean
        public Contact contact() {
            return mock(Contact.class);
        }

        @Bean
        public ContactDao contactDao() {
            return mock(ContactDao.class);
        }
    }
}
