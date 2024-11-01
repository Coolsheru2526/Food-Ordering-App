package com.shreyansh.food_backend_springboot.service;

import com.shreyansh.food_backend_springboot.model.Category;
import com.shreyansh.food_backend_springboot.model.Food;
import com.shreyansh.food_backend_springboot.model.Restaurant;
import com.shreyansh.food_backend_springboot.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegitarian,
                                         boolean isNonveg,
                                         boolean isSeasonal,
                                         String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long id) throws Exception;

    public Food updateAvailibilityStatus(Long foodId) throws Exception;
}
