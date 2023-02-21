package com.example.demo.Dao.Transaction;

import lombok.Data;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,name="device")
    private Integer device;
    @Column(nullable = false,name="seller")
    private Integer seller;
    @Column(nullable = false,name="customer")
    private Integer costomer;
    @Column(nullable = false,name="amount")
    private Integer mount;
    @Column(nullable = false,name = "status")
    private String status;
}
