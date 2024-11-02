package com.shreyansh.food_backend_springboot.repository;

import com.shreyansh.food_backend_springboot.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientsCategoryRepository
        extends JpaRepository<IngredientCategory, Long> {


    List<IngredientCategory> findByRestaurantId(Long id);

    @Query("SELECT e FROM IngredientCategory e "
            + "WHERE e.restaurant.id = :restaurantId "
            + "AND lower(e.name) = lower(:name)")
    IngredientCategory findByRestaurantIdAndNameIgnoreCase(
            @Param("restaurantId") Long restaurantId, @Param("name") String name);
}