package com.shreyansh.food_backend_springboot.service;

import com.shreyansh.food_backend_springboot.model.IngredientCategory;
import com.shreyansh.food_backend_springboot.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {

    public IngredientCategory createIngredientsCategory(
            String name,Long restaurantId) throws Exception;

    public IngredientCategory findIngredientsCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception;

    public List<IngredientsItem> findRestaurantsIngredients(
            Long restaurantId);


    public IngredientsItem createIngredientsItem(Long restaurantId,
                                                 String ingredientName,Long ingredientCategoryId) throws Exception;

    public IngredientsItem updateStock(Long id) throws Exception;

}