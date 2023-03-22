package com.foodrecipe.api.Service;

import com.foodrecipe.api.Entity.Category;
import com.foodrecipe.api.Entity.Recipe;
import com.foodrecipe.api.Exception.Exceptions;
import com.foodrecipe.api.Repository.RecipeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRespository recipeRespository;

    public Recipe saveRecipe(Recipe recipe){
        return recipeRespository.save(recipe);
    }

    public List<Recipe> getAllRecipes(){
        return recipeRespository.findAll();
    }

    public Recipe getRecipeById(long recipeId){
        return recipeRespository.findById(recipeId).get();
    }

    public Recipe updateRecipe(Recipe recipe){
        recipeRespository.findById(recipe.getRecipeId()).orElseThrow(
                () -> new Exceptions("Recipe with id = " + recipe.getRecipeId() +
                        " does not exist.", new RuntimeException("Bad Request"))
        );
        return recipeRespository.save(recipe);
    }

    public void deleteRecipe(long recipeId){
        recipeRespository.findById(recipeId).orElseThrow(
                () -> new Exceptions("Recipe does not exist", new RuntimeException("Bad Request"))
        );
        recipeRespository.deleteById(recipeId);
    }
}
