package com.shreyansh.food_backend_springboot.request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {

    private Long cartItemId;

    public int quantity;
}
