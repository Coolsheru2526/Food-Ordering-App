package com.shreyansh.food_backend_springboot.request;

import com.shreyansh.food_backend_springboot.model.Address;
import com.shreyansh.food_backend_springboot.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
