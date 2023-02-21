package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.Account.AccountRepository;
import com.example.demo_back.Dao.Enums.ScriptType;
import com.example.demo_back.Dao.List_Script_User.ListScriptUserRepository;
import com.example.demo_back.Dao.List_Script_User.List_Script_User;
import com.example.demo_back.Dao.Script.*;
import com.example.demo_back.Dto.UniResponse;
import com.example.demo_back.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.Objects;

@Controller
public class AddScriptController {
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
    public boolean checkString(String s){
        if ((s == null) | (Objects.equals(s, ""))){
            return false;
        }
        return true;
    }
    @ResponseBody
    @PostMapping("/addscript")
    public UniResponse main(HttpServletRequest request){
        UniResponse response = new UniResponse();
        response.setSuccess(true);
        response.setMessage("Success");
        System.out.println("asdvsadasdasd");
        String t = request.getParameter("type");
        if("condition".equals(t)){
            String condition = request.getParameter("condition");
            if(!checkString(condition)||!checkString(request.getParameter("user_id"))){
                response.setSuccess(false);
                response.setMessage("Check you set your condition or log in again");
                return response;
            }
            Integer user_id = Integer.valueOf(request.getParameter("user_id"));
            Script script = new Script();
            script.setScriptType(ScriptType.CONDITIONAL);
            script.setCreatorName(accountService.findAccountById(user_id).getName());
            ConditionalScript conditionalScript = new ConditionalScript();
            conditionalScript.setCondition(condition);
            Integer script_id = scriptRepository.findNewestScript().get(0);
            conditionalScript.setScript_id(script_id);
            scriptRepository.save(script);
            conditionalScriptRepository.save(conditionalScript);
            List_Script_User list_script_user = new List_Script_User();
            list_script_user.setScript_id(script_id);
            list_script_user.setUser_id(user_id);
            listScriptUserRepository.save(list_script_user);
        }else{
            String time = request.getParameter("time");
            System.out.println("Time is:");
            System.out.println(time);
            if(!checkString(time)||!checkString(request.getParameter("user_id"))){
                response.setSuccess(false);
                response.setMessage("Check you set your condition or log in again");
                return response;
            }
            Integer user_id = Integer.valueOf(request.getParameter("user_id"));
            Script script = new Script();
            script.setScriptType(ScriptType.SCHEDULE);
            script.setCreatorName(accountService.findAccountById(user_id).getName());
            scriptRepository.save(script);
            ScheduleScript scheduleScript = new ScheduleScript();
            Integer script_id = scriptRepository.findNewestScript().get(0);
            scheduleScript.setScript_id(script_id);
            Time time1 = new Time(0);
            String [] hm = request.getParameter("time").split(":");
            time1.setHours(Integer.parseInt(hm[0]));
            time1.setMinutes(Integer.parseInt(hm[1]));
            scheduleScript.setTime(time1);
            scheduleScriptRepository.save(scheduleScript);
            List_Script_User list_script_user = new List_Script_User();
            list_script_user.setScript_id(script_id);
            list_script_user.setUser_id(user_id);
            listScriptUserRepository.save(list_script_user);
        }
        return response;
    }
}
