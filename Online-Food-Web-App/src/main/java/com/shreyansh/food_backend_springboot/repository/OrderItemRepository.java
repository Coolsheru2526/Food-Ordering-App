package com.shreyansh.food_backend_springboot.repository;

import com.shreyansh.food_backend_springboot.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
