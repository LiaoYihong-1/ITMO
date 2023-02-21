package com.example.demo_back.Service;

import com.example.demo_back.Dao.Problem.Problem;
import com.example.demo_back.Dao.Problem.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {
    @Autowired
    ProblemRepository problemRepository;



}
