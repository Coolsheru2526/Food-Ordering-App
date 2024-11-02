package com.shreyansh.food_backend_springboot.repository;

import com.shreyansh.food_backend_springboot.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    public Optional<Cart> findByCustomerId(Long userId);
}
