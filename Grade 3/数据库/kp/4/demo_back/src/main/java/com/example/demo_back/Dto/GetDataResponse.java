package com.example.demo_back.Dto;

import lombok.Data;

@Data
public class GetDataResponse {
    private Integer age;
    private String username;
    private String email;
    private String phone;
    private String gender;
    private boolean success;
    private String message;
}
