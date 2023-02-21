package com.example.demo.Dao.Device;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,name = "type")
    private String type;
    @Column(nullable = false,name = "companyid")
    private Integer company;
    @Column(nullable = false,name="value")
    private Integer value;
}
