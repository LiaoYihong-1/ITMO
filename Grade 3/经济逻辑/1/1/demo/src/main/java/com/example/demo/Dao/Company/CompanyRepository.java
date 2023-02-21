package com.example.demo.Dao.Company;

import com.example.demo.Dao.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    @Query(value="select C from  Company  C where C.password=?1 and C.name = ?2")
    List<Company> login(String password, String name);

    @Query(value = "select C.name from Company C")
    List<String> findAllNames();
}
