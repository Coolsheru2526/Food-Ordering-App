package com.shreyansh.food_backend_springboot.repository;

import com.shreyansh.food_backend_springboot.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
