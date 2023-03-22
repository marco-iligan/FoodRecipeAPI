package com.foodrecipe.api.controller;

import com.foodrecipe.api.entity.Ingredients;
import com.foodrecipe.api.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/ingredients")
public class IngredientsController {
    @Autowired
    private IngredientService ingredientService;

    @GetMapping(path="/get-all")
    public @ResponseBody Iterable<Ingredients> getAllIngredients(){
        return ingredientService.getAllIngredients();
    }

    @PostMapping(path="/add")
    public @ResponseBody String addIngredient(@RequestBody Ingredients request){
        return ingredientService.addIngredient(request);
    }

    @PutMapping(path="/update")
    public @ResponseBody String updateIngredient(@RequestBody Ingredients request){
        return ingredientService.updateIngredient(request);
    }

    @DeleteMapping(path="/remove")
    public @ResponseBody String removeIngredient(@RequestBody Ingredients request){
        return ingredientService.removeIngredient(request);
    }
}
