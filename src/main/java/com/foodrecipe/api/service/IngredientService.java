package com.foodrecipe.api.service;

import com.foodrecipe.api.entity.Ingredients;
import com.foodrecipe.api.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    @Autowired
    private IngredientsRepository ingredientsRepository;

    public String addIngredient(Ingredients request){
        Ingredients ingredient = Ingredients.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        ingredientsRepository.save(ingredient);
        return ingredient.getName()+" was added";
    }

    public String updateIngredient(Ingredients request){
        Ingredients ingredient = ingredientsRepository.findById(request.getId()).orElseThrow();
        if(ingredient!=null){
            ingredient.setName(request.getName());
            ingredient.setDescription(request.getDescription());
            ingredientsRepository.save(ingredient);

            return ingredient.getName()+" was updated";
        }
        return "Ingredient doesn't exists";
    }

    public Iterable<Ingredients> getAllIngredients(){
        return ingredientsRepository.findAll();
    }

    public String removeIngredient(Ingredients request){
        Ingredients ingredient = ingredientsRepository.findById(request.getId()).orElseThrow();
        if(ingredient!=null){
            ingredientsRepository.delete(ingredient);

            return ingredient.getName()+" was removed";
        }

        return "Ingredient doesn't exists";
    }
}
