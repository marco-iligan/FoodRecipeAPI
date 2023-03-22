package com.foodrecipe.api.service;

import com.foodrecipe.api.entity.Ingredients;
import com.foodrecipe.api.entity.Profile;
import com.foodrecipe.api.entity.Recipe;
import com.foodrecipe.api.repository.IngredientsRepository;
import com.foodrecipe.api.repository.ProfileRepository;
import com.foodrecipe.api.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private IngredientsRepository ingredientsRepository;

    public Iterable<Recipe> getAllRecipe(Profile profile){
        return recipeRepository.findAllByProfile(profile);
    }

    public Iterable<Recipe> getRecipeByCategory(String request){
        String category = "";
        switch(request.toLowerCase()){
            case "entrees":
                category = "ENTREES";
                break;
            case "main courses":
                category = "MAIN_COURSES";
                break;
            case "sides":
                category = "SIDES";
                break;
            case "drinks":
                category = "DRINKS";
                break;
            case "desserts":
                category = "DESSERTS";
                break;
            default:
                return null;
        }
        return recipeRepository.findAllByCategory(category);
    }

    public Recipe addRecipe(Recipe request){
        Set<Ingredients> ingredients = new HashSet<>();
        for(Ingredients ing:request.getIngredients()){
            if(ing.getId()==null){
                Ingredients ingredient = Ingredients.builder()
                        .name(ing.getName())
                        .description(ing.getDescription())
                        .build();
                Ingredients savedIngredient = ingredientsRepository.save(ingredient);
                ingredients.add(savedIngredient);
            }else{
                ingredients.add(ing);
            }
        }
        Recipe recipe = Recipe.builder()
                .profile(request.getProfile())
                .title(request.getTitle())
                .description(request.getDescription())
                .ingredients(ingredients)
                .build();
        Recipe savedRecipe = recipeRepository.save(recipe);
        return savedRecipe;
    }
}
