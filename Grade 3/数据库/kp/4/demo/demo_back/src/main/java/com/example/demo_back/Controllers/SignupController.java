package com.example.demo_back.Controllers;
import com.example.demo_back.Service.AccountService;
import com.example.demo_back.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import com.example.demo_back.Dto.UniResponse;
@Controller
public class SignupController {
    @Autowired
    AccountService accountService;
    @Autowired
    ContactService contactService;
    public boolean checkString(String s){
        if ((s == null) | (Objects.equals(s, ""))){
            return false;
        }
        return true;
    }
    @PostMapping("/signup")
    @ResponseBody
    public UniResponse main(HttpServletRequest request) {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        Integer age = Integer.parseInt(request.getParameter("age"));
        UniResponse uniResponse = new UniResponse();
        System.out.println(age.intValue());
        if(checkString(name)&&checkString(phone)&&checkString(email)&&checkString(gender)&&checkString(password)) {
            if (accountService.findAccountByEmail(email)!=null || accountService.findAccountByPhone(phone)!=null){
                uniResponse.setSuccess(false);
                uniResponse.setMessage("This phone or email is used");
                return uniResponse;
            }
            accountService.addAccount(password,name,gender,age);
            uniResponse.setMessage("Success");
            Integer id = accountService.findNewestId();
            contactService.addContact(id,phone,email);
            uniResponse.setSuccess(true);
        }else {
            uniResponse.setMessage("You sent an invalid value to server!\n");
            uniResponse.setSuccess(false);
        }
        return uniResponse;
    }

}