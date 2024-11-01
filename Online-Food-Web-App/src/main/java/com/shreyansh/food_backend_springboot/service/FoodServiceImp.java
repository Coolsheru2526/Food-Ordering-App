package com.shreyansh.food_backend_springboot.service;

import com.shreyansh.food_backend_springboot.model.Category;
import com.shreyansh.food_backend_springboot.model.Food;
import com.shreyansh.food_backend_springboot.model.Restaurant;
import com.shreyansh.food_backend_springboot.repository.FoodRepository;
import com.shreyansh.food_backend_springboot.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FoodServiceImp implements FoodService{

    @Autowired
    private FoodRepository foodRepository;


    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {

        Food food=new Food();
        food.setFoodCategory(category);
        food.setCreationDate(new Date());
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice((long) req.getPrice());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegetarian());
        food.setIngredients(req.getIngredients());
        food.setRestaurant(restaurant);
        food = foodRepository.save(food);

        restaurant.getFoods().add(food);
        return food;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.deleteById(foodId);
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarian, boolean isNonveg, boolean isSeasonal, String foodCategory) {

        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if(isVegitarian){
            foods = filterByVegetarian(foods,isVegitarian);
        }
        if(isNonveg){
            foods = filterByNonVeg(foods,isNonveg);
        }
        if(isSeasonal){
            foods = filterBySeasonal(foods,isSeasonal);
        }

        if(foodCategory!=null && !foodCategory.isEmpty()){
            foods = filterByCategory(foods,foodCategory);
        }
        return List.of();
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equalsIgnoreCase(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food -> !food.isVegetarian()).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegitarian) {

        return foods.stream().filter(food -> food.isVegetarian()==isVegitarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchByNameOrCategory(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {

        Optional<Food> optionalFood = foodRepository.findById(foodId);

        if(optionalFood.isEmpty()){
            throw new Exception("food not found");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailibilityStatus(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
