package com.shreyansh.food_backend_springboot.repository;

import com.shreyansh.food_backend_springboot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    public List<Category> findByRestaurantId(Long id);
}
