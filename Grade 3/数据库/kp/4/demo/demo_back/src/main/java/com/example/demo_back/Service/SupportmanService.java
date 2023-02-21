package com.example.demo_back.Service;

import com.example.demo_back.Dao.Problem.SupportMan;
import com.example.demo_back.Dao.Problem.SupportmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SupportmanService {
    @Autowired
    SupportmanRepository supportmanRepository;

    public SupportMan findFreeSupport(){
        List<SupportMan> l = supportmanRepository.findbyfree();
        if (l.size()!=0) {
            return supportmanRepository.findbyfree().get(0);
        }else{
            return null;
        }
    }

}
