package com.example.demo_back.Dao.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountJpa,Integer> {
    @Query(value="select max(id) from AccountJpa")
    List<Integer> findNewestAccount();

    @Query(value="select password from Account A inner join contact C on A.id = C.user_id where C.email = ?1 ",nativeQuery = true)
    List<String> findPasswordJpaByEmail(String email);

    @Query(value="select A from AccountJpa A where A.contactJpa.email = ?1 ")
    List<AccountJpa> findAccountJpaByEmail(String email);

    @Query(value="select A from AccountJpa A where A.contactJpa.phone = ?1 ")
    List<AccountJpa> findAccountJpaByPhone(String phone);

    @Query(value="select A from AccountJpa A where A.id = ?1 ")
    List<AccountJpa> findAccountJpaById(Integer id);
}
