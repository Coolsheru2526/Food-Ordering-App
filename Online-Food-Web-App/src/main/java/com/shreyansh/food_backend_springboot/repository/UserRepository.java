package com.shreyansh.food_backend_springboot.repository;

import com.shreyansh.food_backend_springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    public User findByEmail(String username);
}
