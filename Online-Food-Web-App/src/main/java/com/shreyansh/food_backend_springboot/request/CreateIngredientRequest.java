package com.shreyansh.food_backend_springboot.request;

import lombok.Data;

@Data
public class CreateIngredientRequest {
    private Long restaurantId;
    private String name;
    private Long ingredientCategoryId;
}
