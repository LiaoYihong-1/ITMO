package com.example.demo_back.Dao.List_Script_Action;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "List_Action_Script")
public class List_Action_Script {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,name = "action_id")
    private Integer action_id;
    @Column(nullable = false,name = "script_id")
    private Integer script_id;
    @Column(nullable = false,name = "furniture_id")
    private Integer furniture_id;
}
