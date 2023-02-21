package com.example.demo_back.Dao.Problem;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "support_man")
public class SupportMan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,name="name")
    private String name;

    @Column(nullable = false,name="password")
    private String password;

    @Column(nullable = false,name="is_free")
    private Boolean free;
}
