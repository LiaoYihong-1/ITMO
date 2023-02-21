package com.example.demo_back.Dao.Problem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupportmanRepository extends JpaRepository<SupportMan,Integer> {
    @Query(value="select S from SupportMan S where S.free = true")
    List<SupportMan> findbyfree();

}
