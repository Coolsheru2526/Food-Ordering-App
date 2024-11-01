package com.shreyansh.food_backend_springboot.response;

import com.shreyansh.food_backend_springboot.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {

    private String message;
    private String jwt;
    private USER_ROLE role;



}
