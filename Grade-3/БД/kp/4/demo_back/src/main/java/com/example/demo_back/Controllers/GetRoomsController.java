package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.House.RoomJpa;
import com.example.demo_back.Dao.House.RoomRepository;
import com.example.demo_back.Dao.Problem.Problem;
import com.example.demo_back.Dao.Problem.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GetRoomsController {
    @Autowired
    RoomRepository roomRepository;
    @PostMapping("/getroom")
    @ResponseBody
    public List<RoomJpa> main(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("house_id"));
        return roomRepository.findRoomJpaByHouse_id(id);
    }
}
