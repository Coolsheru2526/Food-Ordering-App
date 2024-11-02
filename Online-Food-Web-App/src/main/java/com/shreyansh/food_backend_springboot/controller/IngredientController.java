package com.shreyansh.food_backend_springboot.controller;

import com.shreyansh.food_backend_springboot.model.IngredientCategory;
import com.shreyansh.food_backend_springboot.model.IngredientsItem;
import com.shreyansh.food_backend_springboot.request.CreateIngredientCategoryRequest;
import com.shreyansh.food_backend_springboot.request.CreateIngredientRequest;
import com.shreyansh.food_backend_springboot.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody CreateIngredientCategoryRequest req) throws Exception{
        IngredientCategory items=ingredientsService.createIngredientsCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(items,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItem> createIngredient(
            @RequestBody CreateIngredientRequest req) throws Exception{

        IngredientsItem item=ingredientsService.createIngredientsItem(req.getRestaurantId(),req.getName(),req.getIngredientCategoryId());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateStock(@PathVariable Long id) throws Exception{
        IngredientsItem item=ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> restaurantsIngredient(
            @PathVariable Long id) throws Exception{
        List<IngredientsItem> items=ingredientsService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> restaurantsIngredientCategory(
            @PathVariable Long id) throws Exception{
        List<IngredientCategory> items=ingredientsService.findIngredientsCategoryByRestaurantId(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }

}