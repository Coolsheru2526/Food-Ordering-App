package com.shreyansh.food_backend_springboot.repository;

import com.shreyansh.food_backend_springboot.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

}
