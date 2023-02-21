package com.example.demo_back.Dao.Problem;

import com.example.demo_back.Dao.Enums.PostgreSQLEnumType;
import com.example.demo_back.Dao.Enums.Problem_Type;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "Problem")
@TypeDef(name="pgsql_enum",typeClass = PostgreSQLEnumType.class)
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,name="user_id")
    private Integer user_id;
    @Column(nullable = false,name="support_man_id")
    private Integer support_man_id;
    @Column(nullable = false,name="problem_type")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Problem_Type problem_type;
    @Column(nullable = false,name="description")
    private String description;
    @Column(nullable = false,name="is_finished")
    private boolean is_finished;
    @Column(nullable = false,name="Date")
    private Date date;
}
