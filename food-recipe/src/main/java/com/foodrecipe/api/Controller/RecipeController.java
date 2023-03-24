package com.foodrecipe.api.Controller;

import com.foodrecipe.api.Entity.Profile;
import com.foodrecipe.api.Entity.Recipe;
import com.foodrecipe.api.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping(value = "/add")
    public Recipe addRecipe(@RequestBody Recipe recipe){
        return recipeService.saveRecipe(recipe);
    }

    @GetMapping(value = "/all")
    public List<Recipe> getAllRecipe(@RequestBody Profile profile){
        return recipeService.getAllRecipes(profile);
    }

    @GetMapping(value = "/searchByTitle/{title}")
    public List<Recipe> getAllRecipeById(@RequestBody Profile profile, @PathVariable(value = "title") String title){
        return recipeService.getRecipeByTitle(profile, title);
    }

    @GetMapping(value = "/searchByCategory/{category}")
    public List<Recipe> getAllRecipeByCategory(@RequestBody Profile profile, @PathVariable(value = "category") String category){
        return recipeService.getRecipeByCategory(profile, category);
    }

    @GetMapping(value = "/searchByIngredient/{ingredient}")
    public List<Recipe> getAllRecipeByIngredient(@RequestBody Profile profile, @PathVariable(value = "ingredient") String ingredient){
        return recipeService.getRecipeByIngredient(profile, ingredient);
    }

    @GetMapping(value = "/findbyid/{recipeId}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable(value = "recipeId") long recipeId){
        try{
            Recipe recipe = recipeService.getRecipeById(recipeId);
            return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public Recipe updateRecipe(@RequestBody Recipe recipe){
        return recipeService.updateRecipe(recipe);
    }

    @DeleteMapping("/delete")
    public void deleteRecipe(@RequestBody Recipe recipe){
        recipeService.deleteRecipe(recipe);
    }
}
