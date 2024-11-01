package com.shreyansh.food_backend_springboot.service;

import com.shreyansh.food_backend_springboot.config.JwtProvider;
import com.shreyansh.food_backend_springboot.model.User;
import com.shreyansh.food_backend_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new Exception("User not found with email "+email);
        }

        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found with email "+email);
        }

        return user;
    }
}
