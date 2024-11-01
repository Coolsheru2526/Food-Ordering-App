package com.shreyansh.food_backend_springboot.service;

import com.shreyansh.food_backend_springboot.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
