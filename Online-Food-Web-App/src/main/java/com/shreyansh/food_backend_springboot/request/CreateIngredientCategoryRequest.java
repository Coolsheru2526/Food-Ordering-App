package com.shreyansh.food_backend_springboot.request;

import lombok.Data;

@Data
public class CreateIngredientCategoryRequest {
    private Long restaurantId;
    private String name;
}
