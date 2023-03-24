package com.foodrecipe.api.Service;

import com.foodrecipe.api.Entity.Profile;
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

    public List<Recipe> getAllRecipes(Profile profile){
        return recipeRespository.findAllByProfile(profile);
    }

    public List<Recipe> getRecipeByTitle(Profile profile, String title){
        if (recipeRespository.findByTitle(profile.getProfileId(), title).size() == 0){
            throw new Exceptions("Recipe with title = " + title +
                    " does not exist.", new RuntimeException("Bad Request"));
        }
        return recipeRespository.findByTitle(profile.getProfileId(), title);
    }

    public List<Recipe> getRecipeByCategory(Profile profile, String category){
        if (recipeRespository.findByCategory(profile.getProfileId(), category).size() == 0){
            throw new Exceptions("Recipe with title = " + category +
                    " does not exist.", new RuntimeException("Bad Request"));
        }
        return recipeRespository.findByCategory(profile.getProfileId(), category);
    }

    public List<Recipe> getRecipeByIngredient(Profile profile, String ingredient){
        if (recipeRespository.findByIngredient(profile.getProfileId(), ingredient).size() == 0){
            throw new Exceptions("Recipe with ingredient = " + ingredient +
                    " does not exist.", new RuntimeException("Bad Request"));
        }
        return recipeRespository.findByIngredient(profile.getProfileId(), ingredient);
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

    public void deleteRecipe(Recipe recipe){
        if (recipeRespository.findByIdAndProfile(recipe.getProfile().getProfileId(), recipe.getRecipeId()).size() == 0){
            throw new Exceptions("Recipe with Recipe ID = " + recipe.getRecipeId() +
                    " does not exist.", new RuntimeException("Bad Request"));
        }
        recipeRespository.deleteById(recipe.getRecipeId());
    }
}
