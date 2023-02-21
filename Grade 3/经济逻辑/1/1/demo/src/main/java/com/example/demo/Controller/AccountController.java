package com.example.demo.Controller;


import com.example.demo.Dao.Company.Company;
import com.example.demo.Dao.Company.CompanyRepository;
import com.example.demo.Dao.Customer.Customer;
import com.example.demo.Dao.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class AccountController {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CustomerRepository customerRepository;
    public boolean checkString(String s){
        if ((s == null) | (Objects.equals(s, ""))){
            return false;
        }
        return true;
    }
    @PostMapping("/login")
    @ResponseBody
    //@RequestMapping(value="/login/{username}/{password}",method = {RequestMethod.GET})
    public String login(HttpServletRequest request){
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        if(!checkString(name)||!checkString(password)||!checkString(type)){
            return "Fail";
        }
        if("company".equalsIgnoreCase(type)){
            if(companyRepository.login(password,name).size()!=1){
                return "Fail";
            }
        }else if ("customer".equalsIgnoreCase(type)) {
            if(customerRepository.login(password,name).size()!=1){
                return "Fail";
            }
        }else{
            return "Fail";
        }
        return "Success";
    }
    @PostMapping("/register")
    @ResponseBody
    //@RequestMapping(value="/login/{username}/{password}",method = {RequestMethod.GET})
    public String signup(HttpServletRequest request){
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        if(!checkString(name)||!checkString(password)||!checkString(type)){
            return "Fail";
        }
        if("company".equalsIgnoreCase(type)){
            List<String> allName = companyRepository.findAllNames();
            if(allName.contains(name)){
                return "Account exists";
            }
            Company company = new Company();
            company.setName(name);
            company.setPassword(password);
            companyRepository.save(company);
        } else if ("customer".equalsIgnoreCase(type)) {
            List<String> allName = customerRepository.findAllNames();
            if(allName.contains(name)){
                return "Account exist";
            }
            Customer customer = new Customer();
            customer.setName(name);
            customer.setPassword(password);
            customerRepository.save(customer);
        }else{
            return "Fail";
        }
        return "Success";
    }
}
