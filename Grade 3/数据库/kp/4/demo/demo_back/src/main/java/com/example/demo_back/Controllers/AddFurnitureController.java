package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.Enums.Furniture_Type;
import com.example.demo_back.Dao.House.FunitureRepository;
import com.example.demo_back.Dao.House.FurnitureJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class AddFurnitureController {
    @Autowired
    FunitureRepository funitureRepository;
    public boolean checkString(String s){
        if ((s == null) | (Objects.equals(s, ""))){
            return false;
        }
        return true;
    }

    @PostMapping("/addfurniture")
    @ResponseBody
    public Boolean main(HttpServletRequest request){
        String type = request.getParameter("furniture_type");
        String manufacture = request.getParameter("manufacture");
        String id = request.getParameter("room_id");
        if(checkString(type)&&checkString(manufacture)&&checkString(id)){
            FurnitureJpa f = new FurnitureJpa();
            f.setFurniture_type(Furniture_Type.valueOf(type));
            f.setAvailable(true);
            f.setManufacture(manufacture);
            f.setRoom_id(Integer.valueOf(id));
            funitureRepository.save(f);
            return true;
        }else{
            return false;
        }
    }
}
