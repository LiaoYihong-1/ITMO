package com.example.demo_back.Dao.Script;

import com.example.demo_back.Dao.Action.ActionJpa;
import com.example.demo_back.Dao.Enums.PostgreSQLEnumType;
import com.example.demo_back.Dao.Enums.Problem_Type;
import com.example.demo_back.Dao.Enums.ScriptType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "script")
@TypeDef(name="pgsql_enum",typeClass = PostgreSQLEnumType.class)
public class Script {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,name="script_type")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private ScriptType scriptType;

    @Column(nullable = false,name="creator_name")
    private String creatorName;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "id",referencedColumnName = "script_id")
    private ConditionalScript conditionalScript;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "id",referencedColumnName = "script_id")
    private ScheduleScript scheduleScript;

}
