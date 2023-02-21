package com.example.demo.Dao.Company;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Companylab1")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,name="name")
    private String name;
    @Column(nullable = false,name="password")
    private String password;
}
