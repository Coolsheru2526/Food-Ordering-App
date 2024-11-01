package com.shreyansh.food_backend_springboot.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
