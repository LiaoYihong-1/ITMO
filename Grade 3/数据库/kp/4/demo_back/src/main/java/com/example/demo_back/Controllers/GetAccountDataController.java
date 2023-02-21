package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.Account.AccountJpa;
import com.example.demo_back.Dto.GetDataResponse;
import com.example.demo_back.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;
@Controller
public class GetAccountDataController {
    @Autowired
    AccountService accountService;
    @PostMapping("/metadata")
    @ResponseBody
    public GetDataResponse main(HttpServletRequest request){
        GetDataResponse getDataResponse = new GetDataResponse();
        if (request.getParameter("username") == null || request.getParameter("password") == null || request.getParameter("username").equals("") || request.getParameter("password").equals("")) {
            getDataResponse.setSuccess(false);
            getDataResponse.setMessage("Username or password can't be empty");
            return getDataResponse;
        }
        String username = request.getParameter("username");
        String regexPhone = "\\+[1-9]+[0-9]*";
        String regexEmail = ".*@.+\\.com";
        if (Pattern.matches(regexPhone,username)) {
            getDataResponse.setSuccess(false);
            getDataResponse.setMessage("No such phone");
        }else if (Pattern.matches(regexEmail,username)){
            AccountJpa l = accountService.findAccountByEmail(username);
            getDataResponse.setAge(l.getAge());
            getDataResponse.setGender(l.getGender().name());
            getDataResponse.setPhone(l.getContactJpa().getPhone());
            getDataResponse.setEmail(l.getContactJpa().getEmail());
            getDataResponse.setUsername(l.getName());
            getDataResponse.setMessage("Success");
            getDataResponse.setSuccess(true);
        }else{
            getDataResponse.setSuccess(false);
            getDataResponse.setMessage("Make sure you input an email or phone");
        }
        return getDataResponse;
    }
}
