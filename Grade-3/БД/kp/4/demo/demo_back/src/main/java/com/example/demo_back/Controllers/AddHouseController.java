package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.Address.AddressRepository;
import com.example.demo_back.Dto.UniResponse;
import com.example.demo_back.Service.AddressService;
import com.example.demo_back.Service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class AddHouseController {
    @Autowired
    HouseService houseService;
    @Autowired
    AddressService addressService;
    public boolean checkString(String s){
        if ((s == null) | (Objects.equals(s, ""))){
            return false;
        }
        return true;
    }
    @PostMapping("/addhouse")
    @ResponseBody
    public UniResponse main(HttpServletRequest request){
        UniResponse uniResponse = new UniResponse();
        String houseType = request.getParameter("house_type");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String country = request.getParameter("country");
        String userId = request.getParameter("user_id");
        if(checkString(houseType)&&checkString(city)&&checkString(street)&&checkString(country)){
            addressService.addAddress(country,city,street);
            Integer id = addressService.findNewestId();
            houseService.addHouse(houseType,id,Integer.parseInt(userId));
            uniResponse.setMessage("Success");
            uniResponse.setSuccess(true);
        }else {
            uniResponse.setMessage("You sent an invalid value to server!\n");
            uniResponse.setSuccess(false);
        }
        return uniResponse;
    }
}
