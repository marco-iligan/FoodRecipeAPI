package com.foodrecipe.api.controller;

import com.foodrecipe.api.entity.Profile;
import com.foodrecipe.api.entity.Recipe;
import com.foodrecipe.api.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="api/v1/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping(path="/get-all")
    public @ResponseBody Iterable<Recipe> getAllRecipe(@RequestBody Profile request){
        return recipeService.getAllRecipe(request);
    }

    @GetMapping(path="/get")
    public @ResponseBody Recipe getRecipe(@RequestBody Recipe request){
        return recipeService.getRecipe(request);
    }

    @GetMapping(path="/search")
    public @ResponseBody Iterable<Recipe> search(@RequestBody Profile profile, @RequestParam String keyword){
        return recipeService.search(profile,keyword);
    }

    @PostMapping(path="/add")
    public @ResponseBody Recipe addRecipe(@RequestBody Recipe request){
        return recipeService.addRecipe(request);
    }

    @PutMapping(path="/update")
    public @ResponseBody Recipe updateRecipe(@RequestBody Recipe request){
        return recipeService.updateRecipe(request);
    }

    @DeleteMapping(path="/remove")
    public @ResponseBody String removeRecipe(@RequestBody Recipe request){
        return recipeService.removeRecipe(request);
    }

}
