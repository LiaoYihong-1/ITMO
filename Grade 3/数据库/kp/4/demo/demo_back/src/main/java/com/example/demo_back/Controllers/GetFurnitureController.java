package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.House.FunitureRepository;
import com.example.demo_back.Dao.House.FurnitureJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GetFurnitureController {
    @Autowired
    FunitureRepository funitureRepository;

    @ResponseBody
    @PostMapping("/getfurniture")
    List<FurnitureJpa> main(HttpServletRequest request){
        return funitureRepository.findAllByRoom(Integer.valueOf(request.getParameter("id")));
    }
}
