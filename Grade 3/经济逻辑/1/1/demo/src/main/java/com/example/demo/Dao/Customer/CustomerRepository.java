package com.example.demo.Dao.Customer;

import com.example.demo.Dao.Company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value="select C from Customer C where C.password=?1 and C.name = ?2")
    List<Customer> login(String password, String name);

    @Query(value = "select C.name from Customer C")
    List<String> findAllNames();
}
