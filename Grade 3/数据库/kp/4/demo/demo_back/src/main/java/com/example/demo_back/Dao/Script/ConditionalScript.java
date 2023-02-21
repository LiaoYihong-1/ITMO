package com.example.demo_back.Dao.Script;

import com.example.demo_back.Dao.Enums.PostgreSQLEnumType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Condition_script")
@TypeDef(name="pgsql_enum",typeClass = PostgreSQLEnumType.class)
public class ConditionalScript {
    @Id
    private Integer script_id;
    @Column(nullable = false,name="condition")
    private String condition;
}
