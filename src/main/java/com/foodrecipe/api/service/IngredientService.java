package com.foodrecipe.api.service;

import com.foodrecipe.api.entity.Ingredients;
import com.foodrecipe.api.exception.ApiRequestException;
import com.foodrecipe.api.repository.IngredientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientsRepository ingredientsRepository;

    public String addIngredient(Ingredients request){
        Ingredients ingredient = Ingredients.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        ingredientsRepository.save(ingredient);
        return ingredient.getName()+" was added";
    }

    public String updateIngredient(Ingredients request){
        Ingredients ingredient = ingredientsRepository.findById(request.getId()).orElseThrow(
                () -> new ApiRequestException("Ingredient doesn't exists."));
        ingredient.setName(request.getName());
        ingredient.setDescription(request.getDescription());
        ingredientsRepository.save(ingredient);

        return ingredient.getName()+" was updated";
    }

    public Iterable<Ingredients> getAllIngredients(){
        return ingredientsRepository.findAll();
    }

    public String removeIngredient(Ingredients request){
        Ingredients ingredient = ingredientsRepository.findById(request.getId()).orElseThrow(
                () -> new ApiRequestException("Ingredient doesn't exists."));
        ingredientsRepository.delete(ingredient);

        return ingredient.getName()+" was removed";
    }
}
