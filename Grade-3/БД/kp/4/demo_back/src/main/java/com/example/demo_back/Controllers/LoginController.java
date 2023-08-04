package com.example.demo_back.Controllers;
import com.example.demo_back.Dao.Account.AccountJpa;
import com.example.demo_back.Dto.LoginResponse;
import com.example.demo_back.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Pattern;

import com.example.demo_back.Dto.UniResponse;
@Controller
public class LoginController {
    @Autowired
    AccountService accountService;
    @PostMapping("/login")
    @ResponseBody
    public LoginResponse main(HttpServletRequest request) {
        LoginResponse uniResponse = new LoginResponse();
        if (request.getParameter("username") == null || request.getParameter("password") == null || request.getParameter("username").equals("") || request.getParameter("password").equals("")) {
            uniResponse.setSuccess(false);
            uniResponse.setMessage("Username or password can't be empty");
            return uniResponse;
        }
        System.out.println("sadasdasdasdasdasd");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String regexPhone = "\\+[1-9]+[0-9]*";
        String regexEmail = ".*@.+\\.com";
        if (Pattern.matches(regexPhone,username)) {
            uniResponse.setSuccess(false);
            uniResponse.setMessage("No such phone");
        }else if (Pattern.matches(regexEmail,username)){
            AccountJpa accountJpa = accountService.findAccountByEmail(username);
            if (accountJpa == null){
                uniResponse.setSuccess(false);
                uniResponse.setMessage("Password or account is wrong");
                return uniResponse;
            }
            String a = accountJpa.getPassword();
            if (a==null){
                uniResponse.setSuccess(false);
                uniResponse.setMessage("No such phone");
            }else {
                if (a.equals(password)) {
                    uniResponse.setSuccess(true);
                    uniResponse.setMessage("Success");
                    uniResponse.setId(accountJpa.getId());
                } else {
                    uniResponse.setSuccess(false);
                    uniResponse.setMessage("phone or password wrong");
                }
            }
        }else{
            uniResponse.setSuccess(false);
            uniResponse.setMessage("Make sure you input an email or phone");
        }
        return uniResponse;
    }
    private String passwordSHA(String password){
        byte [] bytes = password.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(bytes);
            return new BigInteger(messageDigest.digest()).toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

}