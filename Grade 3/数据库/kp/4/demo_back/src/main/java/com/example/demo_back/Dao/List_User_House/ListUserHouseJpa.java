package com.example.demo_back.Dao.List_User_House;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "list_user_house")
public class ListUserHouseJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,name = "user_id")
    private Integer user_id;
    @Column(nullable = false,name = "house_id")
    private Integer house_id;
}
