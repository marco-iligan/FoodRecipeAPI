package com.foodrecipe.api.repository;

import com.foodrecipe.api.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findAllByCategory(String category);
    
}
