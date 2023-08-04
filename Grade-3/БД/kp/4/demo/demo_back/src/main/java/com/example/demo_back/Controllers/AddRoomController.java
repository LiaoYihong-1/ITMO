package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.Enums.Room_Type;
import com.example.demo_back.Dao.House.RoomJpa;
import com.example.demo_back.Dao.House.RoomRepository;
import com.example.demo_back.Dto.UniResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class AddRoomController {
    @Autowired
    RoomRepository roomRepository;
    public boolean checkString(String s){
        if ((s == null) | (Objects.equals(s, ""))){
            return false;
        }
        return true;
    }
    @PostMapping("/addroom")
    @ResponseBody
    public UniResponse main(HttpServletRequest request){
        UniResponse uniResponse = new UniResponse();
        String house_id = request.getParameter("house_id");
        String square = request.getParameter("square");
        String room_type = request.getParameter("room_type");
        String height = request.getParameter("height");
        if(checkString(house_id)&&checkString(square)&&checkString(height)&&checkString(room_type)){
            RoomJpa r = new RoomJpa();
            r.setHeight(Double.valueOf(height));
            r.setSquare(Double.valueOf(square));
            r.setHouse_id(Integer.valueOf(house_id));
            r.setRoom_type(Room_Type.valueOf(room_type));
            r.setIs_filled(false);
            roomRepository.save(r);
            uniResponse.setSuccess(true);
            uniResponse.setMessage("success");
        }else{
            uniResponse.setMessage("Maybe you\n1)didn't login\n2)sent a form which is not filled");
            uniResponse.setSuccess(false);
        }
        return uniResponse;
    }
}
