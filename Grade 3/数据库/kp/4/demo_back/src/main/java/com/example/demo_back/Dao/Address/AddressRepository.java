package com.example.demo_back.Dao.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressJpa,Integer> {
    @Query(value = "select A from AddressJpa A where A.id=?1")
    List<AddressJpa> findAddressJpaById(Integer id);
    @Query(value="select max(id) from AddressJpa")
    List<Integer> findNewestAccount();
}
