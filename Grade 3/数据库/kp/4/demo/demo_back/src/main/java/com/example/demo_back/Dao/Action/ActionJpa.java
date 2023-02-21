package com.example.demo_back.Dao.Action;

import com.example.demo_back.Dao.Enums.Action_Type;
import com.example.demo_back.Dao.Enums.City;
import com.example.demo_back.Dao.Enums.Furniture_Type;
import com.example.demo_back.Dao.Enums.PostgreSQLEnumType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Action")
@TypeDef(name="pgsql_enum",typeClass = PostgreSQLEnumType.class)
public class ActionJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,name="action_type")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Action_Type action;

    @Column(nullable = false,name="type_furniture")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Furniture_Type furniture;

}
