package com.foodrecipe.api.controller;

import com.foodrecipe.api.entity.Profile;
import com.foodrecipe.api.entity.Recipe;
import com.foodrecipe.api.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping(path="/get-all")
    public @ResponseBody Iterable<Recipe> getAllRecipe(@RequestBody Profile request){
        return recipeService.getAllRecipe(request);
    }

    @GetMapping(path="/search")
    public @ResponseBody Iterable<Recipe> search(@RequestBody Profile profile, @RequestParam String keyword){
        return recipeService.search(profile,keyword);
    }

    @PostMapping(path="/add")
    public @ResponseBody Recipe addRecipe(@RequestBody Recipe request){
        return recipeService.addRecipe(request);
    }
}
