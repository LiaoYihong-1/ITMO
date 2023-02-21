package com.example.demo_back.Dao.Problem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem,Integer> {
    @Query(value="select P from Problem P where P.user_id=?1")
    List<Problem> findProblemByUser_id(Integer id);
}
