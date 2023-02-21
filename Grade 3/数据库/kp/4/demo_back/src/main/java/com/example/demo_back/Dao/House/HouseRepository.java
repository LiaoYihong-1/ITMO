package com.example.demo_back.Dao.House;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseRepository extends JpaRepository<HouseJpa,Integer> {
    @Query(value="select max(id) from HouseJpa ")
    List<Integer> findNewestid();
}
