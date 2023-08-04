package com.example.demo_back.Dao.Action;

import com.example.demo_back.Dao.Enums.Action_Type;
import com.example.demo_back.Dao.Enums.Furniture_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActionRepository extends JpaRepository<ActionJpa,Integer> {
    @Query(value = "select A from ActionJpa A where A.action = ?1 and A.furniture=?2")
    List<ActionJpa> findAllByActionAndFurnitrue(Action_Type a, Furniture_Type f);

    @Query(value = "select A from ActionJpa A where A.id = ?1")
    List<ActionJpa> findActionById(Integer id);
}

