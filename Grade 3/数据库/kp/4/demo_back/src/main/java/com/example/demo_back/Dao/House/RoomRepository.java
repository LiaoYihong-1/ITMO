package com.example.demo_back.Dao.House;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomJpa,Integer> {
    @Query(value="select R from RoomJpa R where R.house_id = ?1")
    List<RoomJpa> findRoomJpaByHouse_id(Integer id);
}
