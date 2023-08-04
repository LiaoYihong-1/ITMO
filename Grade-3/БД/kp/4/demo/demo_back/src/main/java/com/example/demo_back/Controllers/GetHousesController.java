package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.Account.AccountJpa;
import com.example.demo_back.Dao.Address.AddressJpa;
import com.example.demo_back.Dao.Address.AddressRepository;
import com.example.demo_back.Dao.House.HouseJpa;
import com.example.demo_back.Dto.GetHousesResponse;
import com.example.demo_back.Dto.House;
import com.example.demo_back.Service.AccountService;
import com.example.demo_back.Service.AddressService;
import com.example.demo_back.Service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GetHousesController{
    @Autowired
    AddressService addressService;
    @Autowired
    HouseService houseService;
    @Autowired
    AccountService accountService;
    @PostMapping("/gethouses")
    @ResponseBody
    public List<House> main(HttpServletRequest request){
        List<House> houseList = new ArrayList<>();
        Integer id = Integer.parseInt(request.getParameter("id"));
        AccountJpa accountJpa = accountService.findAccountById(id);
        for(HouseJpa h : accountJpa.getHouses() ){
            House house = new House();
            AddressJpa addressJpa = addressService.getAddressById(h.getAddress_id());
            house.setHouse_type(h.getHouse_type().name());
            house.setId(h.getId());
            house.setCity(addressJpa.getCity().name());
            house.setCountry(addressJpa.getCountry().name());
            house.setStreet(addressJpa.getStreet());
            houseList.add(house);
        }

        return houseList;
    }
}
