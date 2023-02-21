package com.example.demo_back.Dto;

import com.example.demo_back.Dao.Enums.Action_Type;
import com.example.demo_back.Dao.Enums.Furniture_Type;
import lombok.Data;

@Data
public class ActionResponse {
    private Integer action_id;
    private Action_Type action_type;
    private Integer target_id;
    private Furniture_Type target_type;
}
