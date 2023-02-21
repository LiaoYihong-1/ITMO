package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.Account.AccountJpa;
import com.example.demo_back.Dao.Address.AddressJpa;
import com.example.demo_back.Dao.House.HouseJpa;
import com.example.demo_back.Dao.Problem.Problem;
import com.example.demo_back.Dao.Problem.ProblemRepository;
import com.example.demo_back.Dto.House;
import com.example.demo_back.Service.AccountService;
import com.example.demo_back.Service.AddressService;
import com.example.demo_back.Service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GetProblemsController {
    @Autowired
    ProblemRepository repository;
    @PostMapping("/getproblems")
    @ResponseBody
    public List<Problem> main(HttpServletRequest request) {
        List<Problem> houseList = new ArrayList<>();
        Integer id = Integer.parseInt(request.getParameter("id"));
        List<Problem> problemList = repository.findProblemByUser_id(id);
        return problemList;
    }
}
