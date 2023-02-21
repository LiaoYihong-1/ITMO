package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.Action.ActionJpa;
import com.example.demo_back.Dao.Action.ActionRepository;
import com.example.demo_back.Dao.Enums.Action_Type;
import com.example.demo_back.Dao.Enums.Furniture_Type;
import com.example.demo_back.Dao.House.FunitureRepository;
import com.example.demo_back.Dao.House.FurnitureJpa;
import com.example.demo_back.Dao.List_Script_Action.ListActionScriptRepository;
import com.example.demo_back.Dao.List_Script_Action.List_Action_Script;
import com.example.demo_back.Dao.List_Script_User.ListScriptUserRepository;
import com.example.demo_back.Dao.Script.*;
import com.example.demo_back.Dto.UniResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddActionController {
    @Autowired
    ScriptRepository scriptRepository;
    @Autowired
    FunitureRepository funitureRepository;
    @Autowired
    ListActionScriptRepository listActionScriptRepository;
    @Autowired
    ActionRepository actionRepository;

    @ResponseBody
    @PostMapping("/addaction")
    public UniResponse main(HttpServletRequest request){
        UniResponse response = new UniResponse();
        response.setMessage("Success");
        response.setSuccess(true);
        Integer script_id = Integer.valueOf(request.getParameter("script_id"));
        Integer furniture_id = Integer.valueOf(request.getParameter("target"));
        Action_Type action_type = Action_Type.valueOf(request.getParameter("action_type").toUpperCase());
        FurnitureJpa f = funitureRepository.findFurnitureJpaById(furniture_id).get(0);
        Furniture_Type furniture_type = f.getFurniture_type();
        System.out.println(furniture_type);
        System.out.println(action_type);
        try {
            ActionJpa actionJpa = actionRepository.findAllByActionAndFurnitrue(action_type, furniture_type).get(0);
            List_Action_Script list_action_script = new List_Action_Script();
            list_action_script.setAction_id(actionJpa.getId());
            list_action_script.setScript_id(script_id);
            list_action_script.setFurniture_id(furniture_id);
            listActionScriptRepository.save(list_action_script);
            return response;
        }catch (IndexOutOfBoundsException i){
            response.setSuccess(false);
            response.setMessage("Make sure that you choose proper action type for furniture.Read guider carefully please.");
            return response;
        }
    }
}
