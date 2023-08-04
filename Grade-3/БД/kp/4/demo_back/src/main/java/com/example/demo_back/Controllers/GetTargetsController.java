package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.Account.AccountJpa;
import com.example.demo_back.Dao.House.*;
import com.example.demo_back.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class GetTargetsController {
    @Autowired
    AccountService accountService;
    @Autowired
    FunitureRepository funitureRepository;
    @Autowired
    RoomRepository roomRepository;
    @ResponseBody
    @PostMapping("/getFurnitures")
    List<Integer> main(HttpServletRequest request){
        List<Integer> result = new ArrayList<>();
        String id = request.getParameter("id");
        AccountJpa a = accountService.findAccountById(Integer.valueOf(id));
        for(HouseJpa h:a.getHouses()){
            List<RoomJpa> roomJpas = roomRepository.findRoomJpaByHouse_id(h.getId());
            for(RoomJpa r : roomJpas){
                List<FurnitureJpa> furnitureJpas = funitureRepository.findAllByRoom(r.getId());
                for(FurnitureJpa f:furnitureJpas){
                    result.add(f.getId());
                }
            }
        }
        Collections.sort(result);
        return result;
    }
}
