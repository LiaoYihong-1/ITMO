package com.example.demo_back.Dto;

import com.example.demo_back.Dao.Enums.House_Type;
import com.example.demo_back.Dao.House.HouseJpa;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;
@Data
public class GetHousesResponse {
    private List<House> houses = new ArrayList<>();
    private boolean success = false;
    private String message = "";
    public void addHouse(House h){
        houses.add(h);
    }
}
