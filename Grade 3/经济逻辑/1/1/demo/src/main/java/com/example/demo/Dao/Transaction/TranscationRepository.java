package com.example.demo.Dao.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TranscationRepository extends JpaRepository<Transaction,Integer> {
    @Query(value = "select T FROM Transaction T where T.costomer = ?1")
    List<Transaction> findByCostomer(Integer customer);
}
