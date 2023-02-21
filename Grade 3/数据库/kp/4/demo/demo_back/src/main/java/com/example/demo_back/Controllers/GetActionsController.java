package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.Action.ActionJpa;
import com.example.demo_back.Dao.Action.ActionRepository;
import com.example.demo_back.Dao.Enums.Action_Type;
import com.example.demo_back.Dao.House.FunitureRepository;
import com.example.demo_back.Dao.List_Script_Action.ListActionScriptRepository;
import com.example.demo_back.Dao.List_Script_Action.List_Action_Script;
import com.example.demo_back.Dao.List_Script_User.ListScriptUserRepository;
import com.example.demo_back.Dao.Script.*;
import com.example.demo_back.Dto.ActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GetActionsController {
    @Autowired
    ScriptRepository scriptRepository;
    @Autowired
    FunitureRepository funitureRepository;
    @Autowired
    ActionRepository actionRepository;
    @Autowired
    ScheduleScriptRepository scheduleScriptRepository;
    @Autowired
    ListActionScriptRepository listActionScriptRepository;
    @ResponseBody
    @PostMapping("/getActions")
    public List<ActionResponse> main(HttpServletRequest request){
        List<ActionResponse> result = new ArrayList<>();
        List<List_Action_Script> list_action_scripts = listActionScriptRepository.findbyscript(Integer.valueOf(request.getParameter("script_id")));
        for(List_Action_Script l:list_action_scripts){
            ActionJpa actionJpas = actionRepository.findActionById(l.getAction_id()).get(0);
            ActionResponse a = new ActionResponse();
            a.setAction_id(actionJpas.getId());
            a.setAction_type(actionJpas.getAction());
            a.setTarget_id(l.getFurniture_id());
            a.setTarget_type(funitureRepository.findFurnitureJpaById(l.getFurniture_id()).get(0).getFurniture_type());
            result.add(a);
        }
        System.out.println(result.size());
        return result;
    }
}
