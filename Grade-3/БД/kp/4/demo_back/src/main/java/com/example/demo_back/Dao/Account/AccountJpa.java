package com.example.demo_back.Dao.Account;

import com.example.demo_back.Dao.Contact.ContactJpa;
import com.example.demo_back.Dao.Enums.Gender;
import com.example.demo_back.Dao.Enums.PostgreSQLEnumType;
import com.example.demo_back.Dao.House.HouseJpa;
import com.example.demo_back.Dao.Problem.Problem;
import com.example.demo_back.Dao.Script.ScheduleScript;
import com.example.demo_back.Dao.Script.Script;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Account")
@TypeDef(name="pgsql_enum",typeClass = PostgreSQLEnumType.class)
public class AccountJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,name="name")
    private String name;
    @Column(nullable = false,name="gender")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Gender gender;
    @Column(nullable = false,name="password")
    private String password;
    @Column(nullable = false,name="age")
    private Integer age;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id",referencedColumnName = "user_id")
    private ContactJpa contactJpa;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)//级联保存，懒加载
    //会创建一个man_hobby的维护表，关联man和hobby的id关系
    @JoinTable(name = "list_user_house",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "house_id", referencedColumnName ="id")})
    private List<HouseJpa> houses;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)//级联保存，懒加载
    //会创建一个man_hobby的维护表，关联man和hobby的id关系
    @JoinTable(name = "list_script_user",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "script_id", referencedColumnName ="id")})
    private List<Script> scripts;

}