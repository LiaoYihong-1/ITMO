package com.example.demo_back.Dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class LoginResponse {
    private boolean success;
    private String message;
    private Integer id;
}
