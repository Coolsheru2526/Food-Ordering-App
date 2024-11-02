package com.shreyansh.food_backend_springboot.service;

import com.shreyansh.food_backend_springboot.model.Category;
import com.shreyansh.food_backend_springboot.model.Restaurant;
import com.shreyansh.food_backend_springboot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name,Long userId) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurantByUserId(userId);
        Category createdCategory=new Category();

        createdCategory.setName(name);
        createdCategory.setRestaurant(restaurant);
        return categoryRepository.save(createdCategory);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(id);
        return categoryRepository.findByRestaurantId(id);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> opt=categoryRepository.findById(id);

        if(opt.isEmpty()) {
            throw new Exception("category not exist with id "+id);
        }

        return opt.get();
    }

}