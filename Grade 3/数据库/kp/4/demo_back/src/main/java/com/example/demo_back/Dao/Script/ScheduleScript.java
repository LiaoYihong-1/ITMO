package com.example.demo_back.Dao.Script;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Data
@Entity
@Table(name = "schedule_script")
public class ScheduleScript {
    @Id
    private Integer script_id;
    @Column(nullable = false,name="time")
    private Time time;

}
