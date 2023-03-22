package com.foodrecipe.api.service;

import com.foodrecipe.api.entity.*;
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

    public Iterable<Recipe> search(Profile profile, String keyword){
        if(!keyword.equals(null) && !keyword.equals(" ") && !keyword.equals("")) {
            return recipeRepository.search(profile.getUserId(), keyword);
        }
        return recipeRepository.findAllByProfile(profile);
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
        switch(request.getCategory().toString().toLowerCase()){
            case "entrees":
                recipe.setCategory(Category.ENTREES);
                break;
            case "main courses":
                recipe.setCategory(Category.MAIN_COURSES);
                break;
            case "sides":
                recipe.setCategory(Category.SIDES);
                break;
            case "drinks":
                recipe.setCategory(Category.DRINKS);
                break;
            case "desserts":
                recipe.setCategory(Category.DESSERTS);
                break;
            default:
                break;
        }
        Recipe savedRecipe = recipeRepository.save(recipe);
        return savedRecipe;
    }
}
