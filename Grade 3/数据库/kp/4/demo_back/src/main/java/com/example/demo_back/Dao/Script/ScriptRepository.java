package com.example.demo_back.Dao.Script;

import com.example.demo_back.Dao.List_Script_Action.List_Action_Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScriptRepository extends JpaRepository<Script,Integer> {
    @Query(value="select max(id) from Script")
    List<Integer> findNewestScript();

}
