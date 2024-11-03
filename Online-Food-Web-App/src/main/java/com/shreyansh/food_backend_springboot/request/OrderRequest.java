package com.shreyansh.food_backend_springboot.request;

import com.shreyansh.food_backend_springboot.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;
    private Address deliveryAddress;
}
