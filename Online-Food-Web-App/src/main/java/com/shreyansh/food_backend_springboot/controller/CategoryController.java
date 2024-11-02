package com.shreyansh.food_backend_springboot.controller;

import com.shreyansh.food_backend_springboot.model.Category;
import com.shreyansh.food_backend_springboot.model.User;
import com.shreyansh.food_backend_springboot.service.CategoryService;
import com.shreyansh.food_backend_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @Autowired
    public UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createdCategory(
            @RequestHeader("Authorization")String jwt,
            @RequestBody Category category) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        Category createdCategory=categoryService.createCategory(category.getName(), user.getId());
        return new ResponseEntity<Category>(createdCategory,HttpStatus.CREATED);
    }

    @GetMapping("/category/restaurant/{id}")
    public ResponseEntity<List<Category>> getRestaurantsCategory(
            @PathVariable Long id,
            @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        List<Category> categories=categoryService.findCategoryByRestaurantId(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
