package com.example.demo_back.Dao.List_User_House;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
@Repository
public interface ListUserHouseRepository extends JpaRepository<ListUserHouseJpa, Integer> {

}
