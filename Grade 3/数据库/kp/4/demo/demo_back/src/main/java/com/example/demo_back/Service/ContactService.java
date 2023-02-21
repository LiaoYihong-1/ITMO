package com.example.demo_back.Service;

import com.example.demo_back.Dao.Contact.ContactJpa;
import com.example.demo_back.Dao.Contact.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public void addContact(Integer id, String phone, String email){
        ContactJpa contactJpa = new ContactJpa();
        contactJpa.setEmail(email);
        contactJpa.setUser_id(id);
        contactJpa.setPhone(phone);
        System.out.println(id);
        contactRepository.save(contactJpa);
    }
}
