package com.example.demo_back.Dao.Contact;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Data
@Entity
@Table(name = "Contact")
public class ContactJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name="user_id")
    private Integer user_id;
    @Column(nullable = false,name="email")
    private String email;
    @Column(nullable = false,name="phone")
    private String phone;
}
