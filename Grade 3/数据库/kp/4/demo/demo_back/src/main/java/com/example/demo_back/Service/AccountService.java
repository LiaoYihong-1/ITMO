package com.example.demo_back.Service;

import com.example.demo_back.Dao.Account.AccountJpa;
import com.example.demo_back.Dao.Account.AccountRepository;
import com.example.demo_back.Dao.Enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public void addAccount(String password,String name, String gender,Integer age){
        AccountJpa accountJpa = new AccountJpa();
        accountJpa.setAge(age);
        accountJpa.setGender(Gender.valueOf(gender.toUpperCase()));
        accountJpa.setName(name);
        accountJpa.setPassword(password);
        accountRepository.save(accountJpa);
    }
    public Integer findNewestId(){
        return accountRepository.findNewestAccount().get(0);
    }
    public List<String> findPasswordByEmail(String email){
        return accountRepository.findPasswordJpaByEmail(email);
    }
    public AccountJpa findAccountByEmail(String email){
        if (accountRepository.findAccountJpaByEmail(email).size()==0){
            return null;
        }
        return accountRepository.findAccountJpaByEmail(email).get(0);
    }
    public AccountJpa findAccountByPhone(String phone){
        if (accountRepository.findAccountJpaByPhone(phone).size()==0){
            return null;
        }
        return accountRepository.findAccountJpaByPhone(phone).get(0);
    }
    public AccountJpa findAccountById(Integer id){
        if (accountRepository.findAccountJpaById(id).size()==0){
            return null;
        }
        return accountRepository.findAccountJpaById(id).get(0);
    }
}
