package com.shreyansh.food_backend_springboot.controller;

import com.shreyansh.food_backend_springboot.model.Food;
import com.shreyansh.food_backend_springboot.model.Restaurant;
import com.shreyansh.food_backend_springboot.model.User;
import com.shreyansh.food_backend_springboot.request.CreateFoodRequest;
import com.shreyansh.food_backend_springboot.response.MessageResponse;
import com.shreyansh.food_backend_springboot.service.FoodService;
import com.shreyansh.food_backend_springboot.service.RestaurantService;
import com.shreyansh.food_backend_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req,req.getCategory(),restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
    @PostMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Food Deleted Successfully");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Food> updateAvailabilityFoodStatus(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.updateAvailibilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
