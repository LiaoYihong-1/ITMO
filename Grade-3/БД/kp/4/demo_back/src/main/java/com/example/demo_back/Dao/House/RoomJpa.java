package com.example.demo_back.Dao.House;

import com.example.demo_back.Dao.Enums.House_Type;
import com.example.demo_back.Dao.Enums.PostgreSQLEnumType;
import com.example.demo_back.Dao.Enums.Room_Type;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Room")
@TypeDef(name="pgsql_enum",typeClass = PostgreSQLEnumType.class)
public class RoomJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,name = "house_id")
    private Integer house_id;

    @Column(nullable = false,name="square")
    private Double square;

    @Column(nullable = false,name="height")
    private Double height;

    @Column(nullable = false,name="is_filled")
    private Boolean is_filled;

    @Column(nullable = false,name="room_type")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Room_Type room_type;

}
