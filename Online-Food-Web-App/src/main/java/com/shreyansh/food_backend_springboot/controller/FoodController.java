package com.shreyansh.food_backend_springboot.controller;

import com.shreyansh.food_backend_springboot.model.Food;
import com.shreyansh.food_backend_springboot.model.User;
import com.shreyansh.food_backend_springboot.service.FoodService;
import com.shreyansh.food_backend_springboot.service.RestaurantService;
import com.shreyansh.food_backend_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@PathVariable Long restaurantId,
                                           @RequestParam boolean vegetarian,
                                           @RequestParam boolean seasonal,
                                           @RequestParam boolean nonveg,
                                           @RequestParam(required = false) String food_category,
                                           @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.getRestaurantsFood(restaurantId, vegetarian, nonveg, seasonal, food_category);

        return new ResponseEntity<>(foods,HttpStatus.OK);

    }
}
