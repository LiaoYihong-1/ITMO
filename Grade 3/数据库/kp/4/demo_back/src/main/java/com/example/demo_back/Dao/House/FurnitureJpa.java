package com.example.demo_back.Dao.House;

import com.example.demo_back.Dao.Enums.Furniture_Type;
import com.example.demo_back.Dao.Enums.PostgreSQLEnumType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@Entity
@Table(name = "furniture")
@TypeDef(name="pgsql_enum",typeClass = PostgreSQLEnumType.class)
public class FurnitureJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name="room_id")
    private Integer room_id;

    @Column(nullable = false,name = "manufacture")
    private String manufacture;

    @Column(nullable = false,name = "available")
    private Boolean available;
    @Column(nullable = false,name="furniture_type")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Furniture_Type furniture_type;


}
