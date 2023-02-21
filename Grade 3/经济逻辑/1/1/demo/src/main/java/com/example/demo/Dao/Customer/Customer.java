package com.example.demo.Dao.Customer;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Userlab1")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,name="name")
    private String name;
    @Column(nullable = false,name="password")
    private String password;
}
