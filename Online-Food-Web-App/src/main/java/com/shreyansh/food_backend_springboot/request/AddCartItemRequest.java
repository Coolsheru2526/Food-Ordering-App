package com.shreyansh.food_backend_springboot.request;

import lombok.Data;

import java.util.List;

@Data
public class AddCartItemRequest {

    private Long foodId;

    private int quantity;

    private List<String> ingredients;
}
