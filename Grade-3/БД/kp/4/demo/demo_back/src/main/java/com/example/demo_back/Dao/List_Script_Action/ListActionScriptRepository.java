package com.example.demo_back.Dao.List_Script_Action;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListActionScriptRepository extends JpaRepository<List_Action_Script,Integer> {
    @Query(value ="select L from List_Action_Script L where L.script_id = ?1")
    List<List_Action_Script> findbyscript(Integer id);
}
