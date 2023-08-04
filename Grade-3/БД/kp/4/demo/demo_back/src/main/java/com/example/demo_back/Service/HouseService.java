package com.example.demo_back.Service;

import com.example.demo_back.Dao.Enums.House_Type;
import com.example.demo_back.Dao.House.HouseJpa;
import com.example.demo_back.Dao.House.HouseRepository;
import com.example.demo_back.Dao.List_User_House.ListUserHouseJpa;
import com.example.demo_back.Dao.List_User_House.ListUserHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class HouseService {
    @Autowired
    HouseRepository houseRepository;
    @Autowired
    ListUserHouseRepository listUserHouseRepository;

    public void addHouse(String house_type,Integer address_id,Integer user_id){
        HouseJpa h = new HouseJpa();
        h.setHouse_type(House_Type.valueOf(house_type.toUpperCase()));
        h.setAddress_id(address_id);
        houseRepository.save(h);
        ListUserHouseJpa listUserHouseJpa = new ListUserHouseJpa();
        listUserHouseJpa.setHouse_id(houseRepository.findNewestid().get(0));
        listUserHouseJpa.setUser_id(user_id);
        listUserHouseRepository.save(listUserHouseJpa);
    }
}
