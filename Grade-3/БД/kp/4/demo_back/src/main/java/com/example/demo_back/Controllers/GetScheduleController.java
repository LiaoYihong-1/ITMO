package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.List_Script_User.ListScriptUserRepository;
import com.example.demo_back.Dao.Script.*;
import com.example.demo_back.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GetScheduleController {
    @Autowired
    ScriptRepository scriptRepository;
    @Autowired
    ListScriptUserRepository listScriptUserRepository;
    @Autowired
    ConditionalScriptRepository conditionalScriptRepository;
    @Autowired
    ScheduleScriptRepository scheduleScriptRepository;
    @Autowired
    AccountService accountService;
    @ResponseBody
    @PostMapping("/getschedule")
    public List<ScheduleScript> main(HttpServletRequest request){
        String user = request.getParameter("id");
        List<Script> l = accountService.findAccountById(Integer.valueOf(user)).getScripts();
        List<ScheduleScript> result = new ArrayList<>();
        for(Script s : l){
            if(s.getScheduleScript()!=null){
                result.add(s.getScheduleScript());
            }
        }
        return result;
    }
}
