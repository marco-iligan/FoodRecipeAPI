package com.foodrecipe.api.service;

import com.foodrecipe.api.entity.*;
import com.foodrecipe.api.exception.ApiRequestException;
import com.foodrecipe.api.repository.IngredientsRepository;
import com.foodrecipe.api.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientsRepository ingredientsRepository;

    public Iterable<Recipe> getAllRecipe(Profile profile){
        return recipeRepository.findAllByProfile(profile);
    }

    public Iterable<Recipe> search(Profile profile, String keyword){
        if(keyword != null && !keyword.equals(" ") && !keyword.equals("")) {
            return recipeRepository.search(profile.getUserId(), keyword);
        }
        return recipeRepository.findAllByProfile(profile);
    }

    public Recipe addRecipe(Recipe request){
        Set<Ingredients> ingredients = addIngredients(request.getIngredients());
        Recipe recipe = Recipe.builder()
                .profile(request.getProfile())
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .ingredients(ingredients)
                .build();
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Recipe request){
        Set<Ingredients> ingredients = addIngredients(request.getIngredients());
        Recipe recipe = recipeRepository.findById(request.getId()).orElseThrow(
                () -> new ApiRequestException("Recipe doesn't exists"));
        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setCategory(recipe.getCategory());
        recipe.setIngredients(ingredients);

        return  recipeRepository.save(recipe);
    }

    public Recipe getRecipe(Recipe request){
        Recipe recipe = recipeRepository.findByProfileAndId(request.getProfile(), request.getId());
        if(recipe == null){
            throw new ApiRequestException("Unauthorized Access");
        }
        return recipe;
    }

    public String removeRecipe(Recipe request){
        Recipe recipe = recipeRepository.findByProfileAndId(request.getProfile(), request.getId());
        if(recipe != null){
            recipeRepository.delete(recipe);
            return recipe.getTitle()+" was removed";
        }
        throw new ApiRequestException("Unauthorized Access");
    }

    private Set<Ingredients> addIngredients(Set<Ingredients> request){
        Set<Ingredients> ingredients = new HashSet<>();
        for(Ingredients ing:request){
            if(ing.getId()==null){
                Ingredients ingredient = Ingredients.builder()
                        .name(ing.getName())
                        .description(ing.getDescription())
                        .build();
                Ingredients savedIngredient = ingredientsRepository.save(ingredient);
                ingredients.add(savedIngredient);
            }else{
                try{
                    ingredients.add(ingredientsRepository.findById(ing.getId()).orElseThrow());
                }catch (Exception e){
                    continue;
                }
            }
        }
        return ingredients;
    }
}
